/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class InsertCommand implements Command {
	BufferView bv;
	Buffer buff;
	String tagName;
	public InsertCommand(Buffer buffer, String tag_name, int cursorPos){
		buff = buffer;
		tagName = tag_name;
	}

	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
		
		String origText = bv.getText();
		String newText = "";
		newText = origText.substring(0, bv.getCaretPosition()) + origText.substring(bv.getCaretPosition());
		bv.setText(newText);
		
	}

}
