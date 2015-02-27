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
			for(HTMLTag html : htmlToCheck.getChildren()){
				String startTag = new String(htmlToCheck.getTag());
				if(startTag.charAt(0) != '<'){
					throw new IncorrectHTMLException();
				}
				String endTag = startTag.charAt(0) + "/" + startTag.substring(1);
				if(this.toString().lastIndexOf(endTag) == (this.toString().length() - startTag.length()) - 2){
					throw new IncorrectHTMLException();
				}
				
			}
			return true;
		}

}
