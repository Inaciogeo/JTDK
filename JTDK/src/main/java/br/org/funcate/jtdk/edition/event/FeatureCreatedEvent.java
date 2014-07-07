package br.org.funcate.jtdk.edition.event;

import java.util.EventObject;

import org.opengis.feature.simple.SimpleFeature;

import br.org.funcate.jtdk.edition.EditionFeatureController;

/**
 * This event can be used to add a {@link SimpleFeature} to {@link EditionFeatureController} feature list.
 * 
 * @author Moraes, Emerson Leite.
 *
 */
@SuppressWarnings("serial")
public class FeatureCreatedEvent extends EventObject{

	/**
	 * Feature to add.
	 */
	private SimpleFeature feature;
	
	/**
	 * The name of a {@link Layer} that user wish to add a {@link SimpleFeature}.
	 */
	private String layerName;
	
	/**
	 * Defines if the feature of this event will be add to UndoRedo stack.
	 */
	private boolean undoRedoable;
	
	/**
	 * Constructor.
	 * @param source
	 * @param feature to add.
	 * @param undoRedoable this define if the feature of this event will be add to UndoRedo stack.
	 */
	public FeatureCreatedEvent(Object source, SimpleFeature feature, String layerName, boolean undoRedoable) {
		super(source);
		this.setFeature(feature);
		this.setLayerName(layerName);
		this.setUndoRedoable(undoRedoable);
	}

	/**
	 * Gets Feature to add.
	 * @return
	 */
	public SimpleFeature getFeature() {
		return feature;
	}

	/**
	 * Sets Feature to add.
	 * @param feature
	 */
	public void setFeature(SimpleFeature feature) {
		this.feature = feature;
	}

	/**
	 * Gets the {@link Layer} name that user wish to add a {@link SimpleFeature}.
	 * @return featureCollectionName
	 */
	public String getLayerName() {
		return layerName;
	}

	/**
	 * Sets the {@link Layer} name that user wish to add a {@link SimpleFeature}.
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
