import java.util.Observable;
import java.util.Observer;
import javax.swing.JEditorPane;


public class BufferView extends JEditorPane implements Observer{

	private Buffer buffer;  //the buffer being observed
	
	/*
	 * Constructor
	 */
	public BufferView(Buffer b){
		b.addObserver(this);
		buffer = b;
		setText(b.toString());
	}
	
	
	/*
	 * (non-Javadoc) updates the view text
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if( arg0 instanceof Buffer && arg1 instanceof String){
			buffer = (Buffer) arg0;
			String text = (String) arg1;
			setText(text);
		}

	}

		
}
