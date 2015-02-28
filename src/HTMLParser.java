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
		HTMLTag root = new HTMLTag(rootTag);
		iterator.remove();
		while(iterator.hasNext()) { 
			String temp = iterator.next();
			if(isEndTag(root.getStartTag(), temp)) {
				root.setEndTag(temp);
			}
			else { 
				tagHelper(root, temp);
			}
		}
		return root;
	}
	
	
	//might need this
	private void tagHelper(HTMLTag tag, String temp) { 
		String startTag = temp;
		HTMLTag child = new HTMLTag(temp);
		tag.addChild(child);
		iterator.remove();
		while(iterator.hasNext()) { 
			temp = iterator.next();
			if(isEndTag(child.getStartTag(), temp)) {
				child.setEndTag(temp);
				break;
			}
			else { 
				tagHelper(child, temp);
			}
		}

	}
	
	private boolean isEndTag(String startTag, String endTag) { 
		return startTag.replace("<", "</").equals(endTag);
	}
	
	
	//ignore this testing stuff
	public static void main(String args[]) { 
		String input = "<html><div></div><div><p><div></div></p></div></html>";
		HTMLParser parser = new HTMLParser(input);
		HTMLTag tag = parser.parse();
		System.out.println(tag.getChildren());
		
	}
	

}
