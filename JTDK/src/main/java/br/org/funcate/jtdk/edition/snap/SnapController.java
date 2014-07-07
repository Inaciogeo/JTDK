package br.org.funcate.jtdk.edition.snap;

import java.util.EventObject;

import br.org.funcate.eagles.kernel.dispatcher.EventDispatcher;
import br.org.funcate.eagles.kernel.dispatcher.EventHandler;
import br.org.funcate.eagles.kernel.listener.EventListener;
import br.org.funcate.eagles.kernel.listener.ListenersHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandlerImpl;
import br.org.funcate.eagles.kernel.transmitter.DirectedEventTransmitter;
import br.org.funcate.eagles.kernel.transmitter.EventTransmitter;

/**
 * This provides a snap control for the edition functionalities.
 * 
 * @author Moraes, Emerson Leite
 *
 */
//TODO Needs Implementation!!
public class SnapController implements EventListener,  EventDispatcher{

	private ListenersHandler listeners;
	
	private EventHandler eventHandler;
	
	@SuppressWarnings("unused")
	private EventTransmitter transmitter;
	
	public SnapController(){
		this.listeners = new ListenersHandlerImpl();
		this.eventHandler = new EventHandler();
		this.transmitter = new DirectedEventTransmitter(this);
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
	public void dispatch(EventTransmitter tc, EventObject e) {
		this.dispatch(tc, e);
	}

	@Override
	public void handle(EventObject e) {
		
	}

	
}
