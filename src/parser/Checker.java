package parser;
import buffer.Buffer;
import java.util.List;


/**
 * 
 */

/**
 * Class that automates checking whether the HTML in a buffer is well-formed
 * @author Team Bash-Browns
 *
 */
public class Checker{
	private Buffer buff;
	private HTMLParser parser;
	
	/**
	 * Creates a new New Concrete Command taking in the current buffer
	 * @param b The buffer in question
	 */	
	public Checker(Buffer b){
		buff = b;
		parser = new HTMLParser(buff.text);
	}
	
	/**
	 * Checks to see whether the html in the buffer is valid
	 * @return Whether or not the buffer's html is well-formed
	 */
	public boolean check() {
		buff.addTag(parser.parse());
		return buff.checkHTML();		
	}
	
	/**
	 * Returns a list of the urls found in the text
	 */
	public List<String> getLinks() { 
		return parser.getLinks();
	}
}
