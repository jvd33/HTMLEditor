package buffer;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import java.awt.color.*;

import commands.BuffStateCommand;
import commands.Command;
import commands.CommandHandler;
import commands.CopyCommand;
import commands.IndentLinesCommand;
import commands.InsertCommand;
import commands.NewLineCommand;
import commands.RedoCommand;
import commands.SaveCommand;
import commands.UndoCommand;
import commands.WordWrapCommand;


/**
 * The graphic interface for a single buffer within the editor
 * This class should act as the view for a buffer
 * @author Team Bash-Browns
 *
 */
public class BufferView extends JPanel implements Observer{
	

	
	
	private Buffer buffer;  	// the buffer being observed
	private CommandHandler commandHandler;
	private boolean autoindent; // whether or not to indent
	
	//GUI Elements
	private JTextArea textArea;
	private JToolBar toolBar;
	private JButton undo;
	private JButton redo;
	private JButton wordwrap;
	private JButton newline;
	private JButton inserttag;
	private JButton multipleindent;
	private JPanel sidepanel;
    private GridLayout gl;
    private JPanel collapsepanel;
    
	/**
	 * MVC View for the buffer 
	 * @param b Which buffer represents the model
	 */
	public BufferView(Buffer b){
		textArea = new JTextArea();
		textArea.setDragEnabled(true);

		// Variable initialization
		getTextArea().setLineWrap(true);
		autoindent = true;
		b.addObserver(this);
		commandHandler = new CommandHandler();
		
		this.setLayout(new BorderLayout());
		buffer = b;
		getTextArea().setText(b.toString());
		
        gl = new GridLayout(0,2,0,0);
		gl.setHgap(0);
	    gl.setVgap(0);
	    collapsepanel = new JPanel(gl);
		sidepanel = new JPanel(new BorderLayout());//panel for collapsing buttons
		sidepanel.add(collapsepanel, BorderLayout.NORTH);
		
		if(buffer.getTag() != null){
			this.updateCollapsePanel();
		}
		
		
		//Buffer Tool Bar
		toolBar = new JToolBar();
		
		//Buttons for the Tool Bar
		undo = new JButton("Undo");
		redo = new JButton("Redo");
		wordwrap = new JButton("Toggle Word-Wrap");
		newline = new JButton("Toggle AutoIndent");
		inserttag = new JButton("Insert Tag...");
		multipleindent = new JButton("Indent lines...");
		
		//adding buttons and action listeners
		toolBar.add(undo);
		undo.addActionListener(undoListener);
		toolBar.add(redo);
		redo.addActionListener(redoListener);
		toolBar.add(wordwrap);
		wordwrap.addActionListener(wordWrapListener);
		toolBar.add(newline);
		newline.addActionListener(autoIndentListener);
		toolBar.add(inserttag);
		inserttag.addActionListener(insertListener);
		toolBar.add(multipleindent);
		multipleindent.addActionListener(indentLinesListener);
		this.add(toolBar, BorderLayout.NORTH);
		this.add(textArea, BorderLayout.CENTER);
		this.add(sidepanel, BorderLayout.WEST);
		textArea.addKeyListener(buffedit);
	}
	

	/*
	 * Buffer change listener
	 */
	KeyListener buffedit = new KeyListener(){

		@Override
		public void keyTyped(KeyEvent e) {
			Command newCommand = null;
			if(e.getKeyChar()=='\n' && autoindent){
				// Auto-indent
				newCommand = new NewLineCommand(textArea,buffer);
				newCommand.execute();
			}
			
			// Save-state
			Command buffState = new BuffStateCommand(buffer, textArea.getText());
			buffState.execute();
			
			//edit lines
			updateCollapsePanel();
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
	};
	
	
	/*
	 * Undo button listener
	 */
	ActionListener undoListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			commandHandler.executeCommand(new UndoCommand(buffer));
		}
	};	
	
	/*
	 * Redo button listener
	 */
	ActionListener redoListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			commandHandler.executeCommand(new RedoCommand(buffer));
		}
	};
	
	/*
	 * Word-Wrap toggle listener
	 */
	ActionListener wordWrapListener = new ActionListener(){
		private Color GREEN;

		@Override
		public void actionPerformed(ActionEvent e) {
			commandHandler.executeCommand(new WordWrapCommand(textArea));
			if (textArea.getLineWrap() == true){
				wordwrap.setBackground(GREEN);
			}else{
				wordwrap.setBackground(null);
			}
		}
	};
	
	/*
	 * Insert tag listener
	 */
	ActionListener insertListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			commandHandler.executeCommand(new InsertCommand(textArea,buffer));
		}
	};
	
	/*
	 * Toggle Auto-Indent listener
	 */
	ActionListener autoIndentListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			autoindent = !autoindent;
			System.out.println("Auto-indent status: "+autoindent);
		}
	};	
	
	/*
	 * Indent lines listener
	 */
	ActionListener indentLinesListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			commandHandler.executeCommand(new IndentLinesCommand(textArea));
		}
	};
	
	public Buffer getBuffer(){
		return buffer;
	}

	/**
	 * Getter method to enable operations on the text area
	 * Helps find where the caret is in the text area and toggle the word wrap
	 * @return the textArea
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 * (non-Javadoc) updates the view text
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if( arg0 instanceof Buffer && arg1 instanceof String){
			buffer = (Buffer) arg0;
			String newText = (String) arg1;
			String origText = this.textArea.getText();
			int posBeforeUpdate = getTextArea().getCaretPosition();
			getTextArea().setText(newText);
			int posAfterUpdate = posBeforeUpdate + (newText.length() - origText.length());
			getTextArea().setCaretPosition(posAfterUpdate);
		}

	}
	
	ActionListener collapse = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		} 	
	};
	
	private void updateCollapsePanel(){
		JButton CollapseButton = new JButton();
		CollapseButton.addActionListener(collapse);
		collapsepanel.removeAll();
		int bsize = buffer.getNumLines();
		String linenum;
		for(int x = 0; x<=bsize; x++){
			linenum = ""+(x+1);
			collapsepanel.add(new JLabel(linenum));
			collapsepanel.add(new CollapseButton(x+1));
		}
	}

		
}
