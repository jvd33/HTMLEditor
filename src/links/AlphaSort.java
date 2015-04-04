package links;

import java.util.List;

import javax.swing.JTextArea;

import java.util.Collections;
import java.util.ArrayList;

/**
 * Algorithm to sort the link view alphabetically
 * @author Team Bash-Browns
 *
 */
public class AlphaSort implements Behavior {
	
	private List<String> list;
	private List<String> tempList;
	
	public AlphaSort(List<String> l) { 
		list = l;
	}


	/**
	 * Sorts the list alphabetically and updates the text area
	 */
	@Override
	public void setLinks(Object o, JTextArea a) {
		tempList = new ArrayList<String>(list);
		Collections.sort(tempList);
		a.setEditable(true);
		for(String s : tempList) { 
			a.append(s + "\n");
		}
		a.setEditable(false);
	}
}