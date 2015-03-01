import java.io.File;

/**
 * 
 */

/**
 * @author Bash-Browns
 *
 */
public class SaveCommand implements Command {
	HTMLEditor htmlEditor;
	/**
	 * Creates a new New Concrete Command taking in HTMLEditor
	 * @param htmle
	 */	
	public SaveCommand(HTMLEditor htmle){
		this.htmlEditor = htmle;
	}
	
	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
			Buffer activeBuffer = htmlEditor.getCurrentBuffer();
			File file= activeBuffer.getFile();
			FileHandler fh = new FileHandler(file);
			HTMLParser parser = new HTMLParser(activeBuffer.toString());
			
			
			Buffer tempBuffer = new Buffer(file);
			tempBuffer.addTag(parser.parse());
			try{
				if(tempBuffer.checkHTML())
				{
					fh.writeToFile(activeBuffer);
				}
			}
			catch(IncorrectHTMLException e)
			{
				e.printStackTrace();
			}
			
			
		
	}

}
