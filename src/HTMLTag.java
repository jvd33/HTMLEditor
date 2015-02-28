import java.util.*;

/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class HTMLTag {
		
		private List<HTMLTag> children;
		private int startLine, endLine;
		private String startTag, endTag;
		private String attribute;
		
		public HTMLTag() { 
		
		}
		//constructor, child param is optional. 
		public HTMLTag(String t) {
			startTag = t;
			//attribute = attr[0];
			children = new ArrayList<HTMLTag>();
			
			
		}
		
		//adds a child to the children list
		public void addChild(HTMLTag child) {
			children.add(child);
		}
		
		//sets the start and end line
		public void setLines(int start, int end) {
			startLine = start;
			endLine = end;
		}
		
		public void setEndTag(String in) { 
			endTag = in;
		}
		
		//gets and returns ALL children of the tag
		public List<HTMLTag> getChildren() { 
			List<HTMLTag> allChildren = new ArrayList<HTMLTag>();
			for(HTMLTag child : children) {
				allChildren.add(child);
				List<HTMLTag> childTags = child.getChildren();
				allChildren.addAll(childTags);
			}
			return children;
		}
		
		public String getStartTag() {
			return startTag;
		}
		
		public String getEndTag() { 
			return endTag;
		}
		
		public int getStart() { 
			return startLine;
		}
		
		public int getEnd() {
			return endLine;
		}
}
