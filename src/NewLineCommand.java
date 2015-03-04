import javax.swing.JTextArea;

/**
 * 
 */

/**
 * This class should work on auto-indenting
 * When the new line character is pressed, this should be called
 * It will check the line before it to see how much whitespace that line
 * begins with and add that whitespace to the beginning of the new line
 * @author Team Bash Browns
 *
 */
public class NewLineCommand implements Command {
	
	private JTextArea textArea;
	
	public NewLineCommand(JTextArea text_area){
		textArea = text_area;
	}

	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
		int carPos = textArea.getCaretPosition();
		String text = textArea.getText();		// Text currently in the buffer
		
		int i = carPos-2;
		while(text.charAt(i)!='\n' && i>0){		// Go to the start of the previous line
			i--;
		}
		i++;
		String lineStart = "";
		while(Character.isWhitespace(text.charAt(i)) && i<text.length()-1){
			lineStart += text.charAt(i);
			i++;
		}
		textArea.insert(lineStart, carPos);
	}
}
