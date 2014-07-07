package br.org.funcate.jtdk.style.model;

import java.awt.Color;

import br.org.funcate.jtdk.style.enumeration.LineFinalEnum;
import br.org.funcate.jtdk.style.enumeration.LineJoinEnum;
import br.org.funcate.jtdk.style.enumeration.LineStyleEnum;

/**
 * This class represents a style to Lines.
 * 
 * @author Moraes, Emerson Leite
 * 
 */
public class LineStyleVisual implements Cloneable {

	private Color color;

	private LineStyleEnum style;

	private LineFinalEnum lineFinal;

	private LineJoinEnum join;

	private double width;

	/**
	 * Constructor.
	 */
	public LineStyleVisual() {
	}

	/**
	 * Constructor.
	 * 
	 * @param color
	 * @param style
	 * @param lineFinal
	 * @param join
	 * @param width
	 */
	public LineStyleVisual(Color color, LineStyleEnum style, LineFinalEnum lineFinal, LineJoinEnum join, double width) {
		this.setColor(color);
		this.setJoin(join);
		this.setLineFinal(lineFinal);
		this.setStyle(style);
		this.setWidth(width);

	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the style
	 */
	public LineStyleEnum getStyle() {
		return style;
	}

	/**
	 * @param style
	 *            the style to set
	 */
	public void setStyle(LineStyleEnum style) {
		this.style = style;
	}

	/**
	 * @return the lineFinal
	 */
	public LineFinalEnum getLineFinal() {
		return lineFinal;
	}

	/**
	 * @param lineFinal
	 *            the lineFinal to set
	 */
	public void setLineFinal(LineFinalEnum lineFinal) {
		this.lineFinal = lineFinal;
	}

	/**
	 * @return the join
	 */
	public LineJoinEnum getJoin() {
		return join;
	}

	/**
	 * @param join
	 *            the join to set
	 */
	public void setJoin(LineJoinEnum join) {
		this.join = join;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	public LineStyleVisual clone() {
		LineStyleVisual clone = new LineStyleVisual();
		clone.setColor(this.getColor());
		clone.setJoin(this.getJoin());
		clone.setLineFinal(this.getLineFinal());
		clone.setStyle(this.getStyle());
		clone.setWidth(this.getWidth());

		return clone;
	}
}
