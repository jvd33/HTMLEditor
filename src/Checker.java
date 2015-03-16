
/**
 * 
 */

/**
 * Class that automates checking whether the HTML in a buffer is well-formed
 * @author Team Bash-Browns
 *
 */
public class Checker{
	Buffer buff;
	/**
	 * Creates a new New Concrete Command taking in the current buffer
	 * @param b The buffer in question
	 */	
	public Checker(Buffer b){
		buff = b;
	}
	
	/**
	 * Checks to see whether the html in the buffer is valid
	 * @return Whether or not the buffer's html is well-formed
	 */
	public boolean check() {
			HTMLParser parser = new HTMLParser(buff.text);
			
			buff.addTag(parser.parse());
			try{
				return buff.checkHTML();
			}
			catch(IncorrectHTMLException e)
			{
				//e.printStackTrace();
				//System.out.println("Just so you know, there's some invalid HTML that you might want to do something about...");
				javax.swing.JOptionPane.showMessageDialog(null, "This document has invalid HTML", "Incorrect HTML Exception", javax.swing.JOptionPane.ERROR_MESSAGE);
				return false;
			}
	}
}
