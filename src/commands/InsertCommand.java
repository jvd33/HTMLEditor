package commands;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class InsertCommand implements Command, Undoable {
	JTextArea textArea;
	String insertedText;
	int ix = -1;
	public InsertCommand(JTextArea text_area){
		String tag_name = new JOptionPane().showInputDialog(null, "Please enter the symbol of your desired tag");
		insertedText = tagNameToTag(tag_name);
		textArea = text_area;
	}

	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
		String origText = textArea.getText();
		String newText = "";
		this.ix = textArea.getCaretPosition();
		newText = origText.substring(0, ix) + insertedText +
				origText.substring(ix);
		textArea.setText(newText);
	}
	
	private String tagNameToTag(String s){
		s = "<"+s+"></"+s+">";
		return s;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		String origText = textArea.getText();
		if(this.ix >= 0 && origText.substring(ix, ix+insertedText.length()).equals(insertedText)){
			
			String newText = origText.substring(0, ix) + origText.substring(ix+insertedText.length());
			this.ix = -1;
			textArea.setText(newText);
		}
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		execute();
	}
	
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JTextArea testMe = new JTextArea("Test me");
		InsertCommand ic = new InsertCommand(testMe);
		System.out.println(testMe.getText());
		ic.execute();
		System.out.println(testMe.getText());
		ic.undo();
		System.out.println(testMe.getText());
		ic.undo();
		System.out.println(testMe.getText());
		
	}*/
}
