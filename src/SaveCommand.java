import java.io.File;

import javax.swing.JFileChooser;

/**
 * 
 */

/**
 * Command in charge of saving the current buffer to a file
 * @author Bash-Browns
 *
 */
public class SaveCommand implements Command {
	Buffer buff;
	/**
	 * Creates a new New Concrete Command taking in the buffer in question
	 * @param b The buffer in question
	 */	
	public SaveCommand(Buffer b){
		buff = b;
	}
	
	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
			if(buff.getFile() == null) { 
				JFileChooser jfc = new JFileChooser();
				jfc.showDialog(null, "Save as");
				try { 
					String path = jfc.getSelectedFile().toString();
					System.out.println("SELECTED " + path);
					buff.setFile(path);
					buff.notifyObservers();
					Command save = new SaveCommand(buff);
					save.execute();
				} catch(NullPointerException n) { 
					System.out.println("No file entered");
				}
				
			}
			
			File file= buff.getFile();
			FileHandler fh = new FileHandler(file);
			fh.writeToFile(buff);
				
			
			
		
	}

}
