import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;


public class BufferView extends JPanel implements Observer{
	
	private Buffer buffer;  //the buffer being observed
	
	//GUI Elements
	private JTextArea textArea;
	private JToolBar toolBar;
	private JButton save;
	private JButton undo;
	private JButton redo;
	private JButton cut;
	private JButton copy;
	private JButton paste;
	private JButton wordwrap;
	private JButton newline;
	private BufferView bv;
	
	/*
	 * Constructor
	 */
	public BufferView(Buffer b){
		bv = this;
		textArea = new JTextArea();
		this.setLayout(new BorderLayout());
		b.addObserver(this);
		buffer = b;
		getTextArea().setText(b.toString());
		getTextArea().setLineWrap(false); // By default
		
		
		toolBar = new JToolBar();
		save = new JButton("Save");
		undo = new JButton("Undo");
		redo = new JButton("Redo");
		cut = new JButton("Cut");
		copy = new JButton("Copy");
		paste = new JButton("Paste");
		wordwrap = new JButton("Toggle Word-Wrap");
		newline = new JButton("Toggle AutoIndent");
		
		toolBar.add(save);
		save.addActionListener(buttonListener);
		toolBar.add(undo);
		undo.addActionListener(buttonListener);
		toolBar.add(redo);
		redo.addActionListener(buttonListener);
		toolBar.add(cut);
		cut.addActionListener(buttonListener);
		toolBar.add(copy);
		copy.addActionListener(buttonListener);
		toolBar.add(paste);
		paste.addActionListener(buttonListener);
		toolBar.add(wordwrap);
		wordwrap.addActionListener(buttonListener);
		toolBar.add(newline);
		newline.addActionListener(buttonListener);

		this.add(toolBar, BorderLayout.NORTH);
		this.add(textArea, BorderLayout.CENTER);
	}
	
	
	ActionListener buttonListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {			
			JButton sourceButton = (JButton) e.getSource();
			String sourceText = sourceButton.getText();
			new BuffStateCommand(buffer, textArea.getText()).execute();

			Command newCommand = null;
			if(sourceText=="Save"){
				newCommand = new SaveCommand(buffer);
				
			}else if(sourceText=="Undo"){
				newCommand = new UndoCommand(buffer);
				
			}else if(sourceText=="Redo"){
				newCommand = new RedoCommand(buffer);
				
			}else if(sourceText=="Cut"){
				//newCommand = new CutCommand("");
				System.out.println("Use Ctrl+X for now");
				
			}else if(sourceText=="Copy"){
				//newCommand = new CopyCommand();
				System.out.println("Use Ctrl+C for now");
				
			}else if(sourceText=="Paste"){
				//newCommand = new PasteCommand();
				System.out.println("Use Ctrl+V for now");
				
			}else if(sourceText=="Toggle Word-Wrap"){
				newCommand = new WordWrapCommand(textArea);
				
			}else if(sourceText=="Toggle AutoIndent"){
				newCommand = new NewLineCommand(textArea);
				
			}else if(sourceText=="Insert"){
				//newCommand = new InsertCommand(textArea);
				System.out.println("NOT IMPLEMENTED YET");
				
			}else{
				System.out.println("Unidentified command in BufferView");
				return;
			}
			System.out.println("The " + sourceText + "button was pressed in BufferView");
			try{
				newCommand.execute();
			}catch(NullPointerException n) {
				n.printStackTrace();
			}
			
		}
	};
	/**
	 * @return the textArea
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 * @param textArea the textArea to set
	 */
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	/*
	 * (non-Javadoc) updates the view text
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if( arg0 instanceof Buffer && arg1 instanceof String){
			buffer = (Buffer) arg0;
			String text = (String) arg1;
			getTextArea().setText(text);
		}

	}

		
}
