package Test;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import I_O.OutputHandler;

public class OutputHandler_Test {
	OutputHandler oh;
	@Before
	public void setUp() throws Exception {
		oh = new OutputHandler("whatever");
	}
	
	@Test
	public void test_no_special_char()
	{
		char tmp = (char) 210;
		String text = tmp + "wha" + tmp + "tever" + tmp + tmp + tmp;
		assertEquals(-1, oh.restore(text).indexOf(tmp));
		
		assertTrue(oh.restore(text).contains("\r\n"));
		
		String resultText = oh.restore(text);
		Pattern regex = Pattern.compile("\r\n");
		Matcher matcher = regex.matcher(resultText);
		
		int n = 0;
		while(matcher.find())
			n++;
		assertEquals(5, n);
	}
}
