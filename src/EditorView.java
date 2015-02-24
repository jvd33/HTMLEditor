import java.awt.BorderLayout;
import javax.swing.*;


/**
 * @ Team Bash-Browns
 *
 */


public class EditorView extends JFrame{
	
	public EditorView(String title){
		super(title);
		
		//Layout Manager
		setLayout(new BorderLayout());
		
		//Components
		JTextField textField = new JTextField();
		JButton file = new JButton("File");
		JButton edit = new JButton("Edit");
		JButton shortcut = new JButton("Shortcuts");
		
		
		// Added components to the frame
		//------------------------------
		//This is the menu tabs
		JPanel menuTab = new JPanel();
		menuTab.add(file);
		menuTab.add(edit);
		menuTab.add(shortcut);
		this.add(menuTab, BorderLayout.NORTH);
		
		// this is the text field
		JPanel textTab = new JPanel();
		textTab.add(textField);
		this.add(textTab, BorderLayout.CENTER);
		
	}
	
}
