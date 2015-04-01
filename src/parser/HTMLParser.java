package parser;

import java.util.List;
import java.util.ArrayList;

import elements.*;

/**
 * Parses a string into an aggregate HTMLTag class
 * @author Team Bash-Browns
 *
 */
public class HTMLParser {
	
	private ParserIterator iterator;
	private List<String> links;
	
	/**
	 * constructor, creates an iterator for the string
	 */
	public HTMLParser(String s) {
		iterator = new ParserIterator(s);
		links = iterator.getLinks();
	}
	
	/**
	 * parses the string into an html tag
	 * @return The root html tag
	 */
	public HTMLTag parse() { 
		String rootTag = iterator.next();
		HTMLTag root = new HTMLTag(rootTag, null); //create a new root tag
		while(iterator.hasNext()) { //while there are tags to parse
			String temp = iterator.next();
			if(isStartTag(temp)) { //if its a start tag
				tagHelper(root, temp); //call the helper method
			}
			else if(isEndTag(root.getStartTag(), temp)) { //if its the end tag
				root.setEndTag(temp); //set it and break
				return root;
			}
			else { //if it gets this far, it's plaintext
				TextElement txt = new TextElement(temp); 
				root.addChild(txt);
			}
		}
		
		return root; //return the root (aggregate of html tags)
	}
	
	public List<String> getLinks() { 
		return links;
	}
	
	/**
	 * Creates a new tag, and adds children iteratively until
	 * there are no more children to add and all tags
	 * have been processed
	 * @param tag - the parent of the new tag to create
	 * @param temp - the tag that was found that is not the end of the root
	 */
	private void tagHelper(HTMLTag tag, String temp) { 
		HTMLTag child = new HTMLTag(temp, tag); //create a new child tag
		if(temp.substring(0,5).equals("<img ")) { 
			child.setEndTag("");
		}
		tag.addChild(child); //add it as a child to the tag passed in,
		while(iterator.hasNext()) { //while there are still tags to parse,
			temp = iterator.next(); 
			if(isStartTag(temp) && child.getEndTag()==null) { //if it's a start tag, recursively call again
				tagHelper(child, temp);
				
			}
			else if(isStartTag(temp) && child.getEndTag() != null) { 
				tagHelper(tag, temp);
				
			}
			//if its the end tag of the tag we created,
			else if(isEndTag(child.getStartTag(), temp)) { 
				if(child.getEndTag() != null) { //check if it already has an endtag,
					setParentTag(temp, child); //check its parents
				}
				else { 
					child.setEndTag(temp); //else set the tag
				}
			}
			else if(!temp.contains("<") && child.getEndTag()==null) { 
				TextElement txt = new TextElement(temp); //create a text element and add a child
				child.addChild(txt);
				setParentTag(temp, child);
			}
			 //adds TextElements as children to the parent tag
			else if(!temp.contains("<") && child.getEndTag()!=null	) { //this is gross
				TextElement txt = new TextElement(temp);
				if(tag.getParent() != null) { 
					tag.getParent().addChild(txt);
				} else { tag.addChild(txt); } 
			}
			else { 
				setParentTag(temp, child); //else check parents
			}

		}
	}
	
	/**
	 * Iterates up through parent tags and tries to find
	 * the matching start tag for the end tag that was matched
	 * @param s, the html tag that was matched
	 * @param tag, the HTMLTag with parents to check
	 */
	private void setParentTag(String s, HTMLTag tag) { 
		while(tag.getParent() != null) { //while the tag isnt the root,
			HTMLTag tempTag = tag.getParent(); //store the parent temp
			if(isEndTag(tempTag.getStartTag(), s)) { //if the string passed in is the end tag,
				tempTag.setEndTag(s); //set the end tag of the parent
			}
			tag = tag.getParent(); 
		}
	}
	
	/**
	 * Checks to see if the tag given is the end of the tag
	 * Replaces the "<" in the start tag with "</" and sees
	 * if the two are equal
	 * handles link tags as well
	 * @param startTag Text of the starting tag
	 * @param endTag Text of the ending tag
	 * @return Whether or not the start tag matches the end tag
	 */
	private boolean isEndTag(String startTag, String endTag) {
		if(startTag.length() >= 5 && startTag.substring(0, 5).equals("<a hr")
				&& endTag.equals("</a>")) { //this is gross
			return true;
		}
		else if(startTag.length() >= 5 && startTag.substring(0, 5).equals("<img=")) { 
			return true;
		}
		
		else { 
			return startTag.replace("<", "</").equals(endTag);
		}
	}
	
	/**
	 * Just checks a tag to see if it is a start html tag
	 * @param tag Text of the tag
	 * @return Whether or not the tag is a start tag
	 */
	private boolean isStartTag(String tag) {
		return !tag.contains("</") && tag.contains("<");
	}

}
