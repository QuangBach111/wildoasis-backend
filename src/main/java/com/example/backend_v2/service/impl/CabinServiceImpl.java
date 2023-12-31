package com.example.backend_v2.service.impl;

import com.example.backend_v2.model.dto.CabinDTO;
import com.example.backend_v2.model.entity.Cabin;
import com.example.backend_v2.model.mapper.CabinMapper;
import com.example.backend_v2.repo.CabinRepo;
import com.example.backend_v2.service.CabinService;
import com.example.backend_v2.utils.ImageUtils;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CabinServiceImpl implements CabinService {
	@Autowired
	private CabinRepo cabinRepo;
	@Autowired
	private CabinMapper cabinMapper;

	@Value("${images.cabins.path}")
	private String imageCabinPath;

	@Value("${images.cabins.url}")
	private String imageCabinUrl;

	//	@Transactional(readOnly=true)
	@Override
	public Page<CabinDTO> getAllCabinPagination(int pageNo, int pageSize, String filter, String sortBy) {

		PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize);

		//SORT BY (field-direction)

		if (!sortBy.isEmpty()) {
			String[] sortByArr = sortBy.split("-");
			pageRequest = pageRequest.withSort(Sort.Direction.valueOf(sortByArr[1].toUpperCase()), sortByArr[0]);
		}


		// FILTER
		//with discount
		if (!filter.isEmpty()) {
			if (filter.contains("with")) {
				return cabinRepo.findAllWithDiscount(pageRequest)
						.map(cabin -> cabinMapper.mapToCabinDTO(cabin));
			}
			//with no discount
			if (filter.contains("no")) {
				return cabinRepo.findAllWithNoDiscount(pageRequest)
						.map(cabin -> cabinMapper.mapToCabinDTO(cabin));
			}
		}
		return cabinRepo.findAll(pageRequest)
				.map(cabin -> cabinMapper.mapToCabinDTO(cabin));

	}

	@Override
	public Long deleteCabinById(Long id) {
		Cabin cabin = cabinRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Cabin with id: %d is not found!", id)));

		// delete cabin image in file system
		ImageUtils.removeImageFromFileSystem(cabin.getImageUrl(), imageCabinPath);

		// remove cabin in db
		cabinRepo.deleteById(id);


		return id;
	}

	@Override
	public Long createCabin(CabinDTO cabinDTO) throws IOException {
		// Upload file to file system
		String imageName = ImageUtils.uploadImageToFileSystem(cabinDTO.getImage(), imageCabinPath, cabinDTO.getName());

		// Set path file to cabin
		cabinDTO.setImageUrl(imageCabinUrl + imageName);

		// Map CartDTO to Cart, and save to database
		Cabin createdCabin = cabinRepo.save(cabinMapper.mapToCabin(cabinDTO));

		// Return cabin id
		return createdCabin.getId();
	}

	@Override
	public void updateCabin(CabinDTO cabinDTO) throws IOException {
		// If Image is new
		if (cabinDTO.getImage() != null) {
			String imageUrl = ImageUtils.uploadImageToFileSystem(cabinDTO.getImage(), imageCabinPath, cabinDTO.getName());
		}
		cabinRepo.save(cabinMapper.mapToCabin(cabinDTO));
	}
}