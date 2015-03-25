package editor;
import javax.swing.JTextArea;

/**
 * Interface common to all sorting behaviors for the link view
 * @author Team Bash-Browns
 *
 */

public interface Behavior {
	
	/**
	 * Sets the links in the view to reflect the links in the buffer
	 * @param o
	 * @param a the area to edit
	 */
	public void setLinks(Object o, JTextArea a);
	
	
}
