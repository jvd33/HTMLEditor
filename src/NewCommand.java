import java.io.File;

/**
 * 
 */



/**
 * @author Bash-Browns
 *
 */
public class NewCommand implements Command {
	private HTMLEditor htmlEditor;
	private File f;
	/**
	 * Creates a new New Concrete Command taking in HTMLEditor
	 * @param htmle
	 */
	public NewCommand(HTMLEditor htmle, File f){
		this.htmlEditor = htmle;
		this.f = f;
	}
	
	/* (non-Javadoc)
	 * @see Command#execute()
	 */	
	@Override
	public void execute() {
		Buffer b = new Buffer(f);
		htmlEditor.addBuffer(b);
	}
}
