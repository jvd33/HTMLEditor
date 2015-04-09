package commands;

import javax.swing.JTextArea;

import buffer.Buffer;
import editor.HTMLEditor;

/**
 * Allows the editor to remove the selected text from the BufferView
 * and save it into the editor's clipboard
 * @author Team Bash-Browns
 *
 */
public class CutCommand implements Command, Undoable {
	private JTextArea textarea;
	private String text;
	private Buffer buffer;
	
	/**
	 * Constructor of the cut command
	 * @param jta Text area of the current buffer
	 * @param e The HTML editor that holds the clipboard
	 */
	public CutCommand(JTextArea jta, Buffer b){
		textarea = jta;
		buffer = b;
	}
	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
		
		new CopyCommand(textarea).execute();
		String oldText = textarea.getText();
		String newText = oldText.substring(0, textarea.getSelectionStart())
				+ oldText.substring(textarea.getSelectionEnd());
		buffer.addText(newText);
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