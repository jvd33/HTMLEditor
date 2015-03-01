
public class UndoCommand implements Command {
	private Buffer b;
	
	public UndoCommand(Buffer buff) { 
		b = buff;
	}

	@Override
	public void execute() {
		b.undo();
		
	} 

}
