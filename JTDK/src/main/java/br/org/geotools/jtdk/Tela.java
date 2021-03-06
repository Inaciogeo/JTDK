package br.org.geotools.jtdk;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Tela extends JFrame{

	public Tela(BufferedImage image){
		this.setSize(800, 600);
		MyCanvas canvas = new MyCanvas(image);
		this.add(canvas);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
