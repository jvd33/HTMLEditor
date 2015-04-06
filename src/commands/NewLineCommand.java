package commands;
import javax.swing.JTextArea;

import buffer.Buffer;

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
	private Buffer buff;
	
	public NewLineCommand(JTextArea text_area, Buffer buffer){
		textArea = text_area;
		buff = buffer;
	}

	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {	
		// The whitespace that will be at the start of the new line
		String lineStart = "";
		
		// Carat position should be right after the \n character
		carPos = textArea.getCaretPosition();
		
		// Index of the \n character (I think)
		int i = carPos-1;
		String text = textArea.getText();

		// Put the index right before the \n character
		i--;
		int tagIndentation = 0;
		while(text.charAt(i)!='\n' && i >= 0){
			// Not loving this fix, but it accounts for the
			// first char being whitespace
			if(i==0 && Character.isWhitespace(text.charAt(i))){
				lineStart += text.charAt(i);
				break;
			}
			if(text.charAt(i)=='>'){
				// Will add or subtract 1 based 
				// on whether it's a start or end tag
				tagIndentation += determineTagIndentation(text, i);
			}
			i--;
		}
		
		// Adds tabs if there are more start tags than end tags
		// (Probably shouldn't do the opposite if vice versa?)
		for(int j = 0; j<tagIndentation; j++){
			lineStart += "\t";
		}
		i++;
		
		//Very long conditional; basically says "is this the start of a line?"
		while(Character.isWhitespace(text.charAt(i)) &&	text.charAt(i) != '\n' && i < text.length()){
			lineStart += text.charAt(i);
			i++;
		}
		
		this.insertedLine=lineStart;
		String newText = textArea.getText();
		buff.addText(newText.substring(0, carPos)+lineStart+newText.substring(carPos));
	}
	
	/**
	 * Checks to see if the editor should indent another
	 * level based on if a start tag is in the prior line
	 * 
	 * @param text Text of the document
	 * @param i Index of the '>' character of a tag
	 * @return the amount of tabs that should be added (this will be negative on end tags)
	 */
	private int determineTagIndentation(String text, int i){
		String tag = "";
		while(text.charAt(i)!='\n' && i > 0){
			tag = text.charAt(i) + tag;
			
			if(text.charAt(i)=='<'){
				if(isStartTag(tag))
					return 1;
				else
					return -1;
			}
			i--;
		}
		return 0;
	}
	
	/**
	 * Helper method that takes in a valid tag and checks if it
	 * is a start or end tag to determine the amount of
	 * indentation that should be added
	 * @param tag String of the tag in question
	 * @return Whether the tag is a start tag or an end
	 */
	private boolean isStartTag(String tag){
		if(tag.contains("</") || tag.contains("<img")){
			return false;
		}
		return true;
	}
	
	
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		String origText = textArea.getText();
		if(this.carPos >= 0 && origText.substring(carPos, carPos+insertedLine.length()).equals(insertedLine)){
			
			String newText = origText.substring(0, carPos) + origText.substring(carPos+insertedLine.length());
			this.carPos = -1;
			buff.addText(newText);
		}
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		String origText = textArea.getText();
		if(this.carPos >= 0 && this.insertedLine.length() > 0){
			
			String newText = origText.substring(0, carPos) + this.insertedLine+ origText.substring(carPos);
			//this.ix = -1;
			buff.addText(newText);
		}
		execute();
	}
}
