package br.org.funcate.jtdk.canvas.event;

import java.util.EventObject;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.styling.Style;

import br.org.funcate.glue.model.canvas.BufferEnum;

/**
 * This event can be used to draw a {@link SimpleFeatureCollection} with {@link Style} on Glue's specified Buffer.
 * 
 * @author Moraes, Emerson Leite.
 */
@SuppressWarnings("serial")
public class DrawFeatureCollectionEvent extends EventObject{

	/**
	 * Collection to draw.
	 */
	private SimpleFeatureCollection featureCollection;
	
	/**
	 * Style of the featureCollection.
	 */
	private Style style;
	
	/**
	 * Buffer to draw the featureCollection.
	 */
	private BufferEnum bufferId;
	
	/**
	 * Constructor.
	 * @param source 
	 * @param featureCollection
	 * @param style
	 */
	public DrawFeatureCollectionEvent(Object source, SimpleFeatureCollection featureCollection, Style style, BufferEnum bufferId) {
		super(source);
		this.setFeatureCollection(featureCollection);
		this.setStyle(style);
		this.setBufferId(bufferId);
	}

	/**
	 * Gets a {@link SimpleFeatureCollection}.
	 * @return
	 */
	public SimpleFeatureCollection getFeatureCollection() {
		return featureCollection;
	}

	/**
	 * Sets a {@link SimpleFeatureCollection}.
	 * @param featureCollection
	 */
	public void setFeatureCollection(SimpleFeatureCollection featureCollection) {
		this.featureCollection = featureCollection;
	}

	/**
	 * Gets a Style of the featureCollection.
	 * @return
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * Sets a {@link Style} of the featureCollection.
	 * @param style
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	/**
	 * @param bufferId the bufferId to set
	 */
	public void setBufferId(BufferEnum bufferId) {
		this.bufferId = bufferId;
	}

	/**
	 * @return the bufferId
	 */
	public BufferEnum getBufferId() {
		return bufferId;
	}

	
	
}
