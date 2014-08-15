package br.org.funcate.jtdk.edition.undoredo.event;

import java.util.EventObject;

/**
 * This events can be used to create an Undo action in the application.
 * 
 * @author Moraes, Emerson Leite
 *
 */
@SuppressWarnings("serial")
public class UndoEvent extends EventObject{

	/**
	 * Constructor
	 * @param source
	 */
	public UndoEvent(Object source){
		super(source);
	}
}
