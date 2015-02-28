import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.*;


/**
 * @ Team Bash-Browns
 *
 */


public class EditorView extends JFrame implements ActionListener {
	
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
		
		// Help + items
		
		JMenu help = new JMenu("Help");
		JMenuItem readme = new JMenuItem("Open Readme");
		
		// Text area for the HTML
		
		JEditorPane textpane = new JEditorPane();
		
		
		
		/* Added components to the frame
		------------------------------ */
		//This is the menu tabs
		
		menuBar.add(file);
		file.add(New);
		New.addActionListener(filenew);
		file.add(open);
		open.addActionListener(fileopen);
		file.add(save);
		save.addActionListener(filesave);
		
		menuBar.add(edit);
		edit.add(undo);
		undo.addActionListener(editundo);
		edit.add(redo);
		redo.addActionListener(editredo);
		edit.addSeparator();
		edit.add(cut);
		cut.addActionListener(editcut);
		edit.add(copy);
		copy.addActionListener(editcopy);
		edit.add(paste);
		paste.addActionListener(editpaste);
		
		menuBar.add(help);
		help.add(readme);
		readme.addActionListener(helpreadme);
		
		this.add(menuBar, BorderLayout.NORTH);
		
		// this is the text pane
		
		this.add(textpane, BorderLayout.CENTER);
		
		
		
		
	}
	// Behavior of components
	
	ActionListener filenew = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("New Button pushed!");
			
		}
	};
	ActionListener fileopen = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("Open Button pushed!");
			
		}
	};
	ActionListener filesave = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("Save Button pushed!");
			
		}
	};
	ActionListener editundo = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("Undo Button pushed!");
			
		}
	};
	ActionListener editredo = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("Redo Button pushed!");
			
		}
	};
	ActionListener editcut = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("Cut Button pushed!");
			
		}
	};
	ActionListener editcopy = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("Copy Button pushed!");
			
		}
	};
	ActionListener editpaste = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("Paste Button pushed!");
			
		}
	};
	ActionListener helpreadme = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("readme Button pushed!");
			
		}
	};
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
