import java.util.List;
import java.util.Observable;
import java.io.File;
/**
 * 
 */

/**
 * The model of an editor
 * Serves as the container for the buffers
 * @author Team Bash-Browns
 *
 */
public class HTMLEditor extends Observable{
		
		private Buffer activeBuffer;
		private BufferView bufferView;
		private List<Buffer> buffers;
		
		/**
		 * Constructor for a new HTMLEditor
		 * Creates a new empty list of buffers
		 */
		public HTMLEditor(){
			this.buffers = new java.util.ArrayList<Buffer>();
			this.activeBuffer = null;
		}
		
		/**
		 * Loads the file and reads it into the editor
		 * @param file 
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

		/**
		 * Adds a buffer to the list of active buffers
		 * @param buffer adds a buffer into the editor
		 */
		public void addBuffer(Buffer buffer){
			buffers.add(buffer);
			setActiveBuffer(buffer);
			setChanged();
			notifyObservers(buffers);
		}
		
		/**
		 * Sets the current active buffer view
		 * @param bv The editor's new current buffer view
		 */
		public void setActiveView(BufferView bv) { 
			bufferView = bv;
			
		}
		
		/**
		 * Returns the active buffer view
		 * @return The active buffer view within the editor
		 */
		public BufferView getActiveView() { 
			return bufferView;
		}
		
		/**
		 * Gets the current buffer
		 * @return The active buffer within the editor
		 */
		public Buffer getCurrentBuffer(){
			return activeBuffer;
		}
		
		/**
		 * Get ALL the buffers
		 * @return The list of all buffers within the editor
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
