import java.util.EmptyStackException;
public class RedoCommand implements Command {
	private Buffer b;
	
	public RedoCommand(Buffer buff) { 
		b = buff;
	}

	@Override
	public void execute() {
		try {
			b.redo();
			b.notifyObservers(b.text);
		} catch(EmptyStackException e) { 
			return;
		}
		
	} 

}
