package com.example.backend.service;

import com.example.backend.model.dto.CabinDTO;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface CabinService {
	Page<CabinDTO> getAllCabinPagination(int pageNo, int pageSize, String filter, String sortBy);
	Long deleteCabinById(Long id);

	Long createCabin(CabinDTO cabinDTO) throws IOException;

	void updateCabin(CabinDTO cabinDTO) throws IOException;
}