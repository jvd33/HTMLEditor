import java.io.File;
/**
 * 
 * @author Team Bash-Browns
 *
 */

/*
 * Opens a file and reads it into a buffer
 */
public class OpenCommand implements Command {
	
	private HTMLEditor editor;
	private File file;
	
	public OpenCommand(HTMLEditor edit, String filename) { 
		editor = edit;
		file = new File(filename);
	}
	
	@Override
	public void execute() {
		FileHandler FH = new FileHandler(file);
		Buffer b = FH.readFile(file);
		editor.addBuffer(b);
	}

}
