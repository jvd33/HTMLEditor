package elements;
import java.util.*;

/**
 * HTML tag within an HTML document
 * Acts as the composite within the composite pattern
 * Contains many TextElements within the document
 * @author Team Bash-Browns
 */
public class HTMLTag implements DocumentElement{
		
		private HTMLTag parent;
		private List<DocumentElement> children;
		private int startLine, endLine;
		private String startTag, endTag;
		private String attribute;
		private boolean isCollapsed;
		
		/**
		 * Constructor of the HTMLTag
		 * @param t Start tag of the tag object
		 * @param parent The tag it is a child of
		 */
		public HTMLTag(String t, HTMLTag parent) {
			startTag = t;
			children = new ArrayList<DocumentElement>();
			this.setParent(parent);
			isCollapsed = false;
			
		}
		
		/**
		 * Adds a child to the children list
		 * @param child Either an HTMLTag or a TextElement
		 */
		public void addChild(DocumentElement child) {
			children.add(child);
		}
		
		/**
		 * Sets the string of the end tag
		 * @param in Text that the end tag should be
		 */
		public void setEndTag(String in) { 
			endTag = in;
		}
		
		/**
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
		
		/**
		 * Get the start tag
		 * @return Text in the start tag
		 */
		public String getStartTag() {
			return startTag;
		}
		
		/**
		 * Gets the end tag
		 * @return Text in the end tag
		 */
		public String getEndTag() { 
			return endTag;
		}

		@Override
        public String toString(){
            return startTag + " " + endTag;
        }
        
        /**
         * Gets the parent node
         * return The HTMLTag it belongs to
         */
		public HTMLTag getParent() {
			return parent;
		}
		
		/**
		 * Sets the parent node
		 * @param parent The HTMLTag it belongs to
		 */
		public void setParent(HTMLTag parent) {
			this.parent = parent;
		}
		
		@Override
		public String print() {
			String returnString = getStartTag();
			if(isCollapsed){	// It should print its start tag
				return returnString;
			}else{
				for(DocumentElement de: children) {
					returnString += de.print();
				}
				returnString+=getEndTag();
			}
			return returnString;
		}
		/**
		@Override
		public boolean equals(Object obj){
			if(obj instanceof HTMLTag){
				HTMLTag castedObj = (HTMLTag) obj;
				if(		castedObj.attribute.equals(attribute) &&
						castedObj.children.equals(children) &&
						castedObj.endTag.equals(endTag) &&
						castedObj.startTag.equals(startTag)&&
						castedObj.startLine==startLine &&
						castedObj.endLine==endLine &&
						castedObj.isCollapsed==isCollapsed &&
						castedObj.parent.equals(parent)){
					return true;
				}
			}
			return false;
		}*/
}
