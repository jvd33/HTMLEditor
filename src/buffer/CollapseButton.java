package buffer;

import java.awt.Insets;

import javax.swing.JButton;

public class CollapseButton extends JButton{
	final private String COLLAPSED = "+";
	final private String COLLAPSABLE = "--";
	private int linenum;
	
	public CollapseButton(int line){
		super();
		super.setText(COLLAPSABLE);
		super.setMargin(new Insets(1,1,1,1));
		super.setSize(25,25);
		linenum = line;
		
	}
	
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
	
	
}
