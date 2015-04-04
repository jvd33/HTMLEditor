package commands;

import img.ImgView;
import buffer.Buffer;

/**
 * Command that instantiates a new image view for the buffer
 * @author joe
 *
 */
public class ImgCommand implements Command {
	
	private Buffer b;
	
	public ImgCommand(Buffer buff) {
		b = buff;
	}
	
	@Override
	public void execute() {
		new ImgView(b);
		
	}

}
