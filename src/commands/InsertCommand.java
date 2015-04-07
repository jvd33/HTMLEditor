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
	JOptionPane jop;

	int carPos = -1;
	Buffer buff;
	
	public InsertCommand(JTextArea text_area, Buffer buffer){
		jop = new JOptionPane();
		textArea = text_area;
		buff = buffer;
	}

	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
		String name = ""; // The name of the tag that is going to be inserted
		// Includes both the tag name and all attributes
		String contents = ""; // Text between start and end tags
		
		String[] main_option_list = {"a", "b", "img", "table", "list", "i", "header", "Cancel"};
		String optionName = makeOptionBox(main_option_list, "Which tag would you like to insert?", "Cancel");
		if(optionName == "b" || optionName == "i"){
			name = optionName;
		}else if(optionName == "header"){
			String headerNumber = "";
			int headerNumberVal = Integer.parseInt(headerNumber);
			while(headerNumberVal<1 || headerNumberVal >6){
				headerNumber = jop.showInputDialog("Please enter a header value between 1 and 6");
			}
			name = "h"+headerNumber;
		}else if(optionName == "a"){
			String linkName = jop.showInputDialog("Please enter the desired link");
			name = "a href=\""+linkName+"\"";
		}else if(optionName == "img"){
			String linkName = jop.showInputDialog("Please enter the desired location");
			name = "img src=\""+linkName+"\"";
		}else if(optionName == "list"){
			String[] list_type_options = {"Numbered", "Bulleted", "Dictionary"};
			String listType = makeOptionBox(list_type_options, "What type of list?", "Numbered");
			int listItemsNum = Integer.parseInt(jop.showInputDialog("Please enter the number of list items"));
			String listItems = "";
			for(int i = 0; i < listItemsNum; i++){
				listItems += tagNameToTag("li");
			}
			contents = listItems;
			if(listType == "Bulleted"){
				name = "ul";
			}else{
				name = "ol";
				if(listType == "Numbered"){
					name += " type=\"1\"";
				}else if(listType == "Dictionary"){
					name += " type=\"A\"";
				}
			}
		}else if(optionName == "table"){
			int tableRows = Integer.parseInt(jop.showInputDialog("Please enter the number of rows"));
			int tableCols = Integer.parseInt(jop.showInputDialog("Please enter the number of list columns"));
			String tablecontents = "";
			for(int i = 0; i < tableRows; i++){
				String rowcontents = "";
				for(int j = 0; j < tableCols; j++){
					System.out.println("Col Iteration");
					rowcontents += tagNameToTag("td");
				}
				tablecontents += tagNameToTag("tr", rowcontents);
			}
			name = "table";
			contents = tablecontents;
		}
		
		insertedText = tagNameToTag(name, contents);
		String origText = textArea.getText();
		String newText = "";
		this.carPos = textArea.getCaretPosition();
		newText = origText.substring(0, carPos) + insertedText +
				origText.substring(carPos);
		buff.addText(newText);
	}
	
	/**
	 * Dialogue prompt that pops up given a few options
	 * Makes the logic in here less messy
	 * @return String that has the desired option for which tag to select
	 */
	private String makeOptionBox(String[] options, String message, String defaultVal){
		String[] main_option_list = options;
		return main_option_list[jop.showOptionDialog(null,
				message,
				message,
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				defaultVal)];
	}
	
	/**
	 * Helper function that takes the desired first tag, removes spaces,
	 * and makes a matching end tag for it
	 * 
	 * @param s Desired first tag's contents
	 * @return Resulting set of tags
	 */
	private String tagNameToTag(String name){
		return tagNameToTag(name, "");
	}
	
	private String tagNameToTag(String name, String contents){
		String startTag = "<"+name+">";
		String endTag;
		String returnString;
		
		//Allows for arguments to be in the first tag but not the second
		if(name.contains(" ")){
			endTag = "</" + name.substring(0, name.indexOf(" ")) + ">";
		}else{
			endTag = "</" + name + ">";
		}
		returnString = startTag + contents + endTag;
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
}
