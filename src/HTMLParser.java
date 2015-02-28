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

/*
 * Parses a string into an aggregate HTMLTag class
 */
public class HTMLParser {
	
	private String s;
	private ParserIterator iterator;
	
	//constructor, creates an iterator for the string
	public HTMLParser(String s) {
		iterator = new ParserIterator(s);
		
		
	}
	
	/*
	 * parses the string into an html tag
	 */
	public HTMLTag parse() { 
		String rootTag = iterator.next();
		HTMLTag root = new HTMLTag(rootTag, null);
		iterator.remove();
		while(iterator.hasNext()) { 
			String temp = iterator.next();
			if(isStartTag(temp)) { 
				tagHelper(root, temp);
			}
			else if(isEndTag(root.getStartTag(), temp)) {
				root.setEndTag(temp);
			}
		}
		return root;
	}
	
	
	/*
	 * @param tag - the parent of the new tag to create
	 * @param temp - the tag that was found that is not the end of the root
	 * creates a new tag, and adds children iteratively until
	 * there are no more children to add and all tags
	 * have been processed
	 */
	private void tagHelper(HTMLTag tag, String temp) { 
		String startTag = temp;
		HTMLTag child = new HTMLTag(temp, tag);
		tag.addChild(child);
		while(iterator.hasNext()) { 
			temp = iterator.next();
			if(isStartTag(temp)) {
				tagHelper(child, temp);
			}
			else if(isEndTag(child.getStartTag(), temp)) {
				if(child.getEndTag() != null) { 
					setParentTag(temp, child);
				}
				else { 
					child.setEndTag(temp);
				}
			}
			else { 
				setParentTag(temp, child);
				//if(temp != null) { 
					//tagHelper(child, temp);
				//}
			}

		}
	}
	
	/*
	 * Checks to see if the tag given is the 
	 * end of the tag
	 */
	private boolean isEndTag(String startTag, String endTag) { 
		return startTag.replace("<", "</").equals(endTag);
	}
	
	private boolean isEnd(String tag) { 
		return tag.contains("</");
	}
	
	private boolean isStartTag(String tag) {
		return !tag.contains("</");
	}
	
	private void setParentTag(String s, HTMLTag tag) { 
		while(tag.getParent() != null) { 
			HTMLTag tempTag = tag.getParent();
			if(isEndTag(tempTag.getStartTag(), s)) { 
				tempTag.setEndTag(s);
			}
			tag = tag.getParent();
		}
	}
	
	
	//ignore this testing stuff
	public static void main(String args[]) { 
		String input = "<html><p><p><div></div></p></p></html>";
		HTMLParser parser = new HTMLParser(input);
		HTMLTag tag = parser.parse();
		System.out.println(tag.getChildren());
		
	}
	

}
