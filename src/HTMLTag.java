import java.util.*;

/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class HTMLTag implements DocumentElement{
		
		private HTMLTag parent;
		private List<DocumentElement> children;
		private int startLine, endLine;
		private String startTag, endTag;
		private String attribute;
		private boolean isCollapsed;
		
		public HTMLTag() { 
		
		}
		/*
		 * constructor
		 */
		public HTMLTag(String t, HTMLTag parent) {
			startTag = t;
			children = new ArrayList<DocumentElement>();
			this.setParent(parent);
			isCollapsed = false;
			
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
		public List<DocumentElement> getChildren() { 
			List<DocumentElement> allChildren = new ArrayList<DocumentElement>();
			for(DocumentElement child : children) {
				allChildren.add(child);
				List<DocumentElement> childElements = child.getChildren();
				allChildren.addAll(childElements);
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
		
		@Override
		public String print() {
			String returnString = "";
			if(isCollapsed){	// It should print its start tag
				returnString = getStartTag();
			}else{
				for(DocumentElement de: children){
					returnString += de.print();
				}
			}
			return returnString;

		}
}
