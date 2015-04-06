package links;
import java.util.List;
import javax.swing.JTextArea;

/**
 * Interface common to all sorting behaviors for the link view
 * @author Team Bash-Browns
 *
 */
public interface Behavior {
	
	/**
	 * Sets the links in the view to reflect the links in the buffer
	 * @param l, the list
	 * @param a the area to edit
	 */
	public void setLinks(List<String> l, JTextArea a);
	
	
}
