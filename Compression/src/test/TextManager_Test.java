package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import data.TextManager;

@RunWith(value = Parameterized.class)
public class TextManager_Test {

	private TextManager tm;
	private String expectedText;
	private int [] expectedProperties;
	private String text;
	
	public TextManager_Test(String text, String expectedText, int [] expectedProperties) {
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
					{ "whatever" + specialChar + "100" + specialChar, "whatever", new int [] {100} }, 
					{ "[22][33]" + specialChar + "200" + specialChar, "[22][33]", new int [] {200} },
					{ "bobobo[21]+kskdi" + specialChar + "300" + specialChar, "bobobo[21]+kskdi", new int [] {300} },
					{ "-1234whatever(ieuh)" + specialChar + "400" + specialChar, "-1234whatever(ieuh)", new int [] {400} },
					{ "" + specialChar + "" + specialChar, "", new int [] {-1} },
					{ "", "", new int [] {-1} }
				}
		);
	}

	@Test
	public void test_getText() {
		tm = new TextManager(this.text);
		assertEquals(this.expectedText, this.tm.getText());
	}

	@Test
	public void test_getText_when_text_is_set() {
		tm = new TextManager(this.text);
		tm.setText("whatever");
		assertEquals("whatever", this.tm.getText());
	}
	
	@Test
	public void test_getProperties() {
		tm = new TextManager(this.text);
		assertEquals(this.expectedProperties[0], this.tm.getProperties()[0]);
	}
	
	@Test
	public void test_getProperties_when_properties_set_with_one_index() {
		tm = new TextManager(this.text);
		tm.setProperties(new int [] {10});
		assertEquals(10, this.tm.getProperties()[0]);
	}
	
	@Test
	public void test_getProperties_when_properties_set_with_more_than_one_index() {
		tm = new TextManager(this.text);
		tm.setProperties(new int [] {10, 20});
		assertEquals(10, this.tm.getProperties()[0]);
		assertEquals(1, this.tm.getProperties().length);
	}
}