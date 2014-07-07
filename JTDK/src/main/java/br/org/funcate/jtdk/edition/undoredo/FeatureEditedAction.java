package br.org.funcate.jtdk.edition.undoredo;

import org.opengis.feature.simple.SimpleFeature;

/**
 * This is an extension of {@link UndoRedoAction} and provides
 * an action to turn the {@link SimpleFeature} edition undoredoable.
 * 
 * @author Moraes, Emerson Leite
 *
 */
public class FeatureEditedAction extends UndoRedoAction{

	private String layerName;
	
	private SimpleFeature beforeFeature;
	
	private SimpleFeature afterFeature;
	
	/**
	 * Constructor.
	 * @param undoRedo that will be called to execute the undo/redo.
	 * @param beforeFeature
	 * @param afterFeature
	 * @param layerName
	 */
	public FeatureEditedAction(UndoRedo undoRedo, SimpleFeature beforeFeature, SimpleFeature afterFeature, String layerName) {
		super(undoRedo);
		this.setBeforeFeature(beforeFeature);
		this.setAfterFeature(afterFeature);
		this.setLayerName(layerName);
	}

	/**
	 * Gets the layer name that the features was edited.
	 * @return
	 */
	public String getLayerName() {
		return layerName;
	}
	
	/**
	 * Sets the layer name that the features was edited.
	 * @param layerName
	 */
	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	/**
	 * Gets the {@link SimpleFeature} after the edition.
	 * @return
	 */
	public SimpleFeature getAfterFeature() {
		return afterFeature;
	}

	/**
	 * Sets the {@link SimpleFeature} after the edition.
	 * @param afterFeature
	 */
	public void setAfterFeature(SimpleFeature afterFeature) {
		this.afterFeature = afterFeature;
	}

	/**
	 * Gets the {@link SimpleFeature} before the edition.
	 * @return
	 */
	public SimpleFeature getBeforeFeature() {
		return beforeFeature;
	}

	/**
	 * Sets the {@link SimpleFeature} before the edition.
	 * @param beforeFeature
	 */
	public void setBeforeFeature(SimpleFeature beforeFeature) {
		this.beforeFeature = beforeFeature;
	}

	@Override
	public String toString() {
		return "FeatureEditedAction [layerName=" + layerName
				+ ", beforeFeature=" + beforeFeature + ", afterFeature="
				+ afterFeature + "]";
	}
}
