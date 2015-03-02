import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * 
 */

/**
 * @ Team Bash-Browns
 *
 */
public class HTML_App {
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				
				HTMLEditor editor = new HTMLEditor();
				JFrame frame = new EditorView("Team Bash-Browns HTML Editor", editor);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
