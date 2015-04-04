package commands;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import buffer.Buffer;

/**
 * Command to insert tags into the current buffer
 * @author Team Bash-Browns
 *
 */
public class InsertCommand implements Command, Undoable {
	JTextArea textArea;
	String insertedText;
	int carPos = -1;
	Buffer buff;
	
	public InsertCommand(JTextArea text_area, Buffer buffer){
		String tag_name = new JOptionPane().showInputDialog(null, "Please enter the symbol of your desired tag");
		insertedText = tagNameToTag(tag_name);
		textArea = text_area;
		buff = buffer;
	}

	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
		String origText = textArea.getText();
		String newText = "";
		this.carPos = textArea.getCaretPosition();
		newText = origText.substring(0, carPos) + insertedText +
				origText.substring(carPos);
		buff.addText(newText);
		//textArea.setText(newText);
	}
	
	/**
	 * Helper function that takes the desired first tag, removes spaces,
	 * and makes a matching end tag for it
	 * 
	 * @param s Desired first tag's contents
	 * @return Resulting set of tags
	 */
	private String tagNameToTag(String s){
		String startTag = "<"+s+">";
		String endTag;
		String returnString;
		
		//Allows for arguments to be in the first tag but not the second
		if(s.contains(" ")){
			endTag = "</" + s.substring(0, s.indexOf(" ")) + ">";
		}else{
			endTag = "</" + s + ">";
		}
		returnString = startTag + endTag;
		return returnString;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		String origText = textArea.getText();
		if(this.carPos >= 0 && origText.substring(carPos, carPos+insertedText.length()).equals(insertedText)){
			
			String newText = origText.substring(0, carPos) + origText.substring(carPos+insertedText.length());
			//this.ix = -1;
			textArea.setText(newText);
		}
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		String origText = textArea.getText();
		if(this.carPos >= 0 && this.insertedText.length() > 0){
			
			String newText = origText.substring(0, carPos) + this.insertedText+ origText.substring(carPos);
			//this.ix = -1;
			textArea.setText(newText);
		}
		//execute();
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
