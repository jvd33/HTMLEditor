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
		htmleditor.setClipboard(textarea.getSelectedText());
	}

}
