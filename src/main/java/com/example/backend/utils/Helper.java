package com.example.backend.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class Helper {
	public static String uploadImageToFileSystem(MultipartFile image, String folder) throws IOException {
		String fullPath = folder + "/" + image.getOriginalFilename();

		createCabinsDirIfNeeded(folder);
		File file = new File(fullPath);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream((file)));
		stream.write(image.getBytes());
		stream.close();

		return image.getOriginalFilename();
	}

	private static void createCabinsDirIfNeeded(String folder) {
		File file = new File(folder);

		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public static void removeImageFromFileSystem(String imageUrl, String folderPath) {
		String imagePath = convertImageUrlToImagePath(imageUrl, folderPath);
		assert imagePath != null;

		File file = new File(imagePath);

		if (file.exists()){
			file.delete();
		}
	}

	private static String convertImageUrlToImagePath(String imageUrl, String folderPath) {
		String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
		return folderPath + imageName;
	}
}