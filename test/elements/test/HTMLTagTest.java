package elements.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import elements.HTMLTag;
import elements.TextElement;

/**
 * JUnit test file for HTMLTag
 * @author Team Bash-Browns
 *
 */
public class HTMLTagTest {
	private HTMLTag rootHtml;
	private HTMLTag divTag;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rootHtml = new HTMLTag("<html>", null);
		rootHtml.setEndTag("</html>");
		divTag = new HTMLTag("<div>", null);
		divTag.setEndTag("</div>");
	}

	/**
	 * Test method for {@link elements.HTMLTag#addChild(elements.DocumentElement)}.
	 */
	@Test
	public final void testAddChild() {
		assertTrue(rootHtml.getChildren().size()==0);
		HTMLTag head = new HTMLTag("<head>", rootHtml);
		rootHtml.addChild(head);
		assertTrue(rootHtml.getChildren().contains(head));
	}

	/**
	 * Test method for {@link elements.HTMLTag#setEndTag(java.lang.String)}.
	 */
	@Test
	public final void testSetEndTag() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link elements.HTMLTag#getStartTag()}.
	 */
	@Test
	public final void testGetStartTag() {
		assertTrue(rootHtml.getStartTag().equals("<html>"));
	}

	/**
	 * Test method for {@link elements.HTMLTag#getEndTag()}.
	 */
	@Test
	public final void testGetEndTag() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link elements.HTMLTag#toString()}.
	 */
	@Test
	public final void testToString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link elements.HTMLTag#getParent()}.
	 */
	@Test
	public final void testGetParent() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link elements.HTMLTag#setParent(elements.HTMLTag)}.
	 */
	@Test
	public final void testSetParent() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link elements.HTMLTag#print()}.
	 */
	@Test
	public final void testPrint1() {
		assertTrue(rootHtml.print().equals("<html></html>"));
		rootHtml.addChild(divTag);
		assertTrue(rootHtml.print().equals("<html><div></div></html>"));
	}
	
	/**
	 * Test method for {@link elements.HTMLTag#print()}.
	 */
	@Test
	public final void testPrint2() {
		HTMLTag pTag = new HTMLTag("<p>", rootHtml);
		pTag.setEndTag("</p>");
		divTag.addChild(new TextElement("text"));
		rootHtml.addChild(new TextElement("\n"));
		rootHtml.addChild(pTag);
		rootHtml.addChild(new TextElement("\n"));
		rootHtml.addChild(divTag);
		rootHtml.addChild(new TextElement("\n"));
		assertTrue(rootHtml.print().equals("<html>\n<p></p>\n<div>text</div>\n</html>"));
	}

	/**
	 * Test method for {@link elements.HTMLTag#equals(java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObject() {
		HTMLTag ht = new HTMLTag("<html>", null);
		ht.setEndTag("</html>");
		assertTrue(rootHtml.equals(ht));
		assertFalse(rootHtml.equals(new HTMLTag("<div>", rootHtml)));
		assertFalse(rootHtml.equals(new HTMLTag("<div>", null)));
		
	}
}
