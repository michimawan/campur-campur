package I_O;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputHandler {
	private String path;
	private String text;

	public InputHandler(String path) {
		this.path = path;
	}

	public String getText() throws FileNotFoundException {
		if(this.text == null) {
			this.text = readText();
		}

		return this.text + "|";
	}

	private String readText() throws FileNotFoundException {
		File f = new File(this.path);
		Scanner sc = new Scanner(f);

		String tmp = "";
		while(sc.hasNextLine()) {
			tmp += doClean(sc.nextLine());
		}

		sc.close();
		return tmp;
	}

	public String doClean(String text) {
		return text.replace("|", "");
	}
}
