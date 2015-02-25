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
		public File file;
		public FileHandler(File file){
			this.file = file;
			writeToFile(readFile(file));
		}
		//needs a save (writeToFile) method and a load (readFile) method
	
		public Buffer readFile(File file){
			Buffer buff = new Buffer();				// HTML Document Buffer
			
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String sCurrentLine = null;
				while ((sCurrentLine = br.readLine()) != null) { // Iterator that adds lines from file to buffer
					Line l = new Line();					// Temporary new line
					l.setText(sCurrentLine);
					buff.addLine(l);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("Loaded the file!");
			return buff;
		}
		
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
			return file;//place holder
		}
		
}
