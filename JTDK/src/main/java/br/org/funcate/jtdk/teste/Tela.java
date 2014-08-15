package br.org.funcate.jtdk.teste;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Tela extends JFrame{
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Tela tela = new Tela(null);
	}

	public Tela(BufferedImage image){
		this.setSize(800, 600);
		MyCanvas canvas = new MyCanvas(image);
		this.add(canvas);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
