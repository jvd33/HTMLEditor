import javax.swing.JTextArea;

/**
 * 
 */

/**
 * @author Team Bash Browns
 *
 */
public class IndentLinesCommand implements Command {
	JTextArea textArea;
	
	IndentLinesCommand(JTextArea text_area){
		textArea = text_area;
	}

	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
		int start = textArea.getSelectionStart();
		int end = textArea.getSelectionEnd();
		String text = textArea.getText();

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
		textArea.setText(text);
	}

}
