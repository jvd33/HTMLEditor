package parser;
import java.util.Iterator;
import java.util.regex.*;
import java.util.*;

public class ParserIterator implements Iterator<String> {
	
	private String iteratee;
	private Matcher head;
	private Matcher comment;
	private Matcher text;
	private List<String> tags;
	private Iterator<String> internal;
	
	public ParserIterator(String s) {
		iteratee = s;
		Pattern HTMLHead = Pattern.compile("<.*?>");
		Pattern commentP = Pattern.compile("<!--.*?-->");
		Pattern textP = Pattern.compile("(.*?)<.*?>", Pattern.DOTALL);
		head = HTMLHead.matcher("");
		comment = commentP.matcher("");
		text = textP.matcher("");
		tags = new ArrayList<String>();
		stripText();
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
		System.out.println(iteratee);
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
	// JUST INCASE THIS BREAKS AGAIN
	public List get() { 
		return tags;
	}
	
	public static void main(String args[]) { 
		String t = "<html>\n<p></p>\n<div>hey</div>\n</html>";
		ParserIterator p = new ParserIterator(t);
		System.out.println(p.get());
	}*/
	
	
}
