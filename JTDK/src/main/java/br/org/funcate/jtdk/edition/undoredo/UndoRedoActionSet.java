package br.org.funcate.jtdk.edition.undoredo;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is used to store a Set of {@link UndoRedoAction}
 * and this is used by {@link UndoRedoController} to control the undo/redo
 * stacks.
 * 
 * @author Moraes, Emerson Leite.
 *
 */
public class UndoRedoActionSet {

	private Set<UndoRedoAction> actionSet;
	
	/**
	 * Constructor.
	 */
	public UndoRedoActionSet(){
		this.actionSet = new HashSet<UndoRedoAction>();
	}
	
	/**
	 * Gets the Set of {@link UndoRedoAction}.
	 * The users can use this method to add a new action like: 
	 * 
	 * undoRedoActionSet.getActionSet().add(action);
	 * 
	 * @return
	 */
	public Set<UndoRedoAction> getActionSet(){
		return actionSet;
	}
}
