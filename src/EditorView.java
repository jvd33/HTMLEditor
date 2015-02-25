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
		JMenuBar menuBar = new JMenuBar();
		
		// File + items
		
		JMenu file = new JMenu("File");
		JMenuItem New = new JMenuItem("New");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		
		//Edit + items
		
		JMenu edit = new JMenu("Edit");
		JMenuItem undo = new JMenuItem("Undo");
		JMenuItem redo = new JMenuItem("Redo");
		JMenuItem cut = new JMenuItem("Cut");
		JMenuItem copy = new JMenuItem("Copy");
		JMenuItem paste = new JMenuItem("Paste");
		
		// Shortcuts + items
		
		JMenu shortcut = new JMenu("Shortcuts");
		JMenuItem scview = new JMenuItem("View Shortcuts");
		JMenuItem scedit = new JMenuItem("Edit Shortcuts");
		
		// Text area for the HTML
		
		JTextArea textArea = new JTextArea();
		
		
		
		/* Added components to the frame
		------------------------------ */
		//This is the menu tabs
		
		menuBar.add(file);
		file.add(New);
		file.add(open);
		file.add(save);
		
		menuBar.add(edit);
		edit.add(undo);
		edit.add(redo);
		edit.addSeparator();
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		
		menuBar.add(shortcut);
		shortcut.add(scview);
		shortcut.add(scedit);
		
		
		this.add(menuBar, BorderLayout.NORTH);
		
		// this is the text area
		this.add(textArea, BorderLayout.CENTER);
		
		// Behavior of components
		
		
	}
	
}
