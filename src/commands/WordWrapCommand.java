package commands;
import javax.swing.JTextArea;


public class WordWrapCommand implements Command, Undoable {
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
		System.out.println("Word wrap is now "+textArea.getLineWrap());
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
