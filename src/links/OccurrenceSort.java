package links;

import javax.swing.JTextArea;

import java.util.List;

/**
 * Default sorting behavior, sorts the links by their occurrence
 * in the text.
 * @author Team Bash-Browns
 *
 */
public class OccurrenceSort implements Behavior {
	
	/**
	 * constructor
	 * @param l- the list of all strings in the text
	 */
	public OccurrenceSort() { 
		
	}
	
	/**
	 * Sets the links in the text area
	 */
	@Override
	public void setLinks(List<String> l, JTextArea a) {
		a.setEditable(true);
		for(String s : l) { 
			a.append(s + "\n");
		}
		a.setEditable(false);
		
	}

}
