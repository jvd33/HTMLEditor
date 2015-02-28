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
	
	private Pattern HTMLHead;
	private Pattern HTMLTail;
	private Pattern comment;
	private Pattern HTMLpattern;
	private Buffer buffer;
	private Matcher head;
	private Matcher tail;
	private Matcher commentMatcher;
	private Matcher HTML;
	//private Matcher m;
	
	
	//constructor
	public HTMLParser(Buffer b) {
		HTMLHead = Pattern.compile("<.*?>"); //this regex matches the head of an html tag
		HTMLTail = Pattern.compile("</\\w+>(?!\\p{Punct}+)"); //this only kind of works
		HTMLpattern = Pattern.compile("<.*?>.*?</.*?>");
		comment = Pattern.compile("<!--.*?-->");
		buffer = b;
		head = HTMLHead.matcher("");
		tail = HTMLTail.matcher("");
		commentMatcher = comment.matcher("");
		HTML = HTMLpattern.matcher(""); //matches an entire HTML tag, <..></..> the tag itself doesn't matter.
	}
	
	//parses the buffer into an HTMLTag
	public HTMLTag parse(Buffer b) { 
		String input = b.text;
		HTML.reset(input);
		HTMLTag tag = new HTMLTag();
		System.out.println(input);
		input = tagHelper(input, tag);
		System.out.println(input);
		while(HTML.find()) { 
			HTMLTag temp = new HTMLTag();
			input = tagHelper(input, temp);
			//tag.addChild(temp); this throws an exception?
			//System.out.println(input);
		}
		return tag;
	}
	
	
	//might need this
	public String tagHelper(String input, HTMLTag tag) { 
		head.reset(input);
		tail.reset(input);
		head.find();
		tail.find();
		System.out.println(head.group());
		System.out.println(tail.group());
		
		String tailString = tail.group().substring(2, tail.group().length()-1); //gets rid of surrounding html brackets just the tag
		String headString = head.group().substring(1, head.group().length()-1);
		input = input.substring(head.end(), tail.start());
		if(!headString.equals(tailString)) { //check to make sure start tag == end tag, just in case
			try {
				throw new IncorrectHTMLException();
			} catch (IncorrectHTMLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(headString);
				System.out.println(tailString);
			}
		}
		tag.setTag(headString);
		System.out.println(input);
		return input;
	}
	
	/*
	//ignore this testing stuff
	public static void main(String args[]) { 
		String input = "<html><p>this is useless<!--and get rid of this--><div></div>.info yada yada</p><!--get rid of this--></html>";
		Buffer b = new Buffer();
		b.addText(input);
		HTMLParser parser = new HTMLParser(b);
		//b.text = parser.stripComments(input);
		parser.parse(b);
		
	}
	*/

}
