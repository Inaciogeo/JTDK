package br.org.funcate.jtdk.style;

import java.awt.Color;

public class LineStyleDefinition {

	private Color color;
	
	private int width;
	
	private LineStyleDefinition border;
	
	public LineStyleDefinition(){
		
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
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * @param border the border to set
	 */
	public void setBorder(LineStyleDefinition border) {
		this.border = border;
	}

	/**
	 * @return the border
	 */
	public LineStyleDefinition getBorder() {
		return border;
	}

	public boolean isSetColor(){
		if (color != null){
			return true;
		}
		return false;
	}
	
	public boolean isSetWidth(){
		if (width > 0){
			return true;
		}
		return false;
	}
	
	public boolean isSetBorder(){
		if (border != null){
			return true;
		}
		return false;
	}
}
