package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Algorithm.BWT;
import Algorithm.RLE;

public class BWT_Test {

	private BWT bwt;
	@Before
	public void setUp() throws Exception {
		bwt = new BWT();
	}	
	
	@Test
	public void test_compress() {
		assertEquals("", bwt.compress(""));
		assertEquals("|drcraaaabba", bwt.compress("abracadabra|"));
		assertEquals("BNN^AA|A", bwt.compress("^BANANA|"));
		assertEquals("n|ordsooccimpssee", bwt.compress("compresssioncode|"));
	}
	
	@Test
	public void test_get_right_x_index_before() {
		assertEquals(0, bwt.hasXIndexBefore("AAABNN^|", 6, '^'));
		assertEquals(0, bwt.hasXIndexBefore("AAABNN^|", 0, 'A'));
		assertEquals(1, bwt.hasXIndexBefore("AAABNN^|", 1, 'A'));
		assertEquals(2, bwt.hasXIndexBefore("AAABNN^|", 2, 'A'));
	}
	
	@Test
	public void test_get_next_added_char() {
		assertEquals(3, bwt.getNextAddedIndex("BNN^AA|A", 0, '^'));
		assertEquals(0, bwt.getNextAddedIndex("BNN^AA|A", 0, 'B'));
		assertEquals(4, bwt.getNextAddedIndex("BNN^AA|A", 0, 'A'));
		assertEquals(5, bwt.getNextAddedIndex("BNN^AA|A", 1, 'A'));
		assertEquals(7, bwt.getNextAddedIndex("BNN^AA|A", 2, 'A'));
	}
	
	@Test
	public void test_decode_bwt() {
		assertEquals("^BANANA|", bwt.decompress("BNN^AA|A"));
		assertEquals("abracadabra|", bwt.decompress("|drcraaaabba"));
		assertEquals("compresssioncode|", bwt.decompress("n|ordsooccimpssee"));
	}
}
