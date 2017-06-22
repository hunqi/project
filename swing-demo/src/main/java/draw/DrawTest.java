package draw;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class DrawTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new DrawFrame();
				frame.setTitle("DrawTest");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class DrawFrame extends JFrame {
	public DrawFrame() {
		add(new DrawComponent());
		pack();
	}
}

class DrawComponent extends JComponent {

	private static final int DEFAULT_EDGE = 500;
	private static final Point2D CTR01 = new Point2D.Double(DEFAULT_EDGE / 2,
			DEFAULT_EDGE / 2);

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// draw ellipse
		int ellipseDiameter = 400;
		Ellipse2D eclipse = new Ellipse2D.Double(CTR01.getX()
				- ellipseDiameter / 2, CTR01.getY() - ellipseDiameter / 2,
				ellipseDiameter, ellipseDiameter);
		g2.draw(eclipse);

		// draw rectangle
		int rectangleWidth = 300;
		int rectangleHeight = 200;
		Rectangle2D rectangle = new Rectangle2D.Double(CTR01.getX()
				- rectangleWidth / 2, CTR01.getY() - rectangleHeight / 2,
				rectangleWidth, rectangleHeight);
		g2.draw(rectangle);

		// draw line
		Line2D line = new Line2D.Double(CTR01.getX() - rectangleWidth / 2,
				CTR01.getY() - rectangleHeight / 2, CTR01.getX()
						+ rectangleWidth / 2, CTR01.getY() + rectangleHeight
						/ 2);
		g2.draw(line);

		// draw inner ellipse
		Ellipse2D ellipse02 = new Ellipse2D.Double(CTR01.getX()
				- rectangleWidth / 2, CTR01.getY() - rectangleHeight / 2,
				rectangleWidth, rectangleHeight);
		g2.draw(ellipse02);
		
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_EDGE, DEFAULT_EDGE);
	}
}
