import java.util.*;

/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class HTMLTag {
		
		private HTMLTag parent;
		private List<HTMLTag> children;
		private int startLine, endLine;
		private String startTag, endTag;
		private String attribute;
		
		public HTMLTag() { 
		
		}
		/*
		 * constructor
		 */
		public HTMLTag(String t, HTMLTag parent) {
			startTag = t;
			children = new ArrayList<HTMLTag>();
			this.setParent(parent);
			
			
		}
		
		/*
		 * adds a child to the children list
		 */
		public void addChild(HTMLTag child) {
			children.add(child);
		}
		
		/*
		 * sets the start and end line
		 */
		public void setLines(int start, int end) {
			startLine = start;
			endLine = end;
		}
		
		public void setEndTag(String in) { 
			endTag = in;
		}
		
		/*
		 * gets and returns ALL children of the tag
		 */
		public List<HTMLTag> getChildren() { 
			List<HTMLTag> allChildren = new ArrayList<HTMLTag>();
			for(HTMLTag child : children) {
				allChildren.add(child);
				List<HTMLTag> childTags = child.getChildren();
				allChildren.addAll(childTags);
			}
			return allChildren;
		}
		
		/*
		 * Get the start tag
		 */
		public String getStartTag() {
			return startTag;
		}
		
		/*
		 * Gets the end tag
		 */
		public String getEndTag() { 
			return endTag;
		}
		
		/*Gets start line
		 * 
		 */
		public int getStart() { 
			return startLine;
		}
		
		/*
		 * Gets end line
		 */
		public int getEnd() {
			return endLine;
		}

        public String toString(){
            return startTag + " " + endTag;
        }
        
        /*
         * Gets the parent node
         */
		public HTMLTag getParent() {
			return parent;
		}
		
		/*
		 * sets the parent node
		 */
		public void setParent(HTMLTag parent) {
			this.parent = parent;
		}
}
