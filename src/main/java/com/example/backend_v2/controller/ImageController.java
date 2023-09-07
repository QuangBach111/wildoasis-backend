package com.example.backend_v2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
//@CrossOrigin
@RestController
@RequestMapping("/images")
public class ImageController {
	@Value("${images.cabins.path}")
	private String imageCabinPath;

	@Value("${images.avatar.path}")
	private String imageAvatarPath;

	@GetMapping("/cabins/{imageName}")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getImageCabinDynamicType(@PathVariable("imageName") String imageName ) throws FileNotFoundException {
		MediaType contentType = MediaType.IMAGE_JPEG;
		FileInputStream fis = new FileInputStream(imageCabinPath + imageName);

		return ResponseEntity.ok()
				.contentType(contentType)
				.body(new InputStreamResource(fis));
	}

	@GetMapping("/avatars/{imageName}")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getImageAvatarDynamicType(@PathVariable("imageName") String imageName ) throws FileNotFoundException {
		MediaType contentType = MediaType.IMAGE_JPEG;
		FileInputStream fis = new FileInputStream(imageAvatarPath + imageName);

		return ResponseEntity.ok()
				.contentType(contentType)
				.body(new InputStreamResource(fis));
	}
}