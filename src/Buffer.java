import java.util.*;
import java.io.File;
/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class Buffer extends Observable {
		
		private File sourceFile;
		private Stack<String> undoStack;
		private Stack<String> redoStack;
		private HTMLTag tag; //root tag for buffer
		private boolean hasView;
		public List<Line> lines;//list of all lines in the buffer
		public String text;
		
		/**
		 * Constructor for Buffer
		 * Instantiates new undoStack and redoStack
		 * Instantiates new empty array for lines
		 * @param f file that the buffer is based on
		 */
		public Buffer(File f) {
			sourceFile = f;
			undoStack = new Stack<String>();
			redoStack = new Stack<String>();
			this.lines = new ArrayList<Line>();
			this.hasView = false;
		}
		
		/**
		 * Replaces the root tag of the document with a new root tag
		 * @param root Should be the "html" tag
		 */
		public void addTag(HTMLTag root) { 
			tag = root;
		}
		
		/**
		 * Boolean function stating whether or not the buffer has an 
		 * associated BufferView
		 * @return Whether or not the buffer has a view
		 */
		public boolean hasView() { 
			return this.hasView;
		}
		
		/**
		 * Sets the text of the buffer
		 * 
		 * Sets the text string in the buffer to the parameter
		 * Iterates through the new text to create new Lines based on \n chars
		 * Recreates the Lines array
		 * @param s The new desired text in the buffer
		 */
		public void addText(String s) { 
			this.lines.clear();
			text = s;
			Line line = new Line();
			
			boolean stillLines = true;
			int start = 0;
			int end = 0;
			
			while(stillLines){	// Creates all lines
				start = 0;
				end = s.indexOf('\n');
				if(end > 0){
					String tempText = s.substring(start, end);
					line.setText(tempText);
					this.addLine(line);
					start = end;
					s = s.substring(start);
				}
				else{
					line.setText(s.substring(start));
					stillLines = false;
					
				}
			}	// End of line Creation
			notifyObservers(text);
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			// Contents of all the lines
			String str = "";
			for(Line line : lines) {
				str += line.toString();
			}
			return str;
		}
		
		/**
		 * Getter method for the root tag of the document
		 * @return the root tag (probably "html" tag)
		 */
		public HTMLTag getTag() {
			return tag;
		}
		
		/**
		 * Adds a new line to the end of the lines array
		 * @param line new line to be added
		 */
		public void addLine(Line line) { 
			lines.add(line);
			
		}
		
		/**
		 * Checks to see whether the tag tree is well-formed
		 * 
		 * Checks if every start tag has an end tag within the tree
		 * @return true if the tag tree is well formed
		 * @throws IncorrectHTMLException if any tags are missing an end tag
		 */
		public boolean checkHTML() throws IncorrectHTMLException{
			//checks if every start tag has an end tag
			HTMLTag htmlToCheck = this.getTag();
			System.out.println(htmlToCheck.toString());
			if(htmlToCheck.getEndTag()!=null){
				for(HTMLTag tag : htmlToCheck.getChildren()){
					if(tag.getEndTag() != null){
						continue;
					}
					else { 
						throw new IncorrectHTMLException();
					}
				
				}
				
				
			} else { 
				throw new IncorrectHTMLException();
			}

			return true;
		}
		
		/**
		 * Pushes a state to the undo stack
		 * @param state The contents of the buffer before a change
		 */
		public void addUndo(String state) {
			undoStack.push(state);
		}
		
		
		/**
		 * Pushes a state to the redo command
		 * @param state The contents of the buffer before a change
		 */
		public void addRedo(String state) { 
			redoStack.push(state);
		}
		
		/*
		 * performs the undo operation on the buffer's text
		 */
		/**
		 * Performs an undo operation
		 * 
		 * Pushes the current text to the redo stack 
		 * Pops text from the undo stack to the buffer
		 * Notifies observers of the change
		 */
		public void undo() { 
			addRedo(text);
			String temp = undoStack.pop();
			text = temp;
			addText(temp);
			setChanged();
			notifyObservers(text);
					
		}
		
		/**
		 * Performs a redo operation
		 * 
		 * Pushes the current text to the undo stack
		 * Pops text from the redo stack to the buffer
		 * Notifies observers of the change
		 */
		public void redo() {
			addUndo(text);
			String temp = redoStack.pop();
			text=temp;
			addText(temp);
			setChanged();
			notifyObservers(text);
		}
		
		/**
		 * Getter method for what file the Buffer cares about
		 * 
		 * @return The file the buffer references
		 */
		public File getFile() { 
			return sourceFile;
		}
		

		/**
		 * Setter method to designate a different file for the Buffer
		 * 
		 * Creates a new Java File object based on the input string
		 * Sets the new File to the Buffer's new path
		 * 
		 * @param path Path of the new file
		 */
		public void setFile(String path) {
			sourceFile = new File(path);
		}
		

		/**
		 * Sets the boolean hasView
		 * 
		 * Indicates that the buffer has an associated BufferView
		 * @param b
		 */
		public void setView(boolean b) { 
			this.hasView = b;
		}

}
