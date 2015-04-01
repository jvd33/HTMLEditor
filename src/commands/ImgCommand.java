package commands;

import editor.ImgView;
import buffer.Buffer;

/**
 * Command that instantiates a new image view for the buffer
 * @author joe
 *
 */
public class ImgCommand implements Command {
	
	private Buffer b;
	private ImgView img;
	
	public ImgCommand(Buffer buff) {
		b = buff;
	}
	
	@Override
	public void execute() {
		img = new ImgView(b);
		
	}

}
