package br.org.funcate.jtdk.edition.event;

import java.util.EventObject;

import org.geotools.data.simple.SimpleFeatureCollection;

/**
 * This event can be used for retrieve a {@link SimpleFeatureCollection} by a Layer name.
 * 
 * @author Moraes, Emerson Leite
 *
 */
@SuppressWarnings("serial")
public class GetFeatureEvent extends EventObject{

	private String layerName;
	
	private SimpleFeatureCollection featureCollection;

	/**
	 * Constructor.
	 * 
	 * @param source
	 * @param layerName Layer name that user wish recover the features.
	 */
	public GetFeatureEvent (Object source, String layerName){
		super(source);
		this.layerName = layerName;
	}

	/**
	 * @return the layerName
	 */
	public String getLayerName() {
		return layerName;
	}

	/**
	 * @param layerName the layerName to set
	 */
	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	/**
	 * If the GLUE application doesn't have features by the layerName
	 * this method return an empty {@link SimpleFeatureCollection}.
	 * 
	 * @return the featureCollection
	 */
	public SimpleFeatureCollection getFeatureCollection() {
		return featureCollection;
	}

	/**
	 * @param featureCollection the featureCollection to set
	 */
	public void setFeatureCollection(SimpleFeatureCollection featureCollection) {
		this.featureCollection = featureCollection;
	}
	
}
