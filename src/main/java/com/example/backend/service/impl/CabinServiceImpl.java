package com.example.backend.service.impl;

import com.example.backend.model.dto.CabinDTO;
import com.example.backend.model.entity.Cabin;
import com.example.backend.model.mapper.CabinMapper;
import com.example.backend.repo.CabinRepo;
import com.example.backend.service.CabinService;
import com.example.backend.utils.Helper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CabinServiceImpl implements CabinService {
	final private CabinRepo cabinRepo;
	final private CabinMapper cabinMapper;
	private final String FOLDER_PATH = "/Users/buiqu/OneDrive/Máy tính/workspace/reactjs/the wild oasis/backend/src/main/resources/static/cabins";

	@Transactional(readOnly=true)
	@Override
	public List<CabinDTO> getAllCabin() {
		return cabinRepo.getAll()
				.map(cabinMapper::mapToCabinDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Long deleteCabinById(Long id) {
		boolean hasCabin = cabinRepo.existsById(id);

		if (hasCabin) {
			cabinRepo.deleteById(id);
			return id;
		}

		throw new EntityNotFoundException("Cabin is not found");
	}

	@Override
	public Long createCabin(CabinDTO cabinDTO) throws IOException {
		// Upload file to file system
		String imageUrl = Helper.uploadImageToFileSystem(cabinDTO.getImage(), FOLDER_PATH, cabinDTO.getName());

		// Set path file to cabin
		cabinDTO.setImageUrl(imageUrl);

		// Map CartDTO to Cart, and save to database
		Cabin createdCabin = cabinRepo.save(cabinMapper.mapToCabin(cabinDTO));

		// Return cabin id
		return createdCabin.getId();
	}

	@Override
	public void updateCabin(CabinDTO cabinDTO) throws IOException {
		// If Image is new
		if(cabinDTO.getImage() != null) {
			String imageUrl = Helper.uploadImageToFileSystem(cabinDTO.getImage(), FOLDER_PATH, cabinDTO.getName());
		}
		cabinRepo.save(cabinMapper.mapToCabin(cabinDTO));
	}
}