package commands;
import parser.Checker;
import buffer.Buffer;
import java.util.List;
import javax.swing.*;
import java.util.Hashtable;
import java.util.Enumeration;


/**
 * 
 * @author Team Bash-Browns
 * Command to load and display a list of all URLs found in the active buffer.
 */
public class LinkCommand implements Command {
	private Checker c;
	private List<String> links;
	private JFrame view;
	private Hashtable<String, Integer> tempLinks;
	/**
	 * Constructor
	 * @param b, the active buffer
	 */
	public LinkCommand(Buffer b) { 
		c = new Checker(b);
		links = c.getLinks();
		tempLinks = new Hashtable<String, Integer>();
	}
	
	/**
	 * Creates and displays a new window showing all the links
	 * that were found in the editor when the button was clicked
	 */
	public void execute() { 
		view = new JFrame("Link View");
		view.setVisible(true);
		view.setSize(500, 500);
		JTextArea area = new JTextArea();
		for(String s : links) {
			int count = 1;
			//empLinks.put(s, count);
			if(tempLinks.containsKey(s)) { 
				count = tempLinks.get(s);
				tempLinks.put(s, count + 1);
			}
			else { 
				tempLinks.put(s, count);
			}
		}
		Enumeration<String> it = tempLinks.keys();
		while(it.hasMoreElements()) { 
			String key = it.nextElement();
			int count = tempLinks.get(key);
			area.append(key + ": " + count + "\n");
			
		}
		area.setEditable(false);
		view.add(area);
		
	}
}
