/**
 * 
 */

/**
 * @authorTeam Bash-Browns
 *
 */
public class Line {
	
	//maybe have indentation here?
	private String indentation;
	private String text;//string of all text for line object
	private int lineNum;//integer to keep track of lines
	
	//either a toString method, or a getText method?
	
	public Line(){
		this.text = "";
		this.indentation = "";
	}
	
	public String setText(String newText){
		this.text = newText;
		return this.text;
	}
	
	public String toString()
	{
		return text + "\n";
	}
	
	public int getNum(){
		
		return lineNum;
	}
	
	public String getIndent()
	{
		return this.indentation;
	}
	
	public void setIndent(String indent){
		
		this.indentation = indent;
	}
}
