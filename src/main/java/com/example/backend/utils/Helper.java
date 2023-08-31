package com.example.backend.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Helper {
	private static final String ROOT_PATH = "/Users/buiqu/OneDrive/Máy tính/workspace/reactjs/the wild oasis/backend/src/main/resources/static";

	public static String uploadImageToFileSystem(MultipartFile image, String folder, String name) throws IOException {
//		String fullPath =ROOT_PATH + "/" + folder + "/" + name;
//		File file = new File(fullPath);
//		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream((file)));
//		stream.write(image.getBytes());
//		stream.close();
//		return fullPath;
		byte[] bytes = image.getBytes();
		Path path = Paths.get(folder + image.getOriginalFilename());
		Files.write(path, bytes);
		return folder + image.getOriginalFilename();
	}
}