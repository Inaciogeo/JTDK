package br.org.funcate.jtdk.edition.undoredo.event;

import java.util.EventObject;

/**
 * This event can be used to create a Redo action in the aplication.
 * 
 * @author Moraes, Emerson Leite
 *
 */
@SuppressWarnings("serial")
public class RedoEvent extends EventObject {

	/**
	 * Constructor.
	 * @param source
	 */
	public RedoEvent(Object source) {
		super(source);
	}

}
