import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;


/**
 * @ Team Bash-Browns
 *
 */


public class EditorView extends JFrame implements Observer{
	
		HTMLEditor editor;
	
		//Components
		private	JMenuBar menuBar;
			
		// File + items
		private	JMenu file;
		private	JMenuItem New;
		private	JMenuItem open;
		private	JMenuItem save;
			
		//Edit + items
		private	JMenu edit;
		private	JMenuItem undo;
		private	JMenuItem redo;
		private	JMenuItem cut;
		private	JMenuItem copy ;
		private	JMenuItem paste;
			
		// Help + items
		private	JMenu help;
		private	JMenuItem readme;
			
		// Text area for the HTML
		private	JEditorPane textpane;
			
	public EditorView(String title){
		super(title);
		
		//Layout Manager
		setLayout(new BorderLayout());
		
		//Components
		menuBar = new JMenuBar();
		
		// File + items
		file = new JMenu("File");
		New = new JMenuItem("New");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		
		//Edit + items
		edit = new JMenu("Edit");
		undo = new JMenuItem("Undo");
		redo = new JMenuItem("Redo");
		cut = new JMenuItem("Cut");
		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		
		// Help + items
		help = new JMenu("Help");
		readme = new JMenuItem("Open Readme");
		
		// Text area for the HTML
		textpane = new JEditorPane(); 
		
		
		
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
		
		textpane.addKeyListener(buffedit);
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
			
			String filepath = JOptionPane.showInputDialog("Please input a path");
			OpenCommand OPEN = new OpenCommand( editor, filepath );
			OPEN.execute();
			
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
	KeyListener buffedit = new KeyListener(){

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}
