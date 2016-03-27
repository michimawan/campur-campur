package Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BWT {
	private String text;
	public BWT() {}

	public BWT(String text) {
		this.text = text;
	}

	public String compress() {
		return doEncode(this.text);
	}

	public String compress(String text) {
		return doEncode(text);
	}

	public String doEncode(String text) {
		if(text.length() == 0)
			return "";

		return lastColumn(sorts(rotate(text)));
	}

	public List<String> rotate(String text) {
		List<String> ls = new ArrayList<String>();

		for(int i=0; i<text.length(); i++) {
			ls.add(text.substring(text.length() - i, text.length()) + text.substring(0, text.length() - i));
		}

		return ls;
	}

	public List<String> sorts(List<String> ls) {
		Collections.sort(ls);
		return ls;
	}

	public String lastColumn(List<String> ls) {
		String tmp = "";
		for(String s : ls) {
			tmp += s.charAt(s.length() - 1);
		}
		return tmp;
	}

	public String getRow(List<String> ls) {
		int length = ls.get(0).length();
		for(String s : ls) {
			if(s.indexOf("|") == length - 1)
				return s;
		}

		return "";
	}

	public List<String> reverse_rotate(String text) {
		List<String> ls = prepare_list(text);

		int n = text.length();
		while(n > 0) {
			for(int i=0; i<text.length(); i++) {
				ls.set(i, text.substring(i, i+1) + ls.get(i));
			}

			System.out.println("not sorted: " + ls.toString());
			ls = sorts(ls);
			n--;
			System.out.println("sorted: " + ls.toString());
		}
		System.out.println("final: " + ls.toString());
		return ls;
	}

	public List<String> prepare_list(String text) {
		List<String> ls = new ArrayList<String>();

		for(int i=0; i<text.length(); i++) {
			ls.add("");
		}
		return ls;
	}
}
