
public class CommandHandler {
	
	private java.util.Stack<Undoable> undoables;
	private java.util.Stack<Undoable> redoables;
	
	public CommandHandler(){
		this.undoables = new java.util.Stack<Undoable>();
		this.redoables = new java.util.Stack<Undoable>();
	}

	public void executeCommand(Command c){
		if(c instanceof Undoable){
			undoables.push((Undoable)c);
		}
		c.execute();
	}
	
	public void undoCommand(){
		if(this.undoables.isEmpty()){
			return;
		}
		Undoable c = (Undoable)this.undoables.pop();
		c.undo();
		redoables.push(c);
	}
	
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

interface Undoable{

	void undo();

	void redo();
	
}