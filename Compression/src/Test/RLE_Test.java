package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Algorithm.RLE;

public class RLE_Test {

	private RLE rle;
	@Before
	public void setUp() throws Exception {		
		rle = new RLE();
	}

	@Test
	public void test_RLE_compression() {
		assertEquals("", rle.compress(""));
		assertEquals("[2]a[2]b[2]c", rle.compress("aabbcc"));
		assertEquals("[3]a[2]b[2]c", rle.compress("aaabbcc"));
		assertEquals("[4]2[5]34[3]5", rle.compress("2222333334555"));
		assertEquals("balalala", rle.compress("balalala"));
		assertEquals("bababababababa", rle.compress("bababababababa"));
	}
	
	@Test
	public void test_RLE_decompression() {
		assertEquals("", rle.decompress(""));
		assertEquals("aabb  |", rle.decompress("[2]a[2]b[2] |"));
		assertEquals("aaabb..", rle.decompress("[3]a[2]b[2]."));
		assertEquals("222233333333333333345555", rle.decompress("[4]2[15]345555"));
		assertEquals("balalala", rle.decompress("balalala"));
		assertEquals("bababababababa", rle.decompress("bababababababa"));
	}
}
