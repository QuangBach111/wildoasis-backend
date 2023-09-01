package com.example.backend.service.impl;

import com.example.backend.model.dto.CabinDTO;
import com.example.backend.model.entity.Cabin;
import com.example.backend.model.mapper.CabinMapper;
import com.example.backend.repo.CabinRepo;
import com.example.backend.service.CabinService;
import com.example.backend.utils.Helper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

	@Transactional(readOnly=true)
	@Override
	public List<CabinDTO> getAllCabin() {
		return cabinRepo.getAll()
				.map(cabinMapper::mapToCabinDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Long deleteCabinById(Long id) {
		Cabin cabin = cabinRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Cabin with id: %d is not found!", id)));

		// delete cabin image in file system
		Helper.removeImageFromFileSystem(cabin.getImageUrl(), imageCabinPath);

		// remove cabin in db
		cabinRepo.deleteById(id);


		return id;
	}

	@Override
	public Long createCabin(CabinDTO cabinDTO) throws IOException {
		// Upload file to file system
		String imageName = Helper.uploadImageToFileSystem(cabinDTO.getImage(), imageCabinPath);

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
			String imageUrl = Helper.uploadImageToFileSystem(cabinDTO.getImage(), imageCabinPath);
		}
		cabinRepo.save(cabinMapper.mapToCabin(cabinDTO));
	}
}