/**
 * 
 */

/**
 * @author Bash-Browns
 *
 */
public class SaveCommand implements Command {
	HTMLEditor htmlEditor;
	/**
	 * Creates a new New Concrete Command taking in HTMLEditor
	 * @param htmle
	 */	
	public SaveCommand(HTMLEditor htmle){
		this.htmlEditor = htmle;
	}
	
	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
	}

}
