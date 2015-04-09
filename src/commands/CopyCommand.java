package commands;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

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
	
	/**
	 * Constructor of the copy command
	 * @param ta Text area of the current buffer
	 * @param e The HTML editor that holds the clipboard
	 */
	public CopyCommand(JTextArea ta) {
		textarea = ta;
	}

	/* (non-Javadoc)
	 * @see commands.Command#execute()
	 */
	@Override
	public void execute() {
		StringSelection selection = new StringSelection(textarea.getSelectedText());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
	}

}
