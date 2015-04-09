package commands;
import io.FileHandler;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import buffer.Buffer;

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
			} catch(NullPointerException n) { 
				System.out.println("No file entered");
			}
			
		}
		
		try{
			File file= buff.getFile();
			FileHandler fh = new FileHandler(file);
			fh.writeToFile(buff);
			if(file != null){
				JOptionPane.showMessageDialog(null, "File has been saved.");
			}
		}catch(NullPointerException n){
			JOptionPane.showMessageDialog(null, "Your file could not be saved");
		}
		
	}
	
}
