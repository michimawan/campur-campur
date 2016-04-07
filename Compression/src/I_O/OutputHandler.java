package I_O;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OutputHandler {
	private String text;

	public OutputHandler(String text) {
		this.text = text;
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
		File file = new File("data/zipped.br");
		if(!file.exists())
			file.createNewFile();
		else {
			file.delete();
			file.createNewFile();
		}

		FileWriter writer = new FileWriter(file);
		
		writer.write(restore(this.text));
		writer.flush();
		writer.close();
	}
}
