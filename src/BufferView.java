import java.util.Observable;
import java.util.Observer;

import javax.swing.JEditorPane;


public class BufferView  extends JEditorPane implements Observer{

	private Buffer buffer; 
	
	public BufferView(Buffer b){
		this.buffer = b;
		this.setText(buffer.toString());
		buffer.addObserver(this);
	}
	
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if( arg0 instanceof Buffer && arg1 instanceof String){
			this.buffer = (Buffer) arg0;
			String text = (String) arg1;
			this.setText(text);
		}
		
	}

		
}
