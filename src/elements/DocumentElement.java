package elements;
import java.util.List;

/**
 * 
 */

/**
 * Enables the composite pattern to be more useful for our TextElement and
 * HTMLTag classes
 * @author Team Bash-Browns
 */
public interface DocumentElement {
	/**
	 * Should replace the default toString
	 * TextElement should return the text it contains
	 * HTMLTag should either:
	 * 		return its tags and the elements it contains
	 * 		or return the start tag of the HTMLTag if it is collapsed
	 * 
	 * @return The string that the element should represent in the editor
	 */
	public String print();
	
	
	/**
	 * Used in verifying whether or not things are well-formed
	 * @return List of children the DE has in its tree
	 */
	public List<DocumentElement> getChildren();
	
	
	/**
	 * Used in verifying whether or not things are well-formed
	 * @return Either the end tag (if correct) or a null value (if incorrect)
	 */
	public String getEndTag();
	
}