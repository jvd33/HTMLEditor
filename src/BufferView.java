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
		// TODO Auto-generated method stub
		
	}

		
}
