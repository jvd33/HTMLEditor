package commands;

import javax.swing.JTextArea;

import editor.HTMLEditor;

/**
 * Allows the editor to take text in the clipboard and place
 * it into the BufferView
 * @author Team Bash-Browns
 *
 */
public class PasteCommand implements Command, Undoable {
	
	private JTextArea textarea;
	private HTMLEditor editor;

	/**
	 * Constructor of the cut command
	 * @param jta Text area of the current buffer
	 * @param e The HTML editor that holds the clipboard
	 */
	public PasteCommand(JTextArea jta, HTMLEditor e){
		textarea = jta;
		editor = e;
	}

	/* (non-Javadoc)
	 * @see commands.Undoable#undo()
	 */
	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see commands.Undoable#redo()
	 */
	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see commands.Command#execute()
	 */
	@Override
	public void execute() {
		String pastedtext = editor.getClipboard();
		String oldText = textarea.getText();
		String newText = oldText.substring(0, textarea.getCaretPosition())
				+ pastedtext + oldText.substring(textarea.getCaretPosition());
		textarea.setText(newText);
	}
}
