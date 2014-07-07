package br.org.funcate.jtdk.edition.undoredo;

import br.org.funcate.eagles.kernel.dispatcher.EventDispatcher;

/**
 * This interface provides a generic form to make a
 * undo/redo solution in the application.
 * 
 * @author Moraes, Emerson Leite
 *
 */
public interface UndoRedo {

	/**
	 * This method must be implemented to provide Undo actions
	 * for a specific context.
	 * 
	 * @param action that will be undo.
	 * @param dispatcher to dispatch events.
	 * @return true if success, otherwise false.
	 */
	public boolean undo(UndoRedoAction action, EventDispatcher dispatcher);
	
	/**
	 * This method must be implemented to provide Redo actions
	 * for a specific context.
	 * 
	 * @param action that will be redo.
	 * @param dispatcher to dispatch events.
	 * @return true if success, otherwise false.
	 */
	public boolean redo(UndoRedoAction action, EventDispatcher dispatcher);
}
