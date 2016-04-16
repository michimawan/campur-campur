package data;

import sun.java2d.loops.FillParallelogram;

public class ImageManager implements DataObject {

	private String text;
	private String cleanedText;
	private int [] properties;
	public static final char specialChar = (char) 211;
	
	public ImageManager(String text) {
		this.text = text;
		fillText();
		fillProperties();
	}
	
	private void fillText() {
		if(this.text.indexOf(specialChar) > -1) { 
			this.cleanedText = new String(this.text.substring(0, this.text.indexOf(specialChar)));
		} else {
			this.cleanedText =  new String(this.text);
		}
	}
	
	private void fillProperties() {
		int firstFoundSpecialChar = this.text.indexOf(specialChar);
		if(firstFoundSpecialChar > 0) {
			int secondFoundSpecialChar = this.text.indexOf(specialChar, firstFoundSpecialChar + 1);
			String numbers = this.text.substring(firstFoundSpecialChar + 1, secondFoundSpecialChar);
			String[] numberArray = numbers.split("x");

			this.properties = new int[] {
					(new Integer(numberArray[0])).intValue(), 
					(new Integer(numberArray[1])).intValue(),
					(new Integer(numberArray[2])).intValue(),
			};
		} else {
			this.properties = new int[]{-1, -1, -1};
		}
	}
	
	@Override
	public String getText() {
		return this.cleanedText;
	}

	@Override
	public int [] getProperties() {
		return this.properties;
	}

	@Override
	public void setText(String text) {
		this.cleanedText = text;
	}

	@Override
	public void setProperties(int[] properties) {
		for(int i=0; i<properties.length && i < 3; i++) {
			this.properties[i] = properties[i];
		}
	}

}
