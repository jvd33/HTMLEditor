
public class RedoCommand implements Command {
	
	private Buffer b;
	public RedoCommand(Buffer buff) { 
		b = buff;
	}
	@Override
	public void execute() {
		b.redo();
	}

}
