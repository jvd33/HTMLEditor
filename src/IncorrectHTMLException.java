
public class IncorrectHTMLException extends Exception {
	
	public IncorrectHTMLException(){
		super("Incorrect HTML");
	}
	
	public static void main(String[] args){
		try {
			throw new IncorrectHTMLException();
		} catch (IncorrectHTMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
