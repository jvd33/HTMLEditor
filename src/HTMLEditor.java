import java.util.List;
import java.util.Observable;
import java.io.File;
/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class HTMLEditor extends Observable{
		
		private Buffer activeBuffer;
		private List<Buffer> buffers;
		
		/*
		 * 
		 * Creates a Parser, parses buffer, checks html
		 * If html is correct, creates a filehandler
		 * Filehandler then writes buffer to file.
		 * 
		 */
		
		public HTMLEditor(){
			this.buffers = new java.util.ArrayList<Buffer>();
			this.activeBuffer = null;setChanged();
		}
		
		public void loadFile(File file){
			FileHandler fh = new FileHandler(file);
			Buffer newBuffer = fh.readFile(file);
			HTMLParser parser = new HTMLParser(newBuffer.toString());
			
			newBuffer.addTag(parser.parse());
			try{
				if(newBuffer.checkHTML())
					this.addBuffer(newBuffer);
			}
			catch(IncorrectHTMLException e)
			{
				e.printStackTrace();
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
