package br.org.funcate.jtdk.edition.undoredo.event;

import java.util.EventObject;
import java.util.Stack;

/**
 * This event can be used to clear the undo/redo {@link Stack}.
 * 
 * @author Moraes, Emerson Leite
 *
 */
@SuppressWarnings("serial")
public class ClearUndoRedoEvent extends EventObject {

	/**
	 * Constructor.
	 * @param source
	 */
	public ClearUndoRedoEvent(Object source) {
		super(source);
	}

}
