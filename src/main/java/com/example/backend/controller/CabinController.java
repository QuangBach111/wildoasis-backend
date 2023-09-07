package com.example.backend.controller;

import com.example.backend.model.dto.CabinDTO;
import com.example.backend.service.CabinService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@CrossOrigin
@RestController
@RequestMapping("/cabins")
@AllArgsConstructor
public class CabinController {
	final private CabinService cabinService;

	@GetMapping
	public ResponseEntity<Page<CabinDTO>> getAllCabin(
			@RequestParam(name="pageNo", required=false) Integer pageNo,
			@RequestParam(name="filter", required=false) String filter,
			@RequestParam(name="sortBy", required=false) String sortBy
	) {
		final int PAGE_SIZE = 5;
		pageNo = pageNo == null || pageNo == 0 ? 1 : pageNo;

		Page<CabinDTO> cabinDTOPage = cabinService.getAllCabinPagination(pageNo, PAGE_SIZE, filter, sortBy);

		return ResponseEntity.ok(cabinDTOPage);
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

	@GetMapping("/images/cabins/{imageName}")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getImageCabinDynamicType(@PathVariable("imageName") String imageName) throws FileNotFoundException {
		MediaType contentType = MediaType.IMAGE_JPEG;
		FileInputStream fis = new FileInputStream(String.format("src/main/resources/static/images/cabins/%s", imageName));

		return ResponseEntity.ok()
				.contentType(contentType)
				.body(new InputStreamResource(fis));
	}

	@GetMapping("/images/avatars/{imageName}")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getImageAvatarDynamicType(@PathVariable("imageName") String imageName) throws FileNotFoundException {
		MediaType contentType = MediaType.IMAGE_JPEG;
		FileInputStream fis = new FileInputStream(String.format("src/main/resources/static/images/avatars/%s", imageName));

		return ResponseEntity.ok()
				.contentType(contentType)
				.body(new InputStreamResource(fis));
	}
}