import java.util.EmptyStackException;
public class UndoCommand implements Command {
	private Buffer b;
	
	public UndoCommand(Buffer buff) { 
		b = buff;
	}

	@Override
	public void execute() {
		try {
			b.addRedo(b.text);
			b.undo();
			b.notifyObservers(b.text);
		} catch(EmptyStackException e) { 
			return;
		}
		
	} 

}
