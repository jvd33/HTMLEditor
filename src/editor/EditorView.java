package editor;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import parser.Checker;
import commands.BuffStateCommand;
import commands.Command;
import commands.CommandHandler;
import commands.NewCommand;
import commands.OpenCommand;
import commands.SaveCommand;
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
	private JButton closeBtn;
	
	private	JMenuBar menuBar;
	private JTabbedPane tabBar;
	
	// File + items
	private	JMenu file;
	private	JMenuItem New;
	private	JMenuItem open;
	private	JMenuItem save;
	
	// Help + items
	private	JMenu help;
	private	JMenuItem readme;
	
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
		closeBtn = new JButton("x");
		closeBtn.setSize(5, 5);
		closeBtn.addActionListener(close);

		
		// File + items
		file = new JMenu("File");
		New = new JMenuItem("New");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		
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
			jfc.showDialog(getParent(), "Select");
			try { 
				
				OpenCommand OPEN = new OpenCommand( editor, jfc.getSelectedFile().getPath() );
				CHO.executeCommand(OPEN);
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
			int z = tabBar.indexOfTabComponent((Component) e.getSource());
		    if (i >= 0) {
		      tabBar.remove(z+i);
		      i--;
		      editor.closeBuffer();
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
						  tab.add(title, BorderLayout.WEST);
						  tab.add(closeBtn,BorderLayout.EAST);
						  tabBar.insertTab(null, null, bv, null, i);
						  tabBar.setTabComponentAt(i, tab );
						  System.out.println(i);
						  i++;
						  
						 
						//tabBar.addTab(b.getFile().getName(), bv);
					} catch(NullPointerException e) { 
						tabBar.addTab("New File", bv);
					}
				}
			}
		}
	}
}
