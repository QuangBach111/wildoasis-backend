package com.example.backend.service;

import com.example.backend.model.dto.CabinDTO;

import java.io.IOException;
import java.util.List;

public interface CabinService {
	List<CabinDTO> getAllCabin();
	Long deleteCabinById(Long id);

	Long createCabin(CabinDTO cabinDTO) throws IOException;

	void updateCabin(CabinDTO cabinDTO) throws IOException;
}