package parser.test;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserIterator;


public class ParserIteratorTest {

	@Test
	public void test1() {
		ParserIterator cut = new ParserIterator("<html></html>");
		assertTrue(cut.hasNext());
		assertEquals("<html>", cut.next());
		assertTrue(cut.hasNext());
		assertEquals("</html>", cut.next());
	}

	@Test
	public void test1_with_comment() {
		ParserIterator cut = new ParserIterator("<html><!-- comment --></html>");
		assertTrue(cut.hasNext());
		assertEquals("<html>", cut.next());
		assertTrue(cut.hasNext());
		assertEquals("</html>", cut.next());
	}

	@Test
	public void test2() {
		ParserIterator cut = new ParserIterator("<html><head>asdf</head></html>");
		assertTrue(cut.hasNext());
		assertEquals("<html>", cut.next());
		assertTrue(cut.hasNext());
		assertEquals("<head>", cut.next());
		assertTrue(cut.hasNext());
		assertEquals("asdf", cut.next());
		assertTrue(cut.hasNext());
		assertEquals("</head>", cut.next());
		assertTrue(cut.hasNext());
		assertEquals("</html>", cut.next());
		assertFalse(cut.hasNext());
	}

	@Test
	public void test1_bad() {
		ParserIterator cut = new ParserIterator("<html>");
		assertTrue(cut.hasNext());
		assertEquals("<html>", cut.next());
	}
	
	@Test
	public void testLinks() {
		String input = "<html><a href=\"https://www.google.com\"</a></html>";
		ParserIterator URL = new ParserIterator(input);
		assertEquals("https://www.google.com", URL.getLinks().get(0));
	}

}
