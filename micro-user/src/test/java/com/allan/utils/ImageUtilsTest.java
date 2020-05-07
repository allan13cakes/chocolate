package com.allan.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;

import org.junit.jupiter.api.Test;

public class ImageUtilsTest {
	@Test
	public void base64Test() {
		String path = "src/test/resources/low_quality.jpeg";
		System.out.println(ImageUtils.convertImgToBase64String(path));
	}

	@Test
	public void qualityTest() throws IOException {
		BufferedImage image = ImageIO.read(new File("src/test/resources/7342023.jpeg"));
		Iterator writers = ImageIO.getImageWritersBySuffix("jpeg");
		if (!writers.hasNext())
			throw new IllegalStateException("No writers for jpeg?!");
		ImageWriter writer = (ImageWriter) writers.next();
		ImageWriteParam imageWriteParam = writer.getDefaultWriteParam();
		imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		List thumbNails = null;
		IIOImage iioImage = new IIOImage(image, thumbNails, (IIOMetadata) null);
		write(writer, imageWriteParam, iioImage, "src/test/resources/high_quality.jpeg", 1.0f);
		write(writer, imageWriteParam, iioImage, "src/test/resources/low_quality.jpeg", 0.2f);
		write(writer, imageWriteParam, iioImage, "src/test/resources/mid_quality.jpeg", 0.5f);
	}

	public static void write(ImageWriter writer, ImageWriteParam imageWriteParam, IIOImage iioImage, String filename,
			float compressionQuality) throws IOException {
		ImageOutputStream out = ImageIO.createImageOutputStream(new File(filename));
		imageWriteParam.setCompressionQuality(compressionQuality);
		writer.setOutput(out);
		writer.write((IIOMetadata) null, iioImage, imageWriteParam);
		out.flush();
		out.close();
	}
}
