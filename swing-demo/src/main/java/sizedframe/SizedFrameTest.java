package sizedframe;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SizedFrameTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				JFrame frame = new SizedFrame();
				frame.setTitle("SizedFrame");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}


class SizedFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5723256719929269625L;
	
	public SizedFrame (){
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setSize(screenWidth/2, screenHeight/2);
		setLocationByPlatform(true);
		
		Image img = new ImageIcon("icon.jpg").getImage();
		setIconImage(img);		
	}
	
}