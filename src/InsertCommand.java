import java.io.File;

/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class InsertCommand implements Command {
	BufferView buffview;
	String insertedText;
	public InsertCommand(BufferView bv, String tag_name){
		insertedText = tagNameToTag(tag_name);
		this.buffview = bv;
	}

	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
		String origText = buffview.getText();
		String newText = "";
		newText = origText.substring(0, buffview.getCaretPosition()) + insertedText +
				origText.substring(buffview.getCaretPosition());
		buffview.setText(newText);
	}
	
	private String tagNameToTag(String s){
		s = "<"+s+"></"+s+">";
		return s;
	}
}
