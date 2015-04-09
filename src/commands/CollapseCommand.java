package commands;
import buffer.Buffer;


/**
 * Gets called when collapse() is needed, saves the structure of the 
 * tree and does not reparse
 */
public class CollapseCommand implements Command, Undoable {
	
	private Buffer b;
	private String s;
	
	/**
	 * Constructor for the CollapseCommand
	 * @param buff The buffer in question
	 * @param newText The new text in the buffer that should be saved
	 */
	public CollapseCommand(Buffer buff, String newText) { 
		b = buff;
		s = newText;
		
	}
	
	@Override
	public void execute() {
		try{
			b.addUndo(s);
			b.addText(s);;
			b.notifyObservers();
		}catch(NullPointerException e){
			javax.swing.JOptionPane.showMessageDialog(null, "No file entered", "File Not Found", javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		this.b.undo();
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		//execute();
		this.b.redo();
		
	}
	

}
