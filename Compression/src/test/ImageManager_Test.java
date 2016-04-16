package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import data.ImageManager;

@RunWith(value = Parameterized.class)
public class ImageManager_Test {

	private ImageManager im;
	private String expectedText;
	private int [] expectedProperties;
	private String text;
	
	public ImageManager_Test(String text, String expectedText, int [] expectedProperties) {
		this.text = text;
		this.expectedText = expectedText;
		this.expectedProperties = expectedProperties;
	}

	// Declares parameters here
	@Parameters(name = "{index}: {0} {1} {2}")
	public static Iterable<Object[]> data1() {
		char specialChar = (char) 211;
		
		return Arrays.asList(new Object[][] 
				{ 
					{ "whatever" + specialChar + "1x100x100" + specialChar, "whatever", new int[]{1, 100, 100} },
					{ "[22][33]" + specialChar + "2000x200x100" + specialChar, "[22][33]", new int[]{2000, 200, 100} },
					{ "bobobo[21]+kskdi" + specialChar + "10x6000x5000" + specialChar, "bobobo[21]+kskdi", new int[]{10, 6000, 5000} },
					{ "-1234whatever(ieuh)" + specialChar + "0x1x1" + specialChar, "-1234whatever(ieuh)", new int[]{0, 1, 1} },
					{ "" + specialChar + "" + specialChar, "", new int[]{ -1, -1, -1 } }
				}
		);
	}

	@Test
	public void test_getText() {
		im = new ImageManager(this.text);
		assertEquals(this.expectedText, this.im.getText());
	}

	@Test
	public void test_getText_when_text_is_set() {
		im = new ImageManager(this.text);
		im.setText("whatever");
		assertEquals("whatever", this.im.getText());
	}
	
	@Test
	public void test_getProperties() {
		im = new ImageManager(this.text);
		assertEquals(this.expectedProperties[0], this.im.getProperties()[0]);
		assertEquals(this.expectedProperties[1], this.im.getProperties()[1]);
		assertEquals(this.expectedProperties[2], this.im.getProperties()[2]);
		assertEquals(3, this.im.getProperties().length);
	}
	
	@Test
	public void test_getProperties_when_properties_set_with_one_index() {
		im = new ImageManager(this.text);
		im.setProperties(new int [] {10});
		assertEquals(10, this.im.getProperties()[0]);
		assertEquals(this.expectedProperties[1], this.im.getProperties()[1]);
		assertEquals(this.expectedProperties[2], this.im.getProperties()[2]);
	}
	
	@Test
	public void test_getProperties_when_properties_set_with_more_than_one_index() {
		im = new ImageManager(this.text);
		im.setProperties(new int [] {10, 20, 30, 40});
		assertEquals(10, this.im.getProperties()[0]);
		assertEquals(20, this.im.getProperties()[1]);
		assertEquals(30, this.im.getProperties()[2]);
		assertEquals(3, this.im.getProperties().length);
	}
}
