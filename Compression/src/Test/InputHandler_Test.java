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
	InputHandler ih;
	private File f;
	private Scanner sc;
	@Before
	public void setUp() throws Exception {
		f = new File(path);
		sc = new Scanner(f);
		ih = new InputHandler(path);
	}

	@Test
	public void test_input_as_text() throws FileNotFoundException {
		String tmp = "";
		while(sc.hasNextLine()) {
			tmp += ih.doClean(sc.nextLine());
		}

		assertEquals(ih.getText(), tmp + "|");
	}

	@Test(expected=FileNotFoundException.class)
	public void test_input_text_throw_FileNotFoundException() throws FileNotFoundException {
		InputHandler ih = new InputHandler("data.txt");
		ih.getText();
	}

	@Test
	public void test_no_special_char()
	{
		assertFalse(ih.doClean("").contains("|"));
		assertFalse(ih.doClean("|").contains("|"));
		assertFalse(ih.doClean("||||").contains("|"));
		assertFalse(ih.doClean("balalala|\balala|balala").contains("|"));
		assertFalse(ih.doClean("balalal|||balalala||").contains("|"));
	}
}
