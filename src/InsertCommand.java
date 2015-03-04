import java.io.File;

import javax.swing.JTextArea;

/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class InsertCommand implements Command {
	JTextArea textArea;
	String insertedText;
	public InsertCommand(JTextArea text_area, String tag_name){
		insertedText = tagNameToTag(tag_name);
		textArea = text_area;
	}

	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
		String origText = textArea.getText();
		String newText = "";
		newText = origText.substring(0, textArea.getCaretPosition()) + insertedText +
				origText.substring(textArea.getCaretPosition());
		textArea.setText(newText);
	}
	
	private String tagNameToTag(String s){
		s = "<"+s+"></"+s+">";
		return s;
	}
}
