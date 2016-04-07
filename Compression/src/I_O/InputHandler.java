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
	
	public String getText(String action) throws FileNotFoundException {
		if(this.text == null) {
			if(action.equals("compress"))
				this.text = readText(action) + "|";
			else
				this.text = readText(action);
		}
		
		return this.text;
	}
	
	private String readText(String action) throws FileNotFoundException {
		File f = new File(this.path);
		Scanner sc = new Scanner(f);
		
		String tmp = "";
		while(sc.hasNextLine()) {
			tmp += sc.nextLine() + (char) 210;
		}
		sc.close();
		
		if(action.equals("compress"))
			tmp = doClean(tmp);
		
		return tmp;
	}
		
	public String doClean(String text) {
		return text.replace("|", "");
	}
}
