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
	public final void testParse() {
		assertTrue(testP.parse().print().equals("<html>\n<p></p>\n<div>text</div>\n</html>"));
	}

}
