package links;

import javax.swing.JTextArea;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.List;


/**
 * Algorithm to sort the links and remove all duplicates
 * @author Team Bash-Browns
 *
 */
public class AlphaSort implements Behavior {
	private TreeMap<String, Integer> tempLinks;
	
	/**
	 * Constructor
	 * @param list is the list of all links found in the buffer
	 */
	public AlphaSort() { 
		tempLinks = new TreeMap<String, Integer>();
	}
	
	/**
	 * Takes a list of URLs, counts the occurrences
	 * then sorts it according to the number of occurrences of each URL
	 */
	@Override
	public void setLinks(List<String> list, JTextArea a) {
		List<String> tempList = new ArrayList<String>(list);
		Collections.sort(tempList);
		a.setEditable(true);
		for(String s : tempList) {
			int count = 1;
			if(tempLinks.containsKey(s)) { 
				count = tempLinks.get(s);
				tempLinks.put(s, count + 1);
			}
			else { 
				tempLinks.put(s, count);
			}
		}
		Set<String> set = tempLinks.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) { 
			String key = it.next();
			int count = tempLinks.get(key);
			a.append(key + ": " + count + "\n");
			
		}
		a.setEditable(false);
		
	}

}