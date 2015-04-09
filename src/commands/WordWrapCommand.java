package commands;
import javax.swing.JTextArea;

/**
 * Command to toggle the word-wrap functionality of the program
 * @author Team Bash-Browns
 *
 */
public class WordWrapCommand implements Command{
	JTextArea textArea;
	
	/**
	 * Just toggles the word wrap setting
	 * @param buffer_view
	 */
	public WordWrapCommand(JTextArea text_area){
		textArea = text_area;
	}
	
	@Override
	public void execute() {
		textArea.setLineWrap(!textArea.getLineWrap());
	}



}
