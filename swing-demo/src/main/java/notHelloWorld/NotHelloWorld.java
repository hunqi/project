package notHelloWorld;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class NotHelloWorld {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				JFrame frame = new NotHelloWorldFrame();
				frame.setTitle("NotHelloWorld");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class NotHelloWorldFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6087719245756401181L;
	
	public NotHelloWorldFrame() {
		add(new NotHelloWorldComponent());
		pack();
	}
	
}

class NotHelloWorldComponent extends JComponent {
	
	private static final int MSG_X = 75;
	private static final int MSG_Y = 100;
	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -666035043447286212L;

	@Override
	protected void paintComponent(Graphics g) {
		g.drawString("Not a Hello, World program", MSG_X, MSG_Y);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
}
