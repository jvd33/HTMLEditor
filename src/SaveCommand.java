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
	Buffer buff;
	/**
	 * Creates a new New Concrete Command taking in HTMLEditor
	 * @param htmle
	 */	
	public SaveCommand(HTMLEditor htmle, Buffer b){
		this.htmlEditor = htmle;
		buff = b;
	}
	
	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	public void execute() {
			File file= buff.getFile();
			FileHandler fh = new FileHandler(file);
			HTMLParser parser = new HTMLParser(buff.text);
			
			buff.addTag(parser.parse());
			try{
				if(buff.checkHTML())
				{
					fh.writeToFile(buff);
				}
			}
			catch(IncorrectHTMLException e)
			{
				e.printStackTrace();
			}
			
			
		
	}

}
