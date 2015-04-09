package io;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import buffer.Buffer;

/**
 * 
 */

/**
 * The class that loads a file and converts it into a buffer
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
				String bufferText = "";
				// adds lines from file to buffer
				while ((sCurrentLine = br.readLine()) != null) {
					bufferText += sCurrentLine+'\n';
				}
				buff.addText(bufferText);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
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
				out.print(parsedBuffer.text);
				out.close();
			}
			
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch(NullPointerException e){
				System.out.println("file not found");
			}
			System.out.println("Saved the file!");
			return file;
		}
		
}
