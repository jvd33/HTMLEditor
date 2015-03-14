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
		private BufferView bufferView;
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
			this.activeBuffer = null;
		}
		
		/*
		 * Loads the file and reads it into the editor
		 */
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
			setChanged();
			notifyObservers(buffers);
		}
		
		/*
		 * Adds a buffer to the list of active buffers
		 */
		public void addBuffer(Buffer buffer){
			buffers.add(buffer);
			setActiveBuffer(buffer);
			setChanged();
			notifyObservers(buffers);
		}
		
		/*
		 * Sets the current active buffer view
		 */
		public void setActiveView(BufferView bv) { 
			bufferView = bv;
			
		}
		
		/*
		 * Returns the active buffer view
		 */
		public BufferView getActiveView() { 
			return bufferView;
		}
		
		/*
		 * Gets the current buffer
		 */
		public Buffer getCurrentBuffer(){
			return activeBuffer;
		}
		
		/*
		 * Get ALL the buffers
		 */
		public List<Buffer> getBuffers(){
			return buffers;
		}
		
		/*
		 * Set the active buffer
		 */
		public void setActiveBuffer(Buffer buffer){
			this.activeBuffer = buffer;
			setChanged();
			notifyObservers(buffers);
		}
		
		/*
		 * only closes the active buffer since that will be the only one you can close.
		 */
		public void closeBuffer(){
			buffers.remove(activeBuffer);
			activeBuffer = buffers.get(0);
			setChanged();
			notifyObservers(buffers);
		}
}
