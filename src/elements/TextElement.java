package elements;
import java.util.ArrayList;
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
	private List children;
	private String text;
	/**
	 * Constructor for TextElement
	 * 
	 * @param s The string that the element represents
	 */
	public TextElement(String s){
		//System.out.println("Created text element with " + s);
		text = s;
		children = new ArrayList<DocumentElement>();
	}
	
	@Override
	public String print(){
		return text;
	}

	@Override
	public List<DocumentElement> getChildren() {
		return children;
	}

	@Override
	public String getEndTag() {
		// Returns a non-null value indicating that the non-tags are well-formed
		return "";
	}
	public String toString() { 
		return text;
	}
	@Override
	public boolean equals(Object obj){
		if(obj instanceof TextElement){
			if(((TextElement) obj).text.equals(this.text)){
				return true;
			}
		}
		return false;
	}
}