package commands;
import java.util.EmptyStackException;

import buffer.Buffer;

/**
 * Command to invoke an undo of Undoable commands
 * @author Team Bash-Browns
 *
 */
public class UndoCommand implements Command {
	private Buffer b;
	
	/**
	 * Creates a new UndoCommand taking in the desired buffer
	 * @param buff The buffer which has a command that needs to be undone
	 */
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
