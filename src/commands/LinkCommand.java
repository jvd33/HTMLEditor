package commands;


import links.LinkView;
import buffer.Buffer;


/**
 * 
 * @author Team Bash-Browns
 * Command to load and display a list of all URLs found in the active buffer.
 */
public class LinkCommand implements Command {
	private Buffer buff;
	/**
	 * Constructor
	 * @param b, the active buffer
	 */
	public LinkCommand(Buffer b) { 
		buff = b;
	}
	
	/**
	 * Creates and displays a new window showing all the links
	 * that were found in the editor when the button was clicked
	 */
	public void execute() { 
		new LinkView(buff);
	}
}
