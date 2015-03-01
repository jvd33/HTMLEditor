/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public interface Command {
	/**
	 * Abstract method implying what needs to go into each command
	 * Command pattern
	 * 
	 * Currently this should handle the following commands in the interface:
	 * 		New
	 * 		Open
	 * 		Save
	 * 		Edit
	 * 		Undo
	 * 		Redo
	 * 		Cut
	 * 		Copy
	 * 		Paste
	 * 		Help
	 * 		Open Readme
	 */
    public abstract void execute();
}
