package editor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import parser.Checker;

import buffer.Buffer;

/**
 * Allows the user to display all the images in the source text
 * only displays files on the user's local system
 * @author joe
 *
 */
public class ImgView implements Observer {

	//swing components
	private JFrame frame;
	private JMenuBar menuB;
	private JMenu menu;
	private JLabel label;
	private BufferedImage bi;
	private ImageIcon icon;
	
	//ActionListeners
	private ActionListener img;
	
	//logic
	private List<String> imgPaths;
	
	/**
	 * Constructor
	 * @param b the active buffer
	 */
	public ImgView(Buffer b) { 
		b.addObserver(this);
		imgPaths = b.getImgs();
		
		//Swing
		frame = new JFrame("View images");
		menuB = new JMenuBar();
		menu = new JMenu("Images");
		label = new JLabel("", SwingConstants.CENTER);
		label.setVisible(true);
		for(final String s : imgPaths) { 
			JMenuItem temp = new JMenuItem(s);
			temp.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					File file = new File(s);
					loadImage(file);	
				}
				
			});
			menu.add(temp);
			
		}
		frame.setContentPane(label);
		frame.setLayout(new BorderLayout());
		frame.add(menuB, BorderLayout.NORTH);
		menuB.add(menu);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.repaint();
		
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("ImgView notified");
		
	}
	
	private void loadImage(File f) { 
		try {
			bi = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File not found");
		}
		icon = new ImageIcon(bi);
		label.setIcon(icon);
		label.repaint();
		frame.repaint();
		frame.pack();
		
		
	}

}
