package buffer;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import java.awt.color.*;
import commands.*;
import elements.DocumentElement;
import elements.HTMLTag;
import elements.TextElement;


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
		wordwrap.setBackground(Color.GREEN);
		wordwrap.setOpaque(true);
		wordwrap.setBorderPainted(false);
		
		newline = new JButton("Toggle AutoIndent");
		newline.setBackground(Color.GREEN);
		newline.setOpaque(true);
		newline.setBorderPainted(false);
		
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
				/*newCommand = new NewLineCommand(textArea,buffer);
				newCommand.execute();*/
				commandHandler.executeCommand(new NewLineCommand(textArea,buffer));
			}
			
			// Save-state
			/*Command buffState = new BuffStateCommand(buffer, textArea.getText());
			buffState.execute();*/
			commandHandler.executeCommand(new BuffStateCommand(buffer, textArea.getText()));
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
		public void actionPerformed(ActionEvent e) {
			commandHandler.executeCommand(new WordWrapCommand(textArea));
			if (textArea.getLineWrap() == true){
				wordwrap.setBackground(Color.GREEN);
				wordwrap.setOpaque(true);
				wordwrap.setBorderPainted(false);
			}else{
				wordwrap.setBackground(null);
				wordwrap.setOpaque(false);
				wordwrap.setBorderPainted(true);
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
		public void actionPerformed(ActionEvent e) {
			autoindent = !autoindent;
			System.out.println("Auto-indent status: "+autoindent);
			if (autoindent == true){
				newline.setBackground(Color.GREEN);
				newline.setOpaque(true);
				newline.setBorderPainted(false);
			}else{
				newline.setBackground(null);
				newline.setOpaque(false);
				newline.setBorderPainted(true);
			}
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
			CollapseButton cb = (CollapseButton)e.getSource();
			cb.switchText();
			cb.getTag().collapse();
			commandHandler.executeCommand(new CollapseCommand(buffer, buffer.getTag().print()));
			
		} 	
	};
	
	/**
	 * Updates the side panel when new tags are added
	 */
	private void updateCollapsePanel(){
		JButton cb;
		collapsepanel.removeAll();
		List<DocumentElement> list = new ArrayList<DocumentElement>();
		list.add(buffer.getTag());
		list.addAll(buffer.getTag().getChildren());
		
		
		CollapseButton [] barray = new  CollapseButton [textArea.getLineCount()];
		HTMLTag linetag = null;
		boolean newlinefound = false;
		int arindex = 0;
			
		for(DocumentElement de: list){
			if(de instanceof HTMLTag){
				linetag = (HTMLTag)de;
			}
			else if(de.print().contains("\n")){
					newlinefound = true;
			}
			
			if(newlinefound){
				if(linetag != null){
					cb = new CollapseButton(arindex, linetag);
					cb.addActionListener(collapse);
					barray[arindex]= (CollapseButton) cb;
				}
				else{
					barray[arindex]= null;
				}
				arindex++;
				newlinefound = false;
				linetag = null;
			}
				
		}
			
			
		
		
		String linenum;
		for(int x = 0; x<textArea.getLineCount(); x++){
			linenum = ""+(x+1);
			collapsepanel.add(new JLabel(linenum));
			
			if(barray[x]!=null){
				collapsepanel.add(barray[x]);
			}
			else{
				collapsepanel.add(new JLabel(" "));
			}
			
		}
	}

		
}
