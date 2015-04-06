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
	
	private List<String> list;
	
	/**
	 * constructor
	 * @param l- the list of all strings in the text
	 */
	public OccurrenceSort(List<String> l) { 
		list = l;
	}
	
	/**
	 * Sets the links in the text area
	 */
	@Override
	public void setLinks(Object o, JTextArea a) {
		a.setEditable(true);
		for(String s : list) { 
			a.append(s + "\n");
		}
		a.setEditable(false);
		
	}

}
