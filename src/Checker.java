
/**
 * 
 */

/**
 * @author Bash-Browns
 *
 */
public class Checker{
	Buffer buff;
	/**
	 * Creates a new New Concrete Command taking in HTMLEditor
	 * @param html
	 */	
	public Checker(Buffer b){
		buff = b;
	}
	

	public boolean check() {
			
			HTMLParser parser = new HTMLParser(buff.text);
			
			buff.addTag(parser.parse());
			try{
				return buff.checkHTML();
			
			catch(IncorrectHTMLException e)
			{
				//e.printStackTrace();
				//System.out.println("Just so you know, there's some invalid HTML that you might want to do something about...");
				javax.swing.JOptionPane.showMessageDialog(null, "This document has invalid HTML", "Incorrect HTML Exception", javax.swing.JOptionPane.ERROR_MESSAGE);
			}
			
			
		
	}

}
