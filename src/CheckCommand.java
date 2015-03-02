
public class CheckCommand implements Command{

	private Buffer buffer;
	
	public CheckCommand(Buffer b){
		
		buffer = b;
	}
	
	@Override
	public void execute() throws IncorrectHTMLException{
		// TODO Auto-generated method stub
		buffer.checkHTML();
		
	}
	
}
