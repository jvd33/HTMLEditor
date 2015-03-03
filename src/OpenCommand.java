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
		editor.addBuffer(b);
		editor.setActiveBuffer(b);
		editor.hasChanged();
		editor.notifyObservers();
	}

}
