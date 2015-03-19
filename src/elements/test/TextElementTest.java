/**
 * 
 */
package elements.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import elements.DocumentElement;
import elements.TextElement;

/**
 * @author Team Bash-Browns
 *
 */
public class TextElementTest {

	/**
	 * Test method for {@link elements.TextElement#print()}.
	 */
	@Test
	public void testPrint() {
		TextElement te = new TextElement("asdf");
		assertEquals(te.print(), "asdf");
	}

	/**
	 * Test method for {@link elements.TextElement#getChildren()}.
	 */
	@Test
	public void testGetChildren() {
		TextElement te = new TextElement("asdf");
		assertEquals(te.getChildren(), new ArrayList<DocumentElement>());
	}

	/**
	 * Test method for {@link elements.TextElement#getEndTag()}.
	 */
	@Test
	public void testGetEndTag() {
		TextElement te = new TextElement("asdf");
		assertNotSame(te.getEndTag(), null);
	}

	/**
	 * Test method for {@link elements.TextElement#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		assertEquals(new TextElement("asdf"), new TextElement("asdf"));
		assertNotSame(new TextElement("asdf"), new TextElement("fdsa"));
	}

}
