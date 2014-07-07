package br.org.funcate.jtdk.style.teste;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class MyCanvas extends Canvas {

	private BufferedImage image;

	public MyCanvas(BufferedImage image) {
		this.image = image;
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void paint(Graphics g) {
		if (image == null) {
			return;
		}

		g.drawImage(image, 0, 0, null);
	}

	/**
	 * Clean buffer.
	 */
	public void cleanImage() {
		Graphics2D g2d = image.createGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
		Rectangle2D rect = new Rectangle2D.Double(0, 0, image.getWidth(), image.getHeight());
		g2d.fill(rect);
	}
}
