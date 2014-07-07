package br.org.funcate.jtdk.edition.undoredo;

import org.opengis.feature.simple.SimpleFeature;

/**
 * This is an extension of {@link UndoRedoAction} and provides
 * an action to turn the {@link SimpleFeature} creation undoredoable.
 * 
 * @author Moraes, Emerson Leite
 *
 */
public class FeatureCreatedAction extends UndoRedoAction {

	private String layerName;
	
	private SimpleFeature feature;
	
	/**
	 * Constructor.
	 * @param undoRedo that will be called to execute the undo/redo.
	 * @param feature
	 * @param layerName
	 */
	public FeatureCreatedAction(UndoRedo undoRedo, SimpleFeature feature, String layerName) {
		super(undoRedo);
		this.setFeature(feature);
		this.setLayerName(layerName);
	}

	/**
	 * Gets the {@link SimpleFeature} of this action.
	 * @return
	 */
	public SimpleFeature getFeature() {
		return feature;
	}

	/**
	 * Sets the {@link SimpleFeature} of this action. 
	 * @param feature
	 */
	public void setFeature(SimpleFeature feature) {
		this.feature = feature;
	}

	/**
	 * Gets the layer name that the {@link SimpleFeature} was created.
	 * @return
	 */
	public String getLayerName() {
		return layerName;
	}

	/**
	 * Sets the layer name that the {@link SimpleFeature} was created.
	 * @param layerName
	 */
	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

}
