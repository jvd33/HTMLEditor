package commands;
import javax.swing.JTextArea;


/**
 * Command that handles indentation of multiple lines in the buffer
 * When the indent lines is pressed, it should add a tab to the start of
 * each line selected in the buffer
 * @author Team Bash Browns
 *
 */
public class IndentLinesCommand implements Command, Undoable {
	JTextArea textArea;
	private int start = -1;
	private int end = -1;
	private String preText = "";
	private String postText = "";
	
	public IndentLinesCommand(JTextArea text_area){
		textArea = text_area;
	}

	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
		this.start = textArea.getSelectionStart();
		int end = textArea.getSelectionEnd();
		String text = textArea.getSelectedText();
		this.preText = text;//textArea.getSelectedText();
		
		//go over each line from the start to the end of the selection and add a tab before it
		int i = start;
		while(i<end){
			if(i==0){					//if it's the first line in the buffer
				text = '\t' + text;
			}
			if(i>0){
				if(text.charAt(i-1)=='\n'){	//if a new line character is in the selection
					text = text.substring(0,i)+"\t"+text.substring(i) + "\t";
				}
			}
			i++;
		}
		this.postText = text;
		textArea.setText(textArea.getText().substring(0, this.start)+text+textArea.getText().substring(end));
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		/*int start = textArea.getSelectionStart();
		int end = textArea.getSelectionEnd();
		String text = textArea.getSelectedText();
		java.util.ArrayList<String> lines = new java.util.ArrayList<String>();
		
		int i = start;
		while(i < end){
			String thisLine = "";
			while(text.charAt(i)!='\n'){
				if(text.charAt(i)!='\t'){
					thisLine+=text.charAt(i);
				}
				i++;
			}
			lines.add(thisLine);
		}
		String newText = "";
		for(String s:lines){
			newText+=s+"\n";
		}
		text = textArea.getText();
		textArea.setText(text.substring(0,start)+newText+text.substring(end));*/
		if(this.start >= 0 && this.preText.length() > 0){
			String newText = this.textArea.getText();
			newText = newText.substring(0, this.start) + this.preText + newText.substring(this.start+this.postText.length());
			this.textArea.setText(newText);
		}
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		if(this.start >= 0 && this.postText.length() > 0){
			String newText = this.textArea.getText();
			newText = newText.substring(0, this.start) + this.postText + newText.substring(this.start+this.preText.length());
			this.textArea.setText(newText);
		}
		//execute();
	}
	
	/*public static void main(String args[]){
		JTextArea testMe = new JTextArea("testMe");
		IndentLinesCommand testCom = new IndentLinesCommand(testMe);
		System.out.println(testMe.getText());
		testCom.execute();
		System.out.println(testMe.getText());
	}*/
}

