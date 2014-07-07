package br.org.funcate.jtdk.edition.event;

import java.util.EventObject;



public class ClearGeometriesEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String layerName;

	public ClearGeometriesEvent(Object arg0, String layerName) {
		super(arg0);
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

	

}
