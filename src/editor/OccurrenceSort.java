package editor;

import javax.swing.JTextArea;
import java.util.List;

public class OccurrenceSort implements Behavior {
	
	private List<String> list;
	
	public OccurrenceSort(List<String> l) { 
		list = l;
	}
	@Override
	public void setLinks(Object o, JTextArea a) {
		a.setEditable(true);
		for(String s : list) { 
			a.append(s + "\n");
		}
		a.setEditable(false);
		
	}

}
