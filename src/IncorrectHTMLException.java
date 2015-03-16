/**
 * Thrown when HTML passed is not wellformed
 */
public class IncorrectHTMLException extends Exception {
	/**
	 * Constructor of IncorrectHTMLException
	 */
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
