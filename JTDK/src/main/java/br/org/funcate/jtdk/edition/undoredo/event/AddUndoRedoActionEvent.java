package br.org.funcate.jtdk.edition.undoredo.event;

import java.util.EventObject;
import java.util.Stack;

import br.org.funcate.jtdk.edition.undoredo.UndoRedoActionSet;

/**
 * This event can be used to add a {@link UndoRedoActionSet} to the undo {@link Stack}.
 * 
 * @author Moraes, Emerson Leite
 *
 */
public class AddUndoRedoActionEvent extends EventObject{

	private UndoRedoActionSet actionSet;
	
	/**
	 * Constructor
	 * @param source
	 * @param actionSet the {@link UndoRedoActionSet} to be add in the {@link Stack}.
	 */
	public AddUndoRedoActionEvent(Object source, UndoRedoActionSet actionSet) {
		super(source);
		this.setActionSet(actionSet);
	}

	/**
	 * Gets the {@link UndoRedoActionSet} to be add in the {@link Stack}.
	 * @return
	 */
	public UndoRedoActionSet getAction() {
		return actionSet;
	}

	/**
	 * Sets the {@link UndoRedoActionSet} to be add in the {@link Stack}.
	 * @param actionSet
	 */
	public void setActionSet(UndoRedoActionSet actionSet) {
		this.actionSet = actionSet;
	}

}
