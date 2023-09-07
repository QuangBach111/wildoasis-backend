package com.example.backend_v2.service;

import com.example.backend_v2.model.dto.CabinDTO;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface CabinService {
	Page<CabinDTO> getAllCabinPagination(int pageNo, int pageSize, String filter, String sortBy);
	Long deleteCabinById(Long id);

	Long createCabin(CabinDTO cabinDTO) throws IOException;

	void updateCabin(CabinDTO cabinDTO) throws IOException;
}