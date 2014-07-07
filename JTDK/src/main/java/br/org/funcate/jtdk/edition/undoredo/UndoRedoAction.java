package br.org.funcate.jtdk.edition.undoredo;

/**
 * This class must be extended to create new actions
 * to use in the UndoRedo context.
 * 
 * @author Moraes, Emerson Leite
 *
 */
public class UndoRedoAction {

	private UndoRedo undoRedo;
	
	/**
	 * Constructor.
	 * @param undoRedo an implementation of {@link UndoRedo} that will be called to execute the undo/redo action.
	 */
	public UndoRedoAction(UndoRedo undoRedo){
		this.undoRedo = undoRedo;
	}

	/**
	 * @return an implementation of {@link UndoRedo} that will be called to execute the undo/redo action.
	 */
	public UndoRedo getUndoRedo() {
		return undoRedo;
	}

	/**
	 * 
	 * @param undoRedo an implementation of {@link UndoRedo} that will be called to execute the undo/redo action.
	 */
	public void setUndoRedo(UndoRedo undoRedo) {
		this.undoRedo = undoRedo;
	}
}
