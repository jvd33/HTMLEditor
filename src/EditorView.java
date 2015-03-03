import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
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
		private JPanel panel;
		
		private	JMenuBar menuBar;
		private JTabbedPane tabBar;
			
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
		
		//Insert + items
		private JMenu insert;
		private JMenuItem header;
		private JMenuItem bold;
		private JMenuItem italics;
		private JMenuItem numberedList;
		private JMenuItem bulletedList;
		private JMenuItem dictList;
		private JMenuItem table;

			
		// Help + items
		private	JMenu help;
		private	JMenuItem readme;
			
			
	public EditorView(String title, HTMLEditor htmleditor){
		super(title);
		
		//The main panel of the document
		panel = new JPanel(new BorderLayout());
		//editor that is observed.
		editor = htmleditor;
		htmleditor.addObserver(this);
		//Layout Manager
		setLayout(new BorderLayout());
		
		//Components
		menuBar = new JMenuBar();
		tabBar = new JTabbedPane();
		
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
		
		//Insert + items
		insert = new JMenu("Insert");
		header = new JMenuItem("Header");
		bold = new JMenuItem("Bold");
		italics = new JMenuItem("Italics");
		numberedList = new JMenuItem("Numbered List");
		bulletedList = new JMenuItem("Bulleted List");
		dictList = new JMenuItem("Dictionary List");
		table = new JMenuItem("Table");

		
		// Help + items
		help = new JMenu("Help");
		readme = new JMenuItem("Open Readme");
		
		
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
		
		//Insert + items
		menuBar.add(insert);
		insert.add(header);
		//header.addActionListener(insertHeader);
		insert.add(bold);
		//bold.addActionListener(insertBold);
		insert.add(italics);
		//italics.addActionListener(insertItalics);
		insert.add(numberedList);
		//numberedList.addActionListener(insertNumberedList);
		insert.add(bulletedList);
		//bulletedList.addActionListener(insertBulletedList);
		insert.add(dictList);
		//dictList.addActionListener(insertDictList);
		insert.add(table);
		//table.addActionListener(insertTable);
		
		header.addActionListener(insertButton);
		bold.addActionListener(insertButton);
		italics.addActionListener(insertButton);
		numberedList.addActionListener(insertButton);
		bulletedList.addActionListener(insertButton);
		dictList.addActionListener(insertButton);
		table.addActionListener(insertButton);


		
		
		menuBar.add(help);
		help.add(readme);
		readme.addActionListener(helpreadme);
		
		this.add(menuBar, BorderLayout.NORTH);
		
		// For tab items 
		
		try{
		panel.add(tabBar,BorderLayout.CENTER);
		
		}catch(java.lang.NullPointerException e){
			
		}
		
		this.add(panel, BorderLayout.CENTER);
			
	}
	// Behavior of components
	
	/*
	 * New file listener
	 */
	ActionListener filenew = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("New Button pushed!");
			Command newFile = new NewCommand(editor, null);
			newFile.execute();
		}
	};
	
	/*
	 * Open file listener
	 */
	ActionListener fileopen = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JFileChooser jfc = new JFileChooser();
			jfc.showDialog(getParent(), "Select");
			try { 
				OpenCommand OPEN = new OpenCommand( editor, jfc.getSelectedFile().getPath() );
				OPEN.execute();
			} catch(NullPointerException n) { 
				return;
			}
			
			
			
		}
	};
	
	/*
	 * Save file listener
	 */
	ActionListener filesave = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("Save Button pushed!");
			Buffer currentBuffer = editor.getCurrentBuffer();
			String textInBuffView = editor.getCurrentBuffer().text;
			Command buffState = new BuffStateCommand(currentBuffer, textInBuffView);
			buffState.execute();
			if(editor.getCurrentBuffer().getFile() == null) { 
				JFileChooser jfc = new JFileChooser();
				jfc.showDialog(getParent(), "Save as");
				try { 
					String path = jfc.getSelectedFile().toString();
					editor.getCurrentBuffer().setFile(path);
					editor.notifyObservers();
					Command save = new SaveCommand(editor, currentBuffer);
					save.execute();
				} catch(NullPointerException n) { 
					System.out.println("No file entered");
				}
				
			}
			Command save = new SaveCommand(editor, currentBuffer);
			save.execute();
		}
	};
	
	/*
	 * Undo listener
	 */
	ActionListener editundo = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("Undo Button pushed!");
			Command undo = new UndoCommand(editor.getCurrentBuffer());
			undo.execute();
		}
	};
	
	/*
	 * redo listener
	 */
	ActionListener editredo = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("Redo Button pushed!");
			Command redo = new RedoCommand(editor.getCurrentBuffer());
			redo.execute();
		}
	};
	
	/*
	 * Cut listener
	 */
	ActionListener editcut = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("Cut Button pushed!");
			Command cut = new CutCommand("");
			cut.execute();
		}
	};
	
	/*
	 * Copy listener
	 */
	ActionListener editcopy = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("Copy Button pushed!");
			//Command copy = new CopyCommand();
			//copy.execute();
		}
	};
	
	/*
	 * Paste listener
	 */
	ActionListener editpaste = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("Paste Button pushed!");
			//Command paste = new PasteCommand();
			//paste.execute();
		}
	};
	
	/*
	 * ReadMe listener
	 */
	ActionListener helpreadme = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("readme Button pushed!");
			//Command readmecommand = new ReadMeCommand();
			//readmecommand.execute();
		}
	};
	
	/*
	 * Insert listener
	 */
	ActionListener insertButton = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("One of the insert buttons has been pressed");
			//Command insertcommand = new InsertCommand("TAG DATA HERE");
			//insertcommand.execute();
		}
	};
	
	/*
	 * Buffer change listener
	 */
	KeyListener buffedit = new KeyListener(){

		@Override
		public void keyTyped(KeyEvent e) {
			Buffer currentBuffer = editor.getCurrentBuffer();
			String textInBuffView = ((BufferView) e.getSource()).getText();
			//currentBuffer.addUndo(textInBuffView);
			Command buffState = new BuffStateCommand(currentBuffer, textInBuffView);
			buffState.execute();
			//System.out.println(textInBuffView);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
	};
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if( arg instanceof ArrayList && o instanceof HTMLEditor){
			editor = (HTMLEditor) o;
			List<Buffer> list = (ArrayList<Buffer>) arg;
			for(Buffer b : list){
				
				BufferView bv = new BufferView(b);
				editor.setActiveView(bv);
				bv.addKeyListener(buffedit);
				panel.add(bv, BorderLayout.CENTER);
				bv.setVisible(true);
				if(b.hasView()) { 
					continue;
				}
				else {
					b.setView(true);
					try { 
						tabBar.addTab(b.getFile().getName(), bv);
					} catch(NullPointerException e) { 
						tabBar.addTab("New File", bv);
					}
				}
			}
		}
		
		
	}
	
}

