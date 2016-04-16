package io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import data.ImageManager;
import data.TextManager;
import data.DataObject;

public class InputHandler {
	protected String path;
	protected String toDo;

	public InputHandler(String path, String toDo) {
		this.path = path;
		this.toDo = toDo;
	}
	
	private String readText(File f) throws FileNotFoundException {
		Scanner sc = new Scanner(f);

		String tmp = "";
		while (sc.hasNextLine()) {
			tmp += sc.nextLine() + (char) 210;
		}
		sc.close();

		return tmp;
	}
	
	private String readImageToByteArrayString(File f) throws IOException {
		byte[] fileContent = Files.readAllBytes(f.toPath());
		String str = Arrays.toString(fileContent);
		// System.out.println();
		return str;
	}

	private String readImage(File f) throws IOException {
		BufferedImage image = ImageIO.read(f);

		int width = image.getWidth();
		int height = image.getHeight();

		int[][] result = new int[height][width];
		String tmp = "";

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				result[row][col] = image.getRGB(col, row);
				String hex = "#" + Integer.toHexString(result[row][col]).substring(2);
				tmp += hex;
			}
		}

		return tmp;
	}

	public DataObject getObject() throws FileNotFoundException {
		Pattern regex = Pattern.compile("([^\\s]+(\\.(?i)(br))$)");
		Matcher matcher = regex.matcher(this.path);

		File f = new File(this.path);
		if (matcher.matches()) {
			return readDecompressed(f);
		} else {
			System.out.println("do compress");
			return readCompressed(f);

		}
	}

	private DataObject readCompressed(File f) throws FileNotFoundException {
		Pattern regex = Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|bmp))$)");
		Matcher matcher = regex.matcher(this.path);

		if (matcher.matches()) {
			try {
				System.out.println("read image");
				String tmp = readImageToByteArrayString(f);
				return new ImageManager(tmp);
			} catch (IOException e) {
				throw new FileNotFoundException();
			}
		} else {
			String tmp = readText(f);
			return new TextManager(tmp);
		}
	}
	
	private DataObject readDecompressed(File f) throws FileNotFoundException {
		Scanner s = new Scanner(f);
		
		int indexOfSpecialCharForText = s.nextLine().indexOf((char) 210);
		s.close();
		if(indexOfSpecialCharForText > -1) {
			String tmp = readOneLineText(f);
			return new TextManager(tmp);
		} else {
			String tmp = readOneLineText(f);
			return new ImageManager(tmp);
		}
	}
	
	private String readOneLineText(File f) throws FileNotFoundException {
		Scanner s = new Scanner(f);
		String tmp = "";
		while(s.hasNextLine()) {
			tmp += s.nextLine();
		}
		s.close();
		return tmp;
	}
}
