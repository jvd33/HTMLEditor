import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class FileHandler {
		public FileHandler(File file){
			readFile(file);
		}
		//needs a save (writeToFile) method and a load (readFile) method
	
		public Buffer readFile(File file){
			Buffer buff = new Buffer();				// HTML Document Buffer
			
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String sCurrentLine = null;
				Line l = new Line();					// Temporary new line
				while ((sCurrentLine = br.readLine()) != null) { // Iterator that adds lines from file to buffer
					l.setText(sCurrentLine);
					buff.addLine(l);
					System.out.println(l.toString());// TEST
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
			
			return buff;
		}
		
		public File writeToFile(String parsedBuffer){
			return null;//place holder
		}
		
}
