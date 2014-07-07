package br.org.funcate.jtdk.edition.event;

import java.util.EventObject;

import org.opengis.feature.simple.SimpleFeature;

import br.org.funcate.jtdk.edition.EditionFeatureController;

/**
 * This event can be used to edit a {@link SimpleFeature} from {@link EditionFeatureController} feature list.
 * 
 * @author Moraes, Emerson Leite.
 *
 */
public class FeatureEditedEvent extends EventObject{
	
	private static final long serialVersionUID = 1L;
	
	private String layerName;

	private SimpleFeature beforeFeature;
	
	private SimpleFeature afterFeature;
	
	private boolean undoRedoable;
	
	/**
	 * Constructor.
	 * @param source
	 * @param beforeFeature
	 * @param afterFeature
	 * @param layerName
	 * @param undoRedoable
	 */
	public FeatureEditedEvent(Object source, SimpleFeature beforeFeature, SimpleFeature afterFeature, String layerName, boolean undoRedoable) {
		super(source);
		this.setAfterFeature(afterFeature);
		this.setBeforeFeature(beforeFeature);
		this.setLayerName(layerName);
		this.setUndoRedoable(undoRedoable);
	}
	
	/**
	 * Gets the feature before edition.
	 * @return
	 */
	public SimpleFeature getBeforeFeature() {
		return beforeFeature;
	}

	/**
	 * Sets the feature before edition.
	 * @param beforeFeature
	 */
	public void setBeforeFeature(SimpleFeature beforeFeature) {
		this.beforeFeature = beforeFeature;
	}

	/**
	 * Gets the feature after edition.
	 * @return
	 */
	public SimpleFeature getAfterFeature() {
		return afterFeature;
	}

	/**
	 * Sets the feature after edition.
	 * @param afterFeature
	 */
	public void setAfterFeature(SimpleFeature afterFeature) {
		this.afterFeature = afterFeature;
	}

	/**
	 * Gets the layer name that this event will use.
	 * @return
	 */
	public String getLayerName() {
		return layerName;
	}

	/**
	 * Sets the layer name that this event will use.
	 * @param layerName
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
