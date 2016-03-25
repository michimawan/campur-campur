package Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import I_O.InputHandler;

public class InputHandler_Test {
	private String path = "data/data.txt";
	private File f;
	private Scanner sc;
	@Before
	public void setUp() throws Exception {
		f = new File(path);
		sc = new Scanner(f);
	}

	@Test
	public void test_remove_all_number_from_text() {
		// fail("Not yet implemented");
	}
	
	
	@Test
	public void test_input_as_text() throws FileNotFoundException {
		InputHandler ih = new InputHandler(path);
		String tmp = "";
		while(sc.hasNextLine()) {
			tmp += sc.nextLine();
		}
		
		assertEquals(ih.getText(), tmp); 
	}
	
	@Test(expected=FileNotFoundException.class)
	public void test_input_text_throw_FileNotFoundException() throws FileNotFoundException {
		InputHandler ih = new InputHandler("data.txt");
		ih.getText();
	}
}
