import java.util.List;
import java.util.ArrayList;
import java.util.regex.*;

/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */

//GUYS THIS IS REALLY SKETCHY BE CAREFUL 
//it also doesnt really work yet (regex might not be the best solution but I was messing around with it)
public class HTMLParser {
	
	private String s;
	private ParserIterator iterator;
	
	//constructor
	public HTMLParser(String s) {
		iterator = new ParserIterator(s);
		
		
	}
	
	//parses the buffer into an HTMLTag
	public HTMLTag parse() { 
		String rootTag = iterator.next();
		HTMLTag tag = new HTMLTag(rootTag);
		iterator.remove();
		while(iterator.hasNext()) { 
			String temp = iterator.next();
		}
		return null;
	}
	
	
	//might need this
	private void tagHelper(HTMLTag tag) { 

	}
	
	private boolean isEndTag(String s) { 
		String endTag = s.replace("<", "</");
		return s.equals(endTag);
	}
	
	/*
	//ignore this testing stuff
	public static void main(String args[]) { 
		String input = "<html><p>this is useless<div></div>.info yada yada</p></html>";
		Buffer b = new Buffer();
		b.addText(input);
		HTMLParser parser = new HTMLParser(b);
		//b.text = parser.stripComments(input);
		parser.parse(b);
		
	}
	*/

}
