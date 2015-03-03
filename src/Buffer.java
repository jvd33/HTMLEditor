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
		
		/*
		 * Constructor
		 */
		public Buffer(File f) {
			sourceFile = f;
			undoStack = new Stack<String>();
			redoStack = new Stack<String>();
			this.lines = new ArrayList<Line>();
			this.hasView = false;
		}
		
		/*
		 * Adds the root node of an aggregate HTMLTag to the buffer
		 */
		public void addTag(HTMLTag root) { 
			tag = root;
		}
		
		/*
		 * Has a view been intialized for this buffer?
		 */
		public boolean hasView() { 
			return this.hasView;
		}
		
		/*
		 * Sets the text of the buffer
		 */
		public void addText(String s) { 
			this.lines.clear();
			text = s;
			Line line = new Line();
			
			boolean stillLines = true;
			int start = 0;
			int end = 0;
			
			while(stillLines){
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
			}
			notifyObservers(text);
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() { 
			String str = "";
			for(Line line : lines) {
				str += line.toString();
			}
			return str;
		}
		
		/*
		 * Returns the root tag
		 */
		public HTMLTag getTag() {
			return tag;
		}
		
		/*
		 * Add a line to the line's list
		 */
		public void addLine(Line line) { 
			lines.add(line);
			
		}
		
		/*
		 * Checks to see if the tag is well-formed HTML
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
		
		/*
		 * Push to the undo stack
		 */
		public void addUndo(String state) {
			undoStack.push(state);
		}
		
		/*
		 * Push to the redo stack
		 */
		public void addRedo(String state) { 
			redoStack.push(state);
		}
		
		/*
		 * performs the undo operation on the buffer's text
		 */
		public void undo() { 
			addRedo(text);
			String temp = undoStack.pop();
			text = temp;
			addText(temp);
			setChanged();
			notifyObservers(text);
					
		}
		
		/*
		 * performs the redo operation on the buffer's text
		 */
		public void redo() {
			addUndo(text);
			String temp = redoStack.pop();
			text=temp;
			addText(temp);
			setChanged();
			notifyObservers(text);
		}
		
		/*
		 * Gets the file
		 */
		public File getFile() { 
			return sourceFile;
		}
		
		/*
		 * Sets the file
		 */
		public void setFile(String path) {
			sourceFile = new File(path);
		}
		
		/*
		 * Sets the view
		 */
		public void setView(boolean b) { 
			this.hasView = b;
		}

}
