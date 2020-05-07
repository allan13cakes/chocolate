package com.allan.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.tomcat.util.codec.binary.Base64;

public class ImageUtils {
	public static String encodeImage(byte[] imageByteArray) {
		return Base64.encodeBase64String(imageByteArray);
	}

	public static byte[] decodeImage(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}

	public static String convertImgToBase64String(String filePath) {
		File file = new File(filePath);
		try {
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);

			String imageDataString = ImageUtils.encodeImage(imageData);

			imageInFile.close();
			return imageDataString;

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
