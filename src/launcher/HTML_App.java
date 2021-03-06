package launcher;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import commands.Command;
import commands.OpenCommand;
import editor.EditorView;
import editor.HTMLEditor;


/**
 * 
 */

/**
 * Main file, run this to execute the app
 * @author Team Bash-Browns
 */
public class HTML_App {
	
	public static void main(String[] args){
		final String [] arg = args;
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				
				HTMLEditor editor = new HTMLEditor();
				JFrame frame = new EditorView("Team Bash-Browns HTML Editor", editor);
				frame.setSize(500, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				try{
					if(arg[0]!=(null)){
						File file = new File(arg[0]);
						Command open = new OpenCommand(editor, file.getPath());
						open.execute();
					}
				}catch(ArrayIndexOutOfBoundsException e){
					System.err.println("No Command Line arguments");
				}
			}
		});
	}

}
