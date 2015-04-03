package commands;
import javax.swing.JTextArea;

/**
 * 
 */

/**
 * This class should work on auto-indenting
 * When the new line character is pressed, this should be called
 * It will check the line before it to see how much whitespace that line
 * begins with and add that whitespace to the beginning of the new line
 * @author Team Bash Browns
 *
 */
public class NewLineCommand implements Command, Undoable {
	
	private JTextArea textArea;
	private int carPos = -1;
	private String insertedLine;
	
	public NewLineCommand(JTextArea text_area){
		textArea = text_area;
	}

	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {	
		carPos = textArea.getCaretPosition();
		String text = textArea.getText();
		int i = carPos;
		while(text.charAt(i) !='\n'&&i>0){
			i--;
		}
		String lineStart ="\n";
		while(Character.isWhitespace(text.charAt(i)) && i < text.length()-1){
			lineStart+=text.charAt(i);
			i++;
		}
		this.insertedLine=lineStart;
		String newText = textArea.getText();
		textArea.setText(newText.substring(0, carPos)+lineStart+newText.substring(carPos));
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		String origText = textArea.getText();
		if(this.carPos >= 0 && origText.substring(carPos, carPos+insertedLine.length()).equals(insertedLine)){
			
			String newText = origText.substring(0, carPos) + origText.substring(carPos+insertedLine.length());
			this.carPos = -1;
			textArea.setText(newText);
		}
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		String origText = textArea.getText();
		if(this.carPos >= 0 && this.insertedLine.length() > 0){
			
			String newText = origText.substring(0, carPos) + this.insertedLine+ origText.substring(carPos);
			//this.ix = -1;
			textArea.setText(newText);
		}
		execute();
	}
	
	public static void main(String args[]){
		JTextArea testMe = new JTextArea("\ttestMe 1newLine");
		NewLineCommand testCom = new NewLineCommand(testMe);
		System.out.println(testMe.getText());
		testCom.execute();
		System.out.println(testMe.getText());
		testCom.undo();
		System.out.println(testMe.getText());
	}
}
