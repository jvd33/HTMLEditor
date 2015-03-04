
/*
 * gets called when new text is entered into the buffer
 */
public class BuffStateCommand implements Command {
	
	private Buffer b;
	private String s;
	
	public BuffStateCommand(Buffer buff, String newText) { 
		b = buff;
		s = newText;
		
	}
	
	@Override
	public void execute() {
		try{
			b.addUndo(s);
			b.addText(s);
		}catch(NullPointerException e){
			javax.swing.JOptionPane.showMessageDialog(null, "No file entered", "File Not Found", javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		
		
		
	}
	

}
