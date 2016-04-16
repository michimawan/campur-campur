package io;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import data.TextManager;
import data.DataObject;
import data.ImageManager;

public class OutputHandler {
	private String text;
	private String action;
	private DataObject vo;

	public OutputHandler(DataObject vo, String action) {
		this.vo = vo;
		this.action = action;
	}

	public String restore(String text) {
		char remove = (char) 210;

		String tmp = "";
		int startIndex = 0;
		while (true) {
			int found = text.indexOf(remove, startIndex);

			if (found >= 0) {
				tmp += text.substring(startIndex, found) + "\r\n";
				startIndex = found + 1;
			} else
				break;
		}
		if (startIndex != text.length())
			tmp += text.substring(startIndex + 1);

		return tmp;
	}

	public void store() throws IOException {
		File file = getFileNameFromActionData(); 
		String toWrite = "";
		
		if(this.action.equals("compress")) {
			toWrite = getCompressedContentFile(toWrite);
			writeTextFile(file, toWrite);
		} 
		else if(this.action.equals("decompress")) {
			if(vo.getClass().equals(ImageManager.class)) {
				writeImageFile(file);
			} else {
				// not like this actually
				toWrite = getDecompressedContentFile(toWrite);
				writeTextFile(file, toWrite);
			}
		}
	
	}
	
	private void writeImageFile(File f) throws IOException {
		if(!f.exists())
			f.createNewFile();
		else {
			f.delete();
			f.createNewFile();
		}

		BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(stringToByteArray()));
		ImageIO.write(bufferedImage, "jpg", f);
	}
	
	private byte[] stringToByteArray() {
		String[] byteValues = vo.getText().substring(1, vo.getText().length() - 1).split(",");
		byte[] bytes = new byte[byteValues.length];

		for (int i=0, len=bytes.length; i<len; i++) {
		   bytes[i] = Byte.parseByte(byteValues[i].trim());     
		}
		return bytes;
	}
	
	private void writeTextFile(File file, String toWrite) throws IOException {
		if(!file.exists())
			file.createNewFile();
		else {
			file.delete();
			file.createNewFile();
		}

		FileWriter writer = new FileWriter(file);
		
		writer.write(toWrite);
		writer.flush();
		writer.close();		
	}
	
	private String getCompressedContentFile(String toWrite) {
		toWrite = getCompressedTextFromData(toWrite);
		toWrite = addCompressionInfo(toWrite);
		return toWrite;
	}
	
	private String getDecompressedContentFile(String toWrite) {
		toWrite = getDecompressedTextFromData(toWrite);
		toWrite = addCompressionInfo(toWrite);
		return toWrite;
	}
	
	private File getFileNameFromActionData() {
		if(this.action.equals("decompress") && vo.getClass().equals(ImageManager.class))
			return new File("data/image_result.jpg");
		else if(this.action.equals("decompress"))
			return new File("data/text_result.txt");
		else
			return new File("data/zipped.br");
	}
	
	private String getCompressedTextFromData(String toWrite) {
		if(vo.getClass().equals(TextManager.class)) {
			toWrite = vo.getText();
		} else {
			toWrite = vo.getText();
		}
		return toWrite;
	}
	
	private String getDecompressedTextFromData(String toWrite) {
		if(vo.getClass().equals(TextManager.class)) {
			toWrite = restore(vo.getText());
		} else {
			toWrite = vo.getText();
		}
		return toWrite;
	}
	
	private String addCompressionInfo(String toWrite) {
		if(vo.getProperties()[0] > -1)
			toWrite = insertProperties(toWrite, vo.getProperties());
		
		return toWrite;
	}
	
	private String insertProperties(String text, int [] properties) {
		text += (char) 211;
		
		text += properties[0];
		
		for(int i=1; i<properties.length && i < 3; i++) {
			System.out.println("here");
			text += "x" + properties[i];
		}
		
		text += (char) 211;
		
		return text;
	}
}
