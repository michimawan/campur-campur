package Algorithm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RLE {
	private String text;

	public RLE()
	{}

	public RLE(String text)
	{
		this.text = text;
	}

	public String compress()
	{
		return doEncode(this.text);
	}

	public String compress(String text)
	{
		return doEncode(text);
	}

	public String decompress()
	{
		return doDecode(this.text);
	}

	public String decompress(String text)
	{
		return doDecode(text);
	}

	private String doEncode(String text)
	{
		if(text.length() == 0)
			return "";
		String tmp = "";
		int sum = 1;
		String c = text.substring(0, 1);
		for(int i=1; i<text.length(); i++) {
			if(c.equals(text.substring(i,i+1)))
				sum++;
			else {
				if(sum > 1)
					tmp += "[" + sum + "]" + c;
				else
					tmp += c;
				c = text.substring(i,i+1);
				sum = 1;
			}
		}
		if(sum > 1)
			tmp += "[" + sum + "]" + c;
		else
			tmp += c;

		return tmp;
	}

	private String doDecode(String text) {
		if(text.length() == 0)
			return "";
		Pattern regex = Pattern.compile(".([\\d])](\\w)");
		Matcher matcher = regex.matcher(text);

		while(matcher.find()) {
			String tmp = "";
			int n = Integer.parseInt(matcher.group(1));
			while (n > 0) {
				tmp += matcher.group(2);
				n--;
			}
			text = text.replace(matcher.group(), tmp);
		}

		return text;
	}
}
