package br.org.funcate.jtdk.edition.event;

import java.util.EventObject;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.styling.Style;

/**
 * This event can be used to associate a {@link Style} with a {@link SimpleFeatureCollection} by a name.
 * 
 * @author Moraes, Emerson Leite
 *
 */
@SuppressWarnings("serial")
public class SetStyleEvent extends EventObject{

	/**
	 * The name of a {@link Layer} to associate to a {@link Style}.
	 */
	private String layerName;
	
	/**
	 * The Style that user want to set.
	 */
	private Style style;
	
	/**
	 * Constructor.
	 * @param source
	 * @param style The Style that user wish to set.
	 * @param layerName The name of a {@link Layer} to associate to a {@link Style}.
	 */
	public SetStyleEvent(Object source, Style style, String layerName) {
		super(source);
		this.setStyle(style);
		this.setLayerName(layerName);
	}

	/**
	 * Gets the {@link Style}.
	 * @return
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * Sets the {@link Style}.
	 * @param style
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	/**
	 * Gets the layer name that the {@link Style} will be set.
	 * @return
	 */
	public String getLayerName() {
		return layerName;
	}

	/**
	 * Sets the layer name that the {@link Style} will be set.
	 * @param layerName
	 */
	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

}
