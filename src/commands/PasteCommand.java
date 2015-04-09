package commands;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

import javax.swing.JTextArea;

import parser.HTMLParser;
import buffer.Buffer;
import editor.HTMLEditor;

/**
 * Allows the editor to take text in the clipboard and place
 * it into the BufferView
 * @author Team Bash-Browns
 *
 */
public class PasteCommand implements Command, Undoable {
	
	private JTextArea textarea;
	private Buffer buffer;

	/**
	 * Constructor of the cut command
	 * @param jta Text area of the current buffer
	 * @param e The HTML editor that holds the clipboard
	 */
	public PasteCommand(JTextArea jta, Buffer b){
		textarea = jta;
		buffer = b;
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
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String pasteText = "";
		try{
			pasteText = (String) clipboard.getContents(null).getTransferData(DataFlavor.stringFlavor);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		String newText = textarea.getText().substring(0, textarea.getCaretPosition()) + pasteText
				+ textarea.getText().substring(textarea.getCaretPosition());
		buffer.addText(newText);
	}
}
