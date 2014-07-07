package br.org.funcate.jtdk.edition.event;

import java.util.EventObject;

import org.opengis.feature.simple.SimpleFeature;

import br.org.funcate.jtdk.edition.EditionFeatureController;

/**
 * This event can be used to remove a {@link SimpleFeature} of the {@link EditionFeatureController} feature list.
 * 
 * @author Moraes, Emerson Leite.
 *
 */
@SuppressWarnings("serial")
public class FeatureRemovedEvent extends EventObject{
	
	/**
	 * Feature to be removed.
	 */
	private SimpleFeature feature;
	
	/**
	 * The name of a {@link Layer} that user wish to remove a {@link SimpleFeature}.
	 */
	private String layerName;
	
	/**
	 * Defines if the feature of this event will be add to UndoRedo stack.
	 */
	private boolean undoRedoable;
	
	/**
	 * Constructor
	 * @param source
	 * @param feature to be removed.
	 */
	public FeatureRemovedEvent(Object source, SimpleFeature feature, String layerName, boolean undoRedoable) {
		super(source);
		this.setFeature(feature);
		this.setLayerName(layerName);
		this.setUndoRedoable(undoRedoable);
	}

	/**
	 * Gets the Feature to be removed.
	 * @return
	 */
	public SimpleFeature getFeature() {
		return feature;
	}

	/**
	 * Sets Feature to be removed.
	 * @param feature
	 */
	public void setFeature(SimpleFeature feature) {
		this.feature = feature;
	}
	
	/**
	 * Gets the {@link Layer} name that user wish to remove a {@link SimpleFeature}.
	 * @return featureCollectionName
	 */
	public String getLayerName() {
		return layerName;
	}

	/**
	 * Sets the {@link Layer} name that user wish to remove a {@link SimpleFeature}.
	 * @param layerName to be set.
	 */
	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}
	
	/**
	 * Gets if the feature of this event will be add to UndoRedo stack.
	 * @return
	 */
	public boolean isUndoRedoable() {
		return undoRedoable;
	}

	/**
	 * Sets if the feature of this event will be add to UndoRedo stack.
	 * @param undoRedoable
	 */
	public void setUndoRedoable(boolean undoRedoable) {
		this.undoRedoable = undoRedoable;
	}

}
