package links;

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
 * @author Team Bash-Browns
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
	private ActionListener occu;
	private ActionListener refresh;
	
	//Menu Buttons
	private JMenuItem alphaM;
	private JMenuItem occuM;
	private JMenuItem refreshB;
	
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
		
		//action listeners
		refresh = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) { 
				area.setText("");
				behavior.setLinks(links, area);
			}
		};
		
		alpha = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				setSortBehavior(new AlphaSort());
			}
		};
		
		occu = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				setSortBehavior(new OccurrenceSort());
			}
		};
		
		//menu items
		alphaM = new JMenuItem("Alphabetically");
		alphaM.addActionListener(alpha);
		occuM = new JMenuItem("By Occurrence (default)");
		occuM.addActionListener(occu);
		bar = new JMenuBar();
		refreshB = new JMenuItem("Refresh");
		refreshB.addActionListener(refresh);
		menu = new JMenu("Options");
		menu.add(alphaM);
		menu.add(occuM);
		menu.add(refreshB);
		bar.add(menu);
		
		//Frame behavior
		frame = new JFrame("Link View");
		frame.setLayout(new BorderLayout());
		frame.add(bar, BorderLayout.NORTH);
		frame.setVisible(true);
		frame.setSize(500, 500);
		area = new JTextArea();
		behavior = new OccurrenceSort();
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
		area.setText("");
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
