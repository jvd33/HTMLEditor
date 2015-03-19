package commands;

import javax.swing.JTextArea;

import editor.HTMLEditor;

/**
 * Allows the editor to remove the selected text from the BufferView
 * and save it into the editor's clipboard
 * @author Team Bash-Browns
 *
 */
public class CutCommand implements Command, Undoable {
	private JTextArea textarea;
	private HTMLEditor editor;
	private String text;
	
	/**
	 * Constructor of the cut command
	 * @param jta Text area of the current buffer
	 * @param e The HTML editor that holds the clipboard
	 */
	public CutCommand(JTextArea jta, HTMLEditor e){
		textarea = jta;
		editor = e;
	}
	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
		text = textarea.getSelectedText();
		String oldText = textarea.getText();
		String newText = oldText.substring(0, textarea.getSelectionStart())
				+ oldText.substring(textarea.getSelectionEnd());
		textarea.setText(newText);
		editor.setClipboard(text);
	}
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void redo() {
		// TODO Auto-generated method stub
		execute();
	}

}