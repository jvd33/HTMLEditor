package editor;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import parser.Checker;
import commands.*;
import buffer.Buffer;
import buffer.BufferView;


/**
 * View in the MVC for the editor model
 * Acts as a user interface for the editor
 * @author Team Bash-Browns
 *
 */
public class EditorView extends JFrame implements Observer{
	
	HTMLEditor editor;
	CommandHandler CHO;
	
	//Components
	private JPanel panel;
	
	//tabCounter
	private int i;
	//private JButton closeBtn;
	
	private	JMenuBar menuBar;
	private JTabbedPane tabBar;
	
	// File + items
	private	JMenu file;
	private	JMenuItem New;
	private	JMenuItem open;
	private	JMenuItem save;
	
	// Edit + items
	private JMenu edit;
	private JMenuItem cut;
	private JMenuItem copy;
	private JMenuItem paste;
	private JMenuItem links;
	private JMenuItem img;
	
	/**
	 * Constructor for the editor view
	 * @param title
	 * @param htmleditor
	 */
	public EditorView(String title, HTMLEditor htmleditor){
		super(title);
		
		CHO= new CommandHandler();
		
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
		i = 0;
		/*closeBtn = new JButton("x");
		closeBtn.setSize(5, 5);
		closeBtn.addActionListener(close);*/

		
		// File + items
		file = new JMenu("File");
		New = new JMenuItem("New");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		
		//
		edit = new JMenu("Edit");
		cut = new JMenuItem("Cut");
		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		links = new JMenuItem("View URLs");
		img = new JMenuItem("View Images");
				
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
		edit.add(cut);
		cut.addActionListener(cutListener);
		edit.add(copy);
		copy.addActionListener(copyListener);
		edit.add(paste);
		paste.addActionListener(pasteListener);
		file.add(links);
		links.addActionListener(linksListener);
		file.add(img);
		img.addActionListener(imgListener);
				
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
			CHO.executeCommand(newFile);
		}
	};
	
	/*
	 * Open file listener
	 */
	ActionListener fileopen = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JFileChooser jfc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML Files", "html", "HTML");
			    jfc.setFileFilter(filter);
			jfc.showDialog(getParent(), "Select");
			try { 
				
				OpenCommand OPEN = new OpenCommand( editor, jfc.getSelectedFile().getPath() );
				if (jfc.getSelectedFile().getPath().contains(".html")){
					CHO.executeCommand(OPEN);
				}else{
					JOptionPane.showMessageDialog(null, "Sorry the file you tryed to open is not a '.html' file. Please try again.", "alert", JOptionPane.ERROR_MESSAGE);
				}
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
			String textInBuffView = "";
			try{
				textInBuffView = editor.getCurrentBuffer().text;
				Command buffState = new BuffStateCommand(currentBuffer, textInBuffView);
				CHO.executeCommand(buffState);
				
				if(editor.getCurrentBuffer().getFile() == null) { 
					JFileChooser jfc = new JFileChooser();
					jfc.showDialog(getParent(), "Save as");
					String path = jfc.getSelectedFile().toString();
					editor.getCurrentBuffer().setFile(path);
					editor.notifyObservers();
					//I don't think these last two lines are necessary but they will stay here until that is determined
					
					//Command save = new SaveCommand(currentBuffer);
					//CHO.executeCommand(save);
				}
				Checker checker = new Checker(currentBuffer);
				if(checker.check()){
					Command save = new SaveCommand(currentBuffer);
					CHO.executeCommand(save);
				}
				else{
					Object[] options = { "YES", "NO" };
					int answer = JOptionPane.showOptionDialog(null, "This file contains invalid HTML. \n \n Are you sure you wish to continue?", "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
					
					if(answer == JOptionPane.YES_OPTION){
						Command save = new SaveCommand(currentBuffer);
						CHO.executeCommand(save);
					}
					else{
						System.out.println("USER DID NOT SAVE THE FILE.");
					}
				}
				
				
			}catch(NullPointerException n){
				javax.swing.JOptionPane.showMessageDialog(null, "No file entered", "File Not Found", javax.swing.JOptionPane.ERROR_MESSAGE);
			}
		}
	};
	
	/*
	 * Copy command listener
	 */
	ActionListener copyListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			CHO.executeCommand(new CopyCommand(editor.getActiveView().getTextArea(), editor));
			System.out.println("Copy was called");
		}
	};	
	
	/*
	 * Cut command listener
	 */
	ActionListener cutListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			CHO.executeCommand(new CutCommand(editor.getActiveView().getTextArea(), editor));
			System.out.println("Cut was called");
		}
	};
	
	/*
	 * Paste command listener
	 */
	ActionListener pasteListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			CHO.executeCommand(new PasteCommand(editor.getActiveView().getTextArea(), editor));
			System.out.println("Paste was called");
		}
	};
	
	/*
	 * Link view listener
	 */
	
	ActionListener linksListener = new ActionListener() { 
		@Override
		public void actionPerformed(ActionEvent e) { 
			CHO.executeCommand(new LinkCommand(editor.getCurrentBuffer()));
			System.out.println("Links was called");
		}
	};
	
	/*
	 * Image view listener
	 */
	ActionListener imgListener = new ActionListener() { 
		@Override
		public void actionPerformed(ActionEvent e) { 
			CHO.executeCommand(new ImgCommand(editor.getCurrentBuffer()));
			System.out.println("Images was called");
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
	// close listener
	ActionListener close = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel temp = ((CloseButton)e.getSource()).getTab();
			int z = tabBar.indexOfTabComponent(temp);
		    if (i >= 0) {
		    	BufferView bv =null;
		    	if(tabBar.getComponentAt(z) instanceof BufferView){
		    		bv = (BufferView) tabBar.getComponentAt(z);
		    		
		    		try{
		    			Buffer toSave = bv.getBuffer();
		    			//If the user chooses to save
		    			if(JOptionPane.showConfirmDialog(null,"Would you like to save "+toSave.getFile().toString()+"?", "Save?", JOptionPane.YES_NO_OPTION) == 0){
		    				CHO.executeCommand(new SaveCommand(toSave));
		    			}
		    		}catch(NullPointerException npe){
		    			Buffer toSave = bv.getBuffer();
		    			//If the user chooses to save
		    			if(JOptionPane.showConfirmDialog(null,"Would you like to save as a new file?", "Save?", JOptionPane.YES_NO_OPTION) == 0){
		    				CHO.executeCommand(new SaveCommand(toSave));
		    			}
		    		}
		    	}
		      tabBar.remove(z);
		      i--;
		      editor.closeBuffer(bv.getBuffer());
		    }
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
				//bv.addKeyListener(buffedit);
				//panel.add(bv, BorderLayout.CENTER);
				bv.setVisible(true);
				if(b.hasView()) { 
					continue;
				}
				else {
					b.setView(true);
					try {
						  
						  
						  JPanel tab = new JPanel(new FlowLayout());
						  JLabel title = new JLabel(b.getFile().getName());
						  JButton closeBtn = new CloseButton("x", tab);
						  closeBtn.setSize(5, 5);
						  closeBtn.addActionListener(close);
						  tab.add(title, BorderLayout.WEST);
						  tab.add(closeBtn,BorderLayout.EAST);
						  tabBar.insertTab(null, null, bv, null, i);
						  tabBar.setTabComponentAt(i, tab );
						  i++;
						  
						 
						//tabBar.addTab(b.getFile().getName(), bv);
					} catch(NullPointerException e) { 
						JPanel tab = new JPanel(new FlowLayout());
						  JLabel title = new JLabel("New File");
						  JButton closeBtn = new CloseButton("x", tab);
						  closeBtn.setSize(5, 5);
						  closeBtn.addActionListener(close);
						  tab.add(title, BorderLayout.WEST);
						  tab.add(closeBtn,BorderLayout.EAST);
						  tabBar.insertTab(null, null, bv, null, i);
						  tabBar.setTabComponentAt(i, tab );
						  i++;
					}
				}
			}
		}
	}
}

