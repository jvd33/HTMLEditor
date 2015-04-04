package links;

import javax.swing.JTextArea;

import java.util.Iterator;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

/**
 * Algorithm to sort the links and remove all duplicates
 * @author Team Bash-Browns
 *
 */
public class DuplicateSort implements Behavior {
	private TreeMap<String, Integer> tempLinks;
	private List<String> l;
	
	/**
	 * Constructor
	 * @param list is the list of all links found in the buffer
	 */
	public DuplicateSort(List<String> list) { 
		l = list;
		tempLinks = new TreeMap<String, Integer>();
	}
	
	/**
	 * Takes a list of URLs, counts the occurrences
	 * then sorts it according to the number of occurrences of each URL
	 */
	@Override
	public void setLinks(Object o, JTextArea a) {
		a.setEditable(true);
		for(String s : l) {
			int count = 1;
			if(tempLinks.containsKey(s)) { 
				count = tempLinks.get(s);
				tempLinks.put(s, count + 1);
			}
			else { 
				tempLinks.put(s, count);
			}
		}
		/*
		 * this sorts the occurrences of the URL in descending order
		 * and then writes them to the text area
		 */
		ValueComparator comp = new ValueComparator(tempLinks);
		TreeMap<String, Integer> newTable = new TreeMap<String, Integer>(comp);
		newTable.putAll(tempLinks);
		Set<String> set = newTable.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) { 
			String key = it.next();
			int count = tempLinks.get(key);
			a.append(key + ": " + count + "\n");
			
		}
		a.setEditable(false);
		
	}

}

/**
 * Comparator to sort the map by values 
 * @author Team Bash-Browns
 *
 */
class ValueComparator implements Comparator<Object> {
	
	Map<String, Integer> map;
	
	public ValueComparator(Map<String, Integer> unsorted) { 
		map = unsorted;
	}
	
	
	@Override
	public int compare(Object arg0, Object arg1) {
		if(map.get(arg0).equals(map.get(arg1))) { 
			return 1;
		} else { 
			return -(map.get(arg0).compareTo(map.get(arg1)));
		}
	} 
	
}