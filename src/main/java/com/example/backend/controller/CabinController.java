package com.example.backend.controller;

import com.example.backend.model.dto.CabinDTO;
import com.example.backend.service.CabinService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/cabins")
@AllArgsConstructor
public class CabinController {
	final private CabinService cabinService;

	@GetMapping
	public ResponseEntity<List<CabinDTO>> getAllCabin() {
		return ResponseEntity.ok(cabinService.getAllCabin());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deleteCabinById(@PathVariable("id") Long cabinId) {
		return ResponseEntity.ok(cabinService.deleteCabinById(cabinId));
	}

	@PostMapping
	public ResponseEntity<Long> createCabin(@ModelAttribute CabinDTO cabinDTO) throws IOException {
		return new ResponseEntity<>(cabinService.createCabin(cabinDTO), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Long> editCabin(@ModelAttribute CabinDTO cabinDTO) throws IOException {
		cabinService.updateCabin(cabinDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}