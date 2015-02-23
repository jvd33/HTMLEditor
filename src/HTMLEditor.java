import java.util.List;
import java.io.File;
/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class HTMLEditor {
		private Buffer activeBuffer;
		private List<Buffer> buffers;
		
		/*
		 * 
		 * Creates a Parser, parses buffer, checks html
		 * If html is correct, creates a filehandler
		 * Filehandler then writes buffer to file.
		 * 
		 */
		public void saveBuffer(){
			FileHandler fh = new FileHandler();
			HTMLParser parser = new HTMLParser();
			HTMLChecker checker = new HTMLChecker();
			
			Buffer tempBuffer = parser.Parse(activeBuffer);
			
			if(checker.checkHTML(tempBuffer)){
				fh.writeToFile(tempBuffer);
				this.setActiveBuffer(tempBuffer);
			}
			
			
		}
		
		public void loadFile(File file){
			FileHandler fh = new FileHandler();
			HTMLChecker checker = new HTMLChecker();
			HTMLParser parser = new HTMLParser();
			
			Buffer newBuffer = fh.readFile(file);
			
			newBuffer = parser.Parse(newBuffer);
			//may do a try catch here
			if(checker.checkHTML(newBuffer)){
				this.addBuffer(newBuffer);
			}
		}
		
		public void addBuffer(Buffer buffer){
			buffers.add(buffer);
			activeBuffer = buffer;
		}
		public Buffer getCurrentBuffer(){
			return activeBuffer;
		}
		public List<Buffer> getBuffers(){
			return buffers;
		}
		
		//could take in index from list if that makes it better.
		public void setActiveBuffer(Buffer buffer){
			this.activeBuffer = buffer;
		}
		
		//only closes the active buffer since that will be the only one you can close.
		public void closeBuffer(){
			buffers.remove(activeBuffer);
			activeBuffer = buffers.get(0);
			
		}
}
