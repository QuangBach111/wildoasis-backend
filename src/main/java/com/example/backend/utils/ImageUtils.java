package com.example.backend.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

public class ImageUtils {
	public static String uploadImageToFileSystem(MultipartFile image, String folder, String imageName) throws IOException {
		String subFix = Objects.requireNonNull(image.getOriginalFilename()).split("\\.")[1];
		String fullPath = folder + imageName + "." +subFix;

		createCabinsDirIfNeeded(folder);

		File file = new File(fullPath);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream((file)));
		stream.write(image.getBytes());
		stream.close();

		return imageName + "." +subFix;
	}

	private static void createCabinsDirIfNeeded(String folder) {
		File file = new File(folder);

		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public static void removeImageFromFileSystem(String imageUrl, String folderPath) {
		String imagePath = convertImageUrlToImagePath(imageUrl, folderPath);

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