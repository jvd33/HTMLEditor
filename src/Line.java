/**
 * 
 */

/**
 * @authorTeam Bash-Browns
 *
 */
public class Line {
	
	//maybe have indentation here?
	
	private String text;//string of all text for line object
	private int lineNum;//integer to keep track of lines
	
	//either a toString method, or a getText method?
	
	public String toString()
	{
		return text + "\n";
	}
	
	public int getNum(){
		
		return lineNum;
	}
}
