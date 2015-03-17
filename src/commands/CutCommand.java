package commands;

/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class CutCommand implements Command, Undoable {
	
	public CutCommand(String s){
		
	}
	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
		
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