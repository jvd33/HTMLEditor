package parser;
import java.util.Iterator;
import java.util.regex.*;
import java.util.*;

/**
 * 
 * @author Team Bash-Browns (Team 4)
 *ParserIterator populates a list of strings matching regexs for
 *html tag constructs and plaintext. Also removes all comments
 *from the string
 */
public class ParserIterator implements Iterator<String> {
	
	private String iteratee;
	private Matcher head;
	private Matcher comment;
	private Matcher text;
	private Matcher url;
	private List<String> urls;
	private List<String> tags;
	private Iterator<String> internal;
	
	/**
	 * Constructor
	 * @param s, the text to parse
	 */
	public ParserIterator(String s) {
		iteratee = s;
		Pattern HTMLHead = Pattern.compile("<.*?>");
		Pattern commentP = Pattern.compile("<!--.*?-->");
		Pattern textP = Pattern.compile("(.*?)<.*?>", Pattern.DOTALL);
		String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		Pattern urlP = Pattern.compile(regex);
		url = urlP.matcher("");
		head = HTMLHead.matcher("");
		comment = commentP.matcher("");
		text = textP.matcher("");
		urls = new ArrayList<String>();
		tags = new ArrayList<String>();
		stripText();
		findLinks();
		internal = tags.iterator();
	}
	
	@Override
	public boolean hasNext() {
		return internal.hasNext();
	}

	@Override
	public String next() {
		String next;
		try { 
			next = (String) internal.next();
		} catch(NoSuchElementException e) { 
			next = null;
		}
		return next;
	}

	@Override
	public void remove() {
		try { 
			internal.remove();
		} catch(NoSuchElementException e) { 
			return;
		}
	}
	
	/**
	 * Removes all comments from the input string
	 */
	private void stripComments() {
		comment.reset(iteratee);
		while(comment.find()) { 
			iteratee = comment.replaceAll("");
			comment.reset(iteratee);
		}
	}
	
	/**
	 * Transforms the text from a string into an array list of 
	 * valid html elements and strings. Keeps the structure
	 */
	private void stripText() {
		this.stripComments();
		head.reset(iteratee);
		text.reset(iteratee);
		while(head.find()) { 
			if(text.find()) { 
				String temp = text.group(1);
				if(!temp.equals("")) { tags.add(temp); } 
			}
			tags.add(head.group());
		}
	}
	
	/**
	 * Finds all the links in the text field
	 */
	private void findLinks() { 
		url.reset(iteratee);
		while(url.find()) { 
			urls.add(url.group());
		}
	}
	
	/**
	 * Getter method for the links
	 * @return a list of the links
	 */
	public List<String> getLinks() { 
		return urls;
	}
		
}
