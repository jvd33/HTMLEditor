/**
 * 
 */



/**
 * @author Bash-Browns
 *
 */
public class NewCC implements Command {
	HTMLEditor htmlEditor;
	/**
	 * Creates a new New Concrete Command taking in HTMLEditor
	 * @param htmle
	 */
	public NewCC(HTMLEditor htmle){
		this.htmlEditor = htmle;
	}
	
	/* (non-Javadoc)
	 * @see Command#execute()
	 */	
	@Override
	public void execute() {
		Buffer b = new Buffer();
		htmlEditor.addBuffer(b);
	}
}
