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
		assertEquals("BNN^AA|A", bwt.compress("^BANANA|"));
	}

	@Test
	public void test_generate_all_rotation() {
		assertEquals(8, bwt.rotate("^BANANA|").size());
		assertTrue(bwt.rotate("^BANANA|").contains("^BANANA|"));
		assertTrue(bwt.rotate("^BANANA|").contains("|^BANANA"));
		assertTrue(bwt.rotate("^BANANA|").contains("A|^BANAN"));
		assertTrue(bwt.rotate("^BANANA|").contains("NA|^BANA"));
		assertTrue(bwt.rotate("^BANANA|").contains("ANA|^BAN"));
		assertTrue(bwt.rotate("^BANANA|").contains("NANA|^BA"));
		assertTrue(bwt.rotate("^BANANA|").contains("ANANA|^B"));
		assertTrue(bwt.rotate("^BANANA|").contains("BANANA|^"));
	}

	@Test
	public void test_get_sorted_list() {
		assertEquals("ANANA|^B", bwt.sorts(bwt.rotate("^BANANA|")).get(0));
		assertEquals("|^BANANA", bwt.sorts(bwt.rotate("^BANANA|")).get(7));
	}

	@Test
	public void test_get_last_column() {
		assertEquals("BNN^AA|A", bwt.lastColumn(bwt.sorts(bwt.rotate("^BANANA|"))));
	}

	@Test
	public void test_prepare_empty_list() {
		assertEquals(8, bwt.prepare_list("BANANA^|").size());
		assertEquals(6, bwt.prepare_list("BALALA").size());
	}

	@Test
	public void test_sorted_rows_by_input_last_column() {
		assertEquals(8, bwt.reverse_rotate("BNN^AA|A").size());
		assertEquals("ANANA|^B", bwt.reverse_rotate("BNN^AA|A").get(0));
		assertEquals("ANA|^BAN", bwt.reverse_rotate("BNN^AA|A").get(1));
		assertEquals("A|^BANAN", bwt.reverse_rotate("BNN^AA|A").get(2));
		assertEquals("BANANA|^", bwt.reverse_rotate("BNN^AA|A").get(3));
	}

	@Test
	public void test_get_row_with_special_char() {
		assertEquals("^BANANA|", bwt.getRow(bwt.reverse_rotate("BNN^AA|A")));
	}
}
