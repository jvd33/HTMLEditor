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
				JFrame frame = new EditorView("Team Bash-Browns HTML Editor");
				frame.setSize(500, 400);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
