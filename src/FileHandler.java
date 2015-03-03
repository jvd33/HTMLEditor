import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class FileHandler {
        
        // Destination to save file
		private File file;
		
		
		
		/**
		 * Constructor of FileHandler
		 * Takes in a file which serves as a destination to save in the future
		 * Converts the file's contents to a buffer
		 * 
		 * @param file	
		 */
		public FileHandler(File file){
			this.file = file;
		}
		
		/**
		 * Load method
		 * Returns the Buffer of a File that is provided in the arguments
		 * 
		 * @param file	The file to be turned into a buffer 
		 * @return		The new buffer object
		 */
		public Buffer readFile(File file){
			Buffer buff = new Buffer(file);				// HTML Document Buffer

			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String sCurrentLine = null;
				// adds lines from file to buffer
				while ((sCurrentLine = br.readLine()) != null) {
					Line l = new Line();	// Temporary new line
					System.out.println(sCurrentLine);
					l.setText(sCurrentLine);
					buff.addLine(l);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("Contents of file: " + buff);
			return buff;
		}
		/**
		 * Save method
		 * Takes in a buffer that has been edited and converts it into a file
		 * object
		 * 
		 * @param parsedBuffer	The buffer to be saved as a file
		 * @return				The File object that has just been written
		 */
		public File writeToFile(Buffer parsedBuffer){
			try(PrintWriter out = new PrintWriter(file)) {
				for(Line l : parsedBuffer.lines){
					out.print(l.toString());
				}
				out.close();
			}
			
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("Saved the file!");
			return file;
		}
		
		public static void main(String args[]){
			
			File file = new File("C:/Users/Joe/Desktop/SWEN262/test.txt");
			Buffer b= new Buffer(file);
			FileHandler fh = new FileHandler(file);
			b = fh.readFile(file);
			
			System.out.print(b.toString());
		}
}
