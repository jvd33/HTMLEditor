import java.util.Iterator;
import java.util.regex.*;
import java.util.*;

public class ParserIterator implements Iterator {
	
	private String iteratee;
	private Matcher head;
	private Matcher comment;
	private List<String> tags;
	private Iterator internal;
	
	public ParserIterator(String s) {
		iteratee = s;
		Pattern HTMLHead = Pattern.compile("<.*?>");;
		Pattern commentP = Pattern.compile("<!--.*?-->");
		head = HTMLHead.matcher("");
		comment = commentP.matcher("");
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
	
	//Removes all comments in the input string- this will make it easier to parse (comments are irrelevant)
	private void stripComments() {
		iteratee = iteratee.replaceAll("\\s+","");
		comment.reset(iteratee);
		while(comment.find()) { 
			iteratee = comment.replaceAll("");
			comment.reset(iteratee);
		}
	}
	
	private void stripText() {
		this.stripComments();
		head.reset(iteratee);
		while(head.find()) { 
			tags.add(head.group());
		}
	}
	
}
