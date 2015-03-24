package commands;
import parser.Checker;
import buffer.Buffer;
import java.util.List;
import javax.swing.*;

/**
 * 
 * @author Team Bash-Browns
 * Command to load and display a list of all URLs found in the active buffer.
 */
public class LinkCommand implements Command {
	private Checker c;
	private List<String> links;
	private JFrame view;
	
	/**
	 * Constructor
	 * @param b, the active buffer
	 */
	public LinkCommand(Buffer b) { 
		c = new Checker(b);
		links = c.getLinks();
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
			area.append(s + "\n");
		}
		area.setEditable(false);
		view.add(area);
		
	}
}
