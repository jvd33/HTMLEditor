package commands;
import io.FileHandler;

import java.io.File;
import parser.Checker;
import editor.HTMLEditor;
import buffer.Buffer;
import parser.HTMLParser;

/**
 * Opens a file and reads it into a buffer
 * @author Team Bash-Browns
 *
 */
public class OpenCommand implements Command {
	
	private HTMLEditor editor;
	private File file;
	
	/**
	 * Constructor for the open command
	 * An editor command that opens a new file and creates a buffer for it
	 * @param edit What editor the new buffer will be in
	 * @param filepath What file the command is opening
	 */
	public OpenCommand(HTMLEditor edit, String filepath) { 
		editor = edit;
		file = new File(filepath);
	}
	
	@Override
	public void execute() {
		FileHandler FH = new FileHandler(file);
		Buffer b = FH.readFile(file);
		for(Buffer buff : editor.getBuffers()) {
			if(buff.getFile().equals(file)) { 
				editor.setActiveBuffer(buff);
				System.out.println(editor.getBuffers());
				return;
			}
			else { 
				continue;
		
			}
			
		}
		b.addTag(new HTMLParser(b.text).parse());
		editor.addBuffer(b);
		editor.setActiveBuffer(b);
		editor.hasChanged();
		editor.notifyObservers();
	}
}
