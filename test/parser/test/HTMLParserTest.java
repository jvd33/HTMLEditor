/**
 * 
 */
package parser.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import elements.HTMLTag;
import elements.TextElement;

import parser.HTMLParser;

/**
 * @author Team Bash-Browns
 *
 */
public class HTMLParserTest {
	
	private String inputString;
	private HTMLParser testP;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		inputString = "<html>\n<p></p>\n<div>text</div>\n</html>";
		testP = new HTMLParser(inputString);
	}

	/**
	 * Test method for {@link parser.HTMLParser#parse()}.
	 */
	@Test
	public final void testParse1() {
		assertTrue(testP.parse().print().equals("<html>\n<p></p>\n<div>text</div>\n</html>"));
	}
	
	/**
	 * Test method for {@link parser.HTMLParser#parse()}.
	 */
	@Test
	public final void testParse2() {
		testP = new HTMLParser("<html></html>");
		assertTrue(testP.parse().print().equals("<html></html>"));
	}
	
	/**
	 * Test method for {@link parser.HTMLParser#parse()}.
	 */
	@Test
	public final void testParse3() {
		testP = new HTMLParser("<html><div></div></html>");
		assertTrue(testP.parse().print().equals("<html><div></div></html>"));
	}

	/**
	 * Test method for {@link parser.HTMLParser#parse()}.
	 */
	@Test
	public final void testParse4() {
		testP = new HTMLParser("<html><div>\n</div>text</html>");
		assertTrue(testP.parse().print().equals("<html><div>\n</div>text</html>"));
	}
}
