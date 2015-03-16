import java.util.List;

/**
 * 
 */

/**
 * Represents a non-HTML tag in the buffer html tree
 * Leaf node of a composite pattern
 * @author Team Bash-Browns
 *
 */
public class TextElement implements DocumentElement {
	private String text;
	/**
	 * Constructor for TextElement
	 * 
	 * @param s The string that the element represents
	 */
	TextElement(String s){
		System.out.println("Created text element with " + s);
		text = s;
	}
	
	@Override
	public String print(){
		return text;
	}

	@Override
	public List<DocumentElement> getChildren() {
		return null;
	}

	@Override
	public String getEndTag() {
		// Returns a non-null value indicating that the non-tags are well-formed
		return "";
	}
}
