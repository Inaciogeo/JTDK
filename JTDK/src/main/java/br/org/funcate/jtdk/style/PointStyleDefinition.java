package br.org.funcate.jtdk.style;

import java.awt.Color;

import javax.swing.ImageIcon;

public class PointStyleDefinition {

	private Color color;
	
	private Color contourColor;
	
	private int size;
	
	private int contourWidth;
	
	private String wellKnownName;
	
	private int rotation;
	
	@SuppressWarnings("unused")
	private ImageIcon icon;

	public PointStyleDefinition(){
		
	}
	
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the contourColor
	 */
	public Color getContourColor() {
		return contourColor;
	}

	/**
	 * @param contourColor the contourColor to set
	 */
	public void setContourColor(Color contourColor) {
		this.contourColor = contourColor;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * @param contourWidth the contourWidth to set
	 */
	public void setContourWidth(int contourWidth) {
		this.contourWidth = contourWidth;
	}

	/**
	 * @return the contourWidth
	 */
	public int getContourWidth() {
		return contourWidth;
	}

	/**
	 * @param wellKnownName the wellKnownName to set
	 */
	public void setWellKnownName(String wellKnownName) {
		this.wellKnownName = wellKnownName;
	}

	/**
	 * @return the wellKnownName
	 */
	public String getWellKnownName() {
		return wellKnownName;
	}

	/**
	 * @param rotation the rotation to set
	 */
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	/**
	 * @return the rotation
	 */
	public int getRotation() {
		return rotation;
	}

	public boolean isSetColor(){
		if (color != null){
			return true;
		}
		return false;
	}
	
	public boolean isSetContourColor(){
		if (contourColor != null){
			return true;
		}
		return false;
	}
	
	public boolean isSetSize(){
		if (size > 0){
			return true;
		}
		return false;
	}
	
	public boolean isSetContourWidth(){
		if (contourWidth > 0){
			return true;
		}
		return false;
	}
	
	public boolean isSetWellKnownName(){
		if (wellKnownName != null){
			return true;
		}
		return false;
	}
}
