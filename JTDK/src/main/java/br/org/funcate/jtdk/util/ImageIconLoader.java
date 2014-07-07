package br.org.funcate.jtdk.util;

import javax.swing.ImageIcon;

/**
 * \brief Abstract class responsible for load local images to crate icons.
 * 
 * @version 1.0.0
 */
public abstract class ImageIconLoader {

	/**
	 * \brief Method to get an image icon.
	 * 
	 * @param path
	 *            The image path.
	 * @param c
	 *            The class that's trying to load the image.
	 * @return The image icon.
	 */
	public static ImageIcon createImageIcon(String path, Class<?> c) {
		java.net.URL imgURL = c.getClassLoader().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("arquivo não encontrado: " + path);
			return null;
		}
	}
}
