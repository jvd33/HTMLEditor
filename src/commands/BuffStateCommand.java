package commands;
import parser.HTMLParser;
import buffer.Buffer;


/**
 * Gets called when new text is entered into the buffer
 * Enables input of text to be undone
 * Effectively acts as a save-state command for the text in the buffer
 */
public class BuffStateCommand implements Command, Undoable {
	
	private Buffer b;
	private String s;
	
	/**
	 * Constructor for the BuffStateCommand
	 * @param buff The buffer in question
	 * @param newText The new text in the buffer that should be saved
	 */
	public BuffStateCommand(Buffer buff, String newText) { 
		b = buff;
		s = newText;
		
	}
	
	@Override
	public void execute() {
		try{
			b.addUndo(s);
			b.addText(s);
			b.addTag(new HTMLParser(s).parse());
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
