package editor;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CloseButton extends JButton{
	
	private JPanel tab;//tab to close
	
	/**
	 * Constructor
	 * 
	 * @param bstring String to go into the JButton constructor.
	 * @param intab   tab panel that will be closed.
	 */
	public CloseButton(String bstring, JPanel intab){
		
		super(bstring);
		tab = intab;
	}
	
	/**
	 * Getter for the tab that the button is in
	 * 
	 * @return the tab that the button is in
	 */
	public JPanel getTab(){
		
		return tab;
	}
}
