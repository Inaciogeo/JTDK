package br.org.funcate.jtdk.teste;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class MyCanvas extends Canvas{

	private BufferedImage image;
	
	public MyCanvas(){
	}
	
	public MyCanvas(BufferedImage image){
		this.image = image;
	}
	
	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void paint(Graphics g){
		if (image == null){
			return;
		}
		
		g.drawImage(image, 0, 0, null);
	}
}
