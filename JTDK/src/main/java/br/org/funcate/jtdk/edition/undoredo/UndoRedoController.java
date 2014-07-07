package br.org.funcate.jtdk.edition.undoredo;

import java.util.EventObject;
import java.util.Stack;

import br.org.funcate.eagles.kernel.dispatcher.EventDispatcher;
import br.org.funcate.eagles.kernel.dispatcher.EventHandler;
import br.org.funcate.eagles.kernel.listener.EventListener;
import br.org.funcate.eagles.kernel.listener.ListenersHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandlerImpl;
import br.org.funcate.eagles.kernel.transmitter.DirectedEventTransmitter;
import br.org.funcate.eagles.kernel.transmitter.EventTransmitter;
import br.org.funcate.jtdk.edition.undoredo.event.AddUndoRedoActionEvent;
import br.org.funcate.jtdk.edition.undoredo.event.ClearUndoRedoEvent;
import br.org.funcate.jtdk.edition.undoredo.event.RedoEvent;
import br.org.funcate.jtdk.edition.undoredo.event.UndoEvent;
import br.org.funcate.jtdk.edition.undoredo.event.UndoRedoActiveEvent;

/**
 * This class is responsible to control the Undo/Redo actions
 * of the application.
 * 
 * @author Moraes, Emerson Leite
 *
 */
public class UndoRedoController implements EventListener, EventDispatcher{

	/**
	 * {@link Stack} of undo actions.
	 */
	private Stack<UndoRedoActionSet> undoStack;
	
	/**
	 * {@link Stack} of redo actions.
	 */
	private Stack<UndoRedoActionSet> redoStack;
	
	private ListenersHandler listeners;
	
	private EventHandler eventHandler;
	
	private EventTransmitter transmitter;
	
	/**
	 * Constructor.
	 */
	public UndoRedoController(){
		this.undoStack = new Stack<UndoRedoActionSet>();
		this.redoStack = new Stack<UndoRedoActionSet>();
		this.listeners = new ListenersHandlerImpl();
		this.eventHandler = new EventHandler();
		this.transmitter = new DirectedEventTransmitter(this);
	}
	
	/**
	 * Provides a clean of undo {@link Stack}.
	 */
	public void clearUndoStack(){
		this.undoStack.clear();
	}
	
	/**
	 * Provides a clean of redo {@link Stack}.
	 */
	public void clearRedoStack(){
		this.redoStack.clear();
	}

	@Override
	public ListenersHandler getListenersHandler() {
		return this.listeners;
	}

	@Override
	public EventHandler getEventHandler() {
		return this.eventHandler;
	}

	@Override
	public void dispatch(EventTransmitter tc, EventObject e) throws Exception{
		tc.dispatch(e);
	}

	@Override
	public void handle(EventObject e) {
		
		if (e instanceof AddUndoRedoActionEvent){
			this.handle((AddUndoRedoActionEvent) e);
		} else if (e instanceof UndoEvent){
			this.handle((UndoEvent) e);
		} else if (e instanceof RedoEvent){
			this.handle((RedoEvent) e);
		} else if (e instanceof ClearUndoRedoEvent){
			this.handle((ClearUndoRedoEvent) e);
		}
	}
	
	/**
	 * Handle the {@link AddUndoRedoActionEvent} cleaning the redo {@link Stack}
	 * and adding an {@link UndoRedoActionSet} in the undo {@link Stack}.
	 * 
	 * @param e
	 */
	private void handle(AddUndoRedoActionEvent e){
		this.clearRedoStack();
		
		this.undoStack.addElement(e.getAction());
		try {
			this.dispatch(transmitter, new UndoRedoActiveEvent(this, true, false));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Handle the {@link UndoEvent} peeking the last element
	 * of the undo {@link Stack}. Executes the undo and put the action
	 * in the redo {@link Stack}.
	 * 
	 * @param e
	 */
	private void handle(UndoEvent e){
		
		boolean undoable = false;
		boolean redoable = false;
		
		if (this.undoStack.isEmpty()){
			if (redoStack.size() > 0){
				redoable = true;
			}
			try {
				this.dispatch(transmitter, new UndoRedoActiveEvent(this, undoable, redoable));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
		
		UndoRedoActionSet actionSet = this.undoStack.peek();
		
		if (actionSet.getActionSet().size() != 0){
			
			if (this.executeUndo(actionSet)){
				undoStack.pop();
				redoStack.addElement(actionSet);
			}
			
		}
		
	
		
		if (undoStack.size() > 0){
			undoable = true;
		}
		if (redoStack.size() > 0){
			redoable = true;
		}
		
		try {
			this.dispatch(transmitter, new UndoRedoActiveEvent(this, undoable, redoable));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Executes the undo calling the {@link UndoRedo} undo method
	 * of all the actions in the {@link UndoRedoActionSet}.
	 * 
	 * @param actionSet the {@link UndoRedoActionSet} to execute undo.
	 * @return
	 */
	private boolean executeUndo(UndoRedoActionSet actionSet){
		
		for (UndoRedoAction action : actionSet.getActionSet()){
			if (action.getUndoRedo() != null){
				
				action.getUndoRedo().undo(action, this);
				
			}
		}
		
		return true;
	}
	
	/**
	 * Handle the {@link RedoEvent} peeking the last element
	 * of the redo {@link Stack}. Executes the redo and put the action
	 * in the undo {@link Stack}.
	 * 
	 * @param e
	 */
	private void handle(RedoEvent e){
		
		boolean undoable = false;
		boolean redoable = false;
		
		if (this.redoStack.isEmpty()){
			if (undoStack.size() > 0){
				undoable = true;
			}
			try {
				this.dispatch(transmitter, new UndoRedoActiveEvent(this, undoable, redoable));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return;
		}
		
		UndoRedoActionSet actionSet = this.redoStack.peek();
		
		if (actionSet.getActionSet().size() != 0){
			
			if (this.executeRedo(actionSet)){
				this.redoStack.pop();
				this.undoStack.addElement(actionSet);
			}
		}
		
		
		if (undoStack.size() > 0){
			undoable = true;
		}
		if (redoStack.size() > 0){
			redoable = true;
		}
		
		try {
			this.dispatch(transmitter, new UndoRedoActiveEvent(this, undoable, redoable));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Executes the redo calling the {@link UndoRedo} redo method
	 * of all the actions in the {@link UndoRedoActionSet}.
	 * 
	 * @param actionSet the {@link UndoRedoActionSet} to execute redo.
	 * @return
	 */
	private boolean executeRedo(UndoRedoActionSet actionSet){
		
		for (UndoRedoAction action : actionSet.getActionSet()){
			if (action.getUndoRedo() != null){
				
				action.getUndoRedo().redo(action, this);
				
			}
		}
		
		return true;
	}
	
	/**
	 * Handle the {@link ClearUndoRedoEvent} cleaning
	 * the undo/redo {@link Stack}.
	 * 
	 * @param e
	 */
	private void handle(ClearUndoRedoEvent e){
		this.clearRedoStack();
		this.clearUndoStack();
		try {
			this.dispatch(transmitter, new UndoRedoActiveEvent(this, false, false));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
