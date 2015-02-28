import java.util.*;
/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class Buffer extends Observable {
		
		private HTMLTag tag; //root tag for buffer
		public List<Line> lines;//list of all lines in the buffer
		public String text;
		
		public Buffer() {
			this.lines = new ArrayList<Line>();
		}
		
		public void addTag(HTMLTag root) { 
			tag = root;
		}
		
		public void addText(String s) { 
			text = s;
		}
		//returns the full text of the buffer
		public String toString() { 
			String str = "";
			for(Line line : lines) {
				str += line.toString();
			}
			return str;
		}
		
		public HTMLTag getTag() {
			return tag;
		}
		
		public void addLine(Line line) { 
			lines.add(line);
		}
		
		public boolean checkHTML() throws IncorrectHTMLException{
			//checks if every start tag has an end tag
			HTMLTag htmlToCheck = this.getTag();
			if(htmlToCheck.getEndTag()!=null){
				for(HTMLTag tag : htmlToCheck.getChildren()){
					if(tag.getEndTag() ==null){
						throw new IncorrectHTMLException();
					}
					
				}
				return true;
			}
			else
			{
				throw new IncorrectHTMLException();
			}
			
		}
		
		//for science...
		public static void main(String args []){
			
			HTMLTag tag = new HTMLTag("<html>");
			tag.setEndTag("</html>");
			HTMLTag ctag = new HTMLTag("<body>");
			ctag.setEndTag("</body>");
			tag.addChild(ctag);
			HTMLTag ctag2 = new HTMLTag("<body1>");
			ctag2.setEndTag("</body1>");
			tag.addChild(ctag2);
			HTMLTag ctag1 = new HTMLTag("<p>");
			ctag1.setEndTag("</p>");
			ctag.addChild(ctag1);
			HTMLTag ctag3 = new HTMLTag("<p>");
			ctag3.setEndTag("</p>");
			ctag2.addChild(ctag3);
			Buffer b = new Buffer();
			b.addTag(tag);
			
			try{
				System.out.println(b.checkHTML());
			}
			catch(IncorrectHTMLException e){
				e.printStackTrace();
			}
		}

}
