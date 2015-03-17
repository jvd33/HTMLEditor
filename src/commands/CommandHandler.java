package commands;

/**
 * Contains the stacks for certain buffer commands to be undone and redone
 * @author Team Bash-Browns
 *
 */
public class CommandHandler {
	
	private java.util.Stack<Undoable> undoables;
	private java.util.Stack<Undoable> redoables;
	
	/**
	 * Constructor for the CommandHandler
	 * creates the undo and redo stacks to be used for buffer operations
	 */
	public CommandHandler(){
		this.undoables = new java.util.Stack<Undoable>();
		this.redoables = new java.util.Stack<Undoable>();
	}
	
	/**
	 * Calls the execution of a command and, if the command is undoable
	 * adds it to the undo stack
	 * @param c The command to be executed
	 */
	public void executeCommand(Command c){
		if(c instanceof Undoable){
			undoables.push((Undoable)c);
		}
		if(c instanceof BuffStateCommand){
			this.redoables = new java.util.Stack<Undoable>();
		}
		c.execute();
	}
	
	/**
	 * Undoes a command in the buffer
	 * If there is a command in the undo stack, the command gets popped,
	 * the undo() method of that command gets called, and the command gets
	 * pushed to the redo stack
	 */
	public void undoCommand(){
		if(this.undoables.isEmpty()){
			return;
		}
		Undoable c = (Undoable)this.undoables.pop();
		c.undo();
		redoables.push(c);
	}
	
	/**
	 * Redoes a command in the buffer that has been undone
	 * If there is a command in the redo stack, the command gets popped,
	 * the redo() method gets called on it, and the command gets pushed
	 * onto the undo stack
	 */
	public void redoCommand(){
		if(this.redoables.isEmpty()){
			return;
		}
		Undoable c = (Undoable)this.redoables.pop();
		c.redo();
		undoables.push(c);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/**
 * Specifies which commands can be undone
 * These commands are:
 * CutCommand
 * IndentLinesCommand
 * InsertCommand
 * NewLineCommand
 * WordWrapCommand
 *
 * @author Team Bash-Browns
 *
 */
interface Undoable{
	
	/**
	 * Takes the steps necessary to undo any changes the command
	 * had in the buffer
	 */
	void undo();
	
	/**
	 * Takes the steps necessary to redo any changes that were undone
	 * by the undo method
	 */
	//Should this be done with the execute command?
	void redo();
	
}