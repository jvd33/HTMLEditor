package commands;
import java.util.EmptyStackException;

import buffer.Buffer;
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
