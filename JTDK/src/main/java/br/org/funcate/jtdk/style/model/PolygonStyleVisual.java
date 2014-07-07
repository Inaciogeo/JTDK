package br.org.funcate.jtdk.style.model;

import java.awt.Color;
import java.awt.Image;

import br.org.funcate.jtdk.style.enumeration.AreaStyleEnum;

/**
 * This class represents a style for Polygons.
 * 
 * @author Moraes, Emerson Leite.
 *
 */
public class PolygonStyleVisual implements Cloneable{

	private Color areaColor;
	
	private double areaTransparency;
	
	private AreaStyleEnum areaStyle;
	
	private Image image;
	
	private double areaHeight;
	
	private LineStyleVisual contour;
	
	public PolygonStyleVisual(){
		
	}

	public PolygonStyleVisual(Color areaColor, int areaTransparency,
			AreaStyleEnum areaStyle, Image image, double areaHeight,
			LineStyleVisual contour) {
		super();
		this.areaColor = areaColor;
		this.areaTransparency = areaTransparency;
		this.areaStyle = areaStyle;
		this.image = image;
		this.areaHeight = areaHeight;
		this.contour = contour;
	}

	/**
	 * @return the areaColor
	 */
	public Color getAreaColor() {
		return areaColor;
	}

	/**
	 * @param areaColor the areaColor to set
	 */
	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}

	/**
	 * @return the areaTransparency
	 */
	public double getAreaTransparency() {
		return areaTransparency;
	}

	/**
	 * @param areaTransparency the areaTransparency to set
	 */
	public void setAreaTransparency(double areaTransparency) {
		this.areaTransparency = areaTransparency;
	}

	/**
	 * @return the areaStyle
	 */
	public AreaStyleEnum getAreaStyle() {
		return areaStyle;
	}

	/**
	 * @param areaStyle the areaStyle to set
	 */
	public void setAreaStyle(AreaStyleEnum areaStyle) {
		this.areaStyle = areaStyle;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return the areaHeight
	 */
	public double getAreaHeight() {
		return areaHeight;
	}

	/**
	 * @param areaHeight the areaHeight to set
	 */
	public void setAreaHeight(double areaHeight) {
		this.areaHeight = areaHeight;
	}

	/**
	 * @return the contour
	 */
	public LineStyleVisual getContour() {
		return contour;
	}

	/**
	 * @param contour the contour to set
	 */
	public void setContour(LineStyleVisual contour) {
		this.contour = contour;
	}
	
	/**
	 * Cloneable implementation.
	 */
	public PolygonStyleVisual clone(){
		PolygonStyleVisual clone = new PolygonStyleVisual();
		
		clone.setAreaColor(this.getAreaColor());
		clone.setAreaHeight(this.getAreaHeight());
		clone.setAreaTransparency(this.getAreaTransparency());
		clone.setContour(this.getContour());
		clone.setImage(this.getImage());
		clone.setAreaStyle(this.getAreaStyle());
		
		return clone;
	}
}
