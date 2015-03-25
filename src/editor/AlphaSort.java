package editor;

import java.util.List;
import javax.swing.JTextArea;
import java.util.Collections;

/**
 * Algorithm to sort the link view alphabetically
 * @author Team Bash-Browns
 *
 */
public class AlphaSort implements Behavior {
	
	private List<String> list;
	
	public AlphaSort(List<String> l) { 
		list = l;
	}


	/**
	 * Sorts the list alphabetically and updates the text area
	 */
	@Override
	public void setLinks(Object o, JTextArea a) {
		Collections.sort(list);
		a.setEditable(true);
		for(String s : list) { 
			a.append(s + "\n");
		}
		a.setEditable(false);
	}
}