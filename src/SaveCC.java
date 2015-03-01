/**
 * 
 */

/**
 * @author Bash-Browns
 *
 */
public class SaveCC implements Command {
	HTMLEditor htmlEditor;
	/**
	 * Creates a new New Concrete Command taking in HTMLEditor
	 * @param htmle
	 */	
	public SaveCC(HTMLEditor htmle){
		this.htmlEditor = htmle;
	}
	
	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
	}

}
