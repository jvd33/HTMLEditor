package commands;
import java.util.EmptyStackException;

import buffer.Buffer;

/**
 * Command to redo an undone command by calling its redo() method
 * @author Team Bash-Browns
 *
 */
public class RedoCommand implements Command {
	private Buffer b;
	
	/**
	 * Constructor for the Redo command
	 * @param buff Buffer that is having a command redone
	 */
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
