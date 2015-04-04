package commands;

import javax.swing.JTextArea;

import editor.HTMLEditor;
import buffer.BufferView;

/**
 * Allows the editor to take the selected text from the BufferView
 * and save it into the editor's clipboard
 * 
 * @author Team Bash-Browns
 *
 */
public class CopyCommand implements Command {
	
	private JTextArea textarea;
	private HTMLEditor htmleditor;
	
	/**
	 * Constructor of the copy command
	 * @param ta Text area of the current buffer
	 * @param e The HTML editor that holds the clipboard
	 */
	public CopyCommand(JTextArea ta, HTMLEditor e) {
		textarea = ta;
		htmleditor = e;
	}

	/* (non-Javadoc)
	 * @see commands.Command#execute()
	 */
	@Override
	public void execute() {
		System.out.println("this is the text: " + textarea.getText());
		htmleditor.setClipboard(textarea.getSelectedText());
		System.out.println(htmleditor.getClipboard());
	}

}
