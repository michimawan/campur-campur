package data;

public class TextManager implements DataObject {

	private String text;
	private String cleanedText;
	private int [] properties;
	public static final char specialChar = (char) 211;
	
	public TextManager(String text) {
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
			String number = this.text.substring(firstFoundSpecialChar + 1, secondFoundSpecialChar);
			
			int val = (new Integer (number)).intValue();
			this.properties = new int [] {val};
		} else 
			this.properties = new int [] {-1};
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
		this.properties[0] = properties[0];
	}

}
