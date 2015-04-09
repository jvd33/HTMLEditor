package buffer;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;

import elements.HTMLTag;

/**
 * Button that allows for collapsing of HTMLTags
 * @author Team Bash-Browns
 *
 */
public class CollapseButton extends JButton{
	final private String COLLAPSED = "+";
	final private String COLLAPSABLE = "-";
	private int linenum;
	private HTMLTag tag;
	
	/**
	 * Constructor
	 * @param line, the line number its on
	 * @param t, the tag to attach
	 */
	public CollapseButton(int line, HTMLTag t){
		super();
		super.setText(COLLAPSABLE);
		super.setMargin(new Insets(0,0,0,0));
		super.setPreferredSize(new Dimension(15,0));
		tag = t;
		linenum = line;
		
	}
	
	/**
	 * Switch the state of the button
	 */
	public void switchText(){
		if(this.getText().equals(COLLAPSABLE)){
			this.setText(COLLAPSED);
		}
		else{
			this.setText(COLLAPSABLE);
		}
	}
	
	public int getLineNumber(){
		return  linenum;
	}
	
	public HTMLTag getTag(){
		return tag;
	}
	
}
