package com.example.backend.controller;

import com.example.backend.model.dto.CabinDTO;
import com.example.backend.service.CabinService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cabins")
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

	@PutMapping()
	public ResponseEntity<Long> editCabin(@ModelAttribute CabinDTO cabinDTO) throws IOException {
		cabinService.updateCabin(cabinDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/images/{imageName}")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getImageDynamicType(@PathVariable("imageName") String imageName ) throws FileNotFoundException {
		MediaType contentType = MediaType.IMAGE_JPEG;
		FileInputStream fis = new FileInputStream(String.format("src/main/resources/static/images/cabins/%s", imageName));

		assert fis != null;
		return ResponseEntity.ok()
				.contentType(contentType)
				.body(new InputStreamResource(fis));
	}
}