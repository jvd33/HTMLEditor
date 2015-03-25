package editor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import buffer.Buffer;
import parser.Checker;
import javax.swing.*;
import java.util.List;

/**
 * Creates the popup window when link command is called
 * @author joe
 *
 */
public class LinkView implements Observer {
	
	//Swing components
	private JFrame frame;
	private JTextArea area;
	private JMenuBar bar;
	private JMenu menu;
	
	//ActionListeners
	private ActionListener alpha;
	private ActionListener num;
	private ActionListener occu;
	
	//Menu Buttons
	private JMenuItem alphaM;
	private JMenuItem numO;
	private JMenuItem occuM;
	
	//Listing components
	private Checker c;
	private Behavior behavior;
	private List<String> links;
	
	/**
	 * Constructor
	 * @param b, the buffer to be observed
	 */
	public LinkView(Buffer b) { 
		b.addObserver(this);
		c = new Checker(b);
		links = c.getLinks();
		
		//Action listeners
		alpha = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				setSortBehavior(new AlphaSort(links));
			}
		};
		
		num = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				setSortBehavior(new DuplicateSort(links));
			}
		};
		
		occu = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				setSortBehavior(new OccurrenceSort(links));
			}
		};
		
		//menu items
		alphaM = new JMenuItem("Alphabetically");
		alphaM.addActionListener(alpha);
		numO = new JMenuItem("Number of Occurrences (Descending)");
		numO.addActionListener(num);
		occuM = new JMenuItem("By Occurrence (default)");
		occuM.addActionListener(occu);
		bar = new JMenuBar();
		menu = new JMenu("Sort");
		menu.add(alphaM);
		menu.add(numO);
		menu.add(occuM);
		bar.add(menu);
		
		//Frame behavior
		frame = new JFrame("Link View");
		frame.setLayout(new BorderLayout());
		frame.add(bar, BorderLayout.NORTH);
		frame.setVisible(true);
		frame.setSize(500, 500);
		area = new JTextArea();
		behavior = new OccurrenceSort(links);
		behavior.setLinks(links, area);
		frame.add(area);
		frame.setVisible(true);
	}
	
	/**
	 * Updates based on changes to the buffer
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		c = new Checker((Buffer)arg0);
		links = c.getLinks();
		behavior.setLinks(links, area);
		
	}
	
	/**
	 * Sets the sorting behavior of the view
	 * @param o, the behavior algorithm to pass in
	 */
	public void setSortBehavior(Behavior b) { 
		behavior = b;
		b.setLinks(links, area);
	}
	

}
