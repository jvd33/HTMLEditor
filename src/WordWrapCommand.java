import javax.swing.JTextArea;


public class WordWrapCommand implements Command {
	JTextArea textArea;
	
	/**
	 * Just toggles the word wrap setting
	 * @param buffer_view
	 */
	WordWrapCommand(JTextArea text_area){
		textArea = text_area;
	}
	
	@Override
	public void execute() {
		textArea.setLineWrap(!textArea.getLineWrap());
		System.out.println("Word wrap is now "+textArea.getLineWrap());
	}

}
