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
		
		//private List<Observer> Observers;
		private File sourceFile;
		private Stack<String> undoStack;
		private Stack<String> redoStack;
		private HTMLTag tag; //root tag for buffer
		private boolean hasView;
		public List<Line> lines;//list of all lines in the buffer
		public String text;
		
		public Buffer(File f) {
			//Observers = new ArrayList<Observer>();
			sourceFile = f;
			undoStack = new Stack<String>();
			redoStack = new Stack<String>();
			this.lines = new ArrayList<Line>();
			this.hasView = false;
		}
		
		//public List<Observer> getObservers(){
		//	return Observers;
		//}
		
		public void addTag(HTMLTag root) { 
			tag = root;
		}
		
		public boolean hasView() { 
			return this.hasView;
		}
		
		public void addText(String s) { 
			this.lines.clear();
			//addUndo(s);
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
		
		
		public void addUndo(String state) {
			undoStack.push(state);
		}
		
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
		
		public File getFile() { 
			return sourceFile;
		}
		
		public void setFile(String path) {
			sourceFile = new File(path);
		}
		
		
		public void setView(boolean b) { 
			this.hasView = b;
		}
		
		//for science...
		public static void main(String args []){
			
			/*HTMLTag tag = new HTMLTag("<html>");
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
			}*/
		}

}
