import java.util.List;
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
		
		public Buffer() {
			this.lines = new ArrayList<Line>();
		}
		
		public void addTag(HTMLTag root) { 
			tag = root;
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
		

}
