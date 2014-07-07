package br.org.funcate.jtdk.edition.undoredo.event;

import java.util.EventObject;

/**
 * This event can be used to verify if the application has
 * undo/redo to execute.
 * 
 * @author Moraes, Emerson Leite
 *
 */
@SuppressWarnings("serial")
public class UndoRedoActiveEvent extends EventObject{

	private boolean undoable;
	
	private boolean redoable;
	
	/**
	 * Constructor
	 * @param source
	 * @param undoable true if the application has undo, otherwise false.
	 * @param redoable true if the application has redo, otherwise false.
	 */
	public UndoRedoActiveEvent(Object source, boolean undoable, boolean redoable) {
		super(source);
		this.setUndoable(undoable);
		this.setRedoable(redoable);
	}

	/**
	 * @return true if the application has undo, otherwise false.
	 */
	public boolean isUndoable() {
		return undoable;
	}

	/**
	 * 
	 * @param undoable true if the application has undo, otherwise false.
	 */
	public void setUndoable(boolean undoable) {
		this.undoable = undoable;
	}

	/**
	 * 
	 * @return true if the application has redo, otherwise false.
	 */
	public boolean isRedoable() {
		return redoable;
	}

	/**
	 * 
	 * @param redoable true if the application has redo, otherwise false.
	 */
	public void setRedoable(boolean redoable) {
		this.redoable = redoable;
	}

}
