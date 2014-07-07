package br.org.funcate.jtdk.edition;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import br.org.funcate.eagles.kernel.dispatcher.EventDispatcher;
import br.org.funcate.eagles.kernel.dispatcher.EventHandler;
import br.org.funcate.eagles.kernel.listener.EventListener;
import br.org.funcate.eagles.kernel.listener.ListenersHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandlerImpl;
import br.org.funcate.eagles.kernel.transmitter.DirectedEventTransmitter;
import br.org.funcate.eagles.kernel.transmitter.EventTransmitter;
import br.org.funcate.glue.controller.CanvasController;
import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.event.BoxChangedEvent;
import br.org.funcate.glue.event.CleanBufferEvent;
import br.org.funcate.glue.event.DrawLayersEvent;
import br.org.funcate.glue.event.DrawTilesEvent;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.view.AbstractCanvas;
import br.org.funcate.jtdk.canvas.SFSCanvas;
import br.org.funcate.jtdk.edition.event.ClearGeometriesEvent;
import br.org.funcate.jtdk.edition.event.FeatureCreatedEvent;
import br.org.funcate.jtdk.edition.event.FeatureEditedEvent;
import br.org.funcate.jtdk.edition.event.FeatureRemovedEvent;
import br.org.funcate.jtdk.edition.event.GetFeatureEvent;
import br.org.funcate.jtdk.edition.event.SetStyleEvent;
import br.org.funcate.jtdk.edition.undoredo.UndoRedo;
import br.org.funcate.jtdk.edition.undoredo.UndoRedoController;
import br.org.funcate.jtdk.edition.undoredo.event.AddUndoRedoActionEvent;
import br.org.funcate.jtdk.edition.undoredo.event.ClearUndoRedoEvent;
import br.org.funcate.jtdk.edition.undoredo.event.RedoEvent;
import br.org.funcate.jtdk.edition.undoredo.event.UndoEvent;
import br.org.funcate.jtdk.edition.undoredo.event.UndoRedoActiveEvent;
import br.org.funcate.plugin.GluePlugin;
import br.org.funcate.plugin.GluePluginService;

/**
 * This is the main controller to provides a SFS suport to GLUE application.
 * The {@link EditionController} provides support to feature creation, remotion and edition. This
 * also permits the use of {@link UndoRedo} functions.
 * 
 * @author Moraes, Emerson Leite
 *
 */
public class EditionController implements EventDispatcher, EventListener, GluePlugin {

	private ListenersHandler listeners;

	private EventHandler eventHandler;

	private EventTransmitter transmitter;

	/**
	 * Responsible to control the feature edition events.
	 */
	private EditionFeatureController editionFeatureController;
	
	/**
	 * Responsible to control the undo/redo events.
	 */
	private UndoRedoController undoRedoController;

	/**
	 * Constructor.
	 */
	public EditionController() {
		this.listeners = new ListenersHandlerImpl();
		this.eventHandler = new EventHandler();
		this.transmitter = new DirectedEventTransmitter(this);
	}

	/**
	 * This initialize the listeners of the {@link EditionController}, {@link EditionFeatureController} and {@link UndoRedoController}.
	 */
	private void initListeners() {
		this.editionFeatureController = new EditionFeatureController();
		this.editionFeatureController.getListenersHandler().attachListener(this, null);
		List<String> eventsToFeature = new ArrayList<String>();
		eventsToFeature.add(BoxChangedEvent.class.getName());
		eventsToFeature.add(FeatureRemovedEvent.class.getName());
		eventsToFeature.add(FeatureCreatedEvent.class.getName());
		eventsToFeature.add(FeatureEditedEvent.class.getName());
		eventsToFeature.add(SetStyleEvent.class.getName());
		eventsToFeature.add(DrawLayersEvent.class.getName());
		eventsToFeature.add(GetFeatureEvent.class.getName());
		eventsToFeature.add(ClearGeometriesEvent.class.getName());
		this.listeners.attachListener(editionFeatureController, eventsToFeature);
		
		this.undoRedoController = new UndoRedoController();
		this.undoRedoController.getListenersHandler().attachListener(this, null);
		List<String> eventsToUndo = new ArrayList<String>();
		eventsToUndo.add(UndoEvent.class.getName());
		eventsToUndo.add(RedoEvent.class.getName());
		eventsToUndo.add(AddUndoRedoActionEvent.class.getName());
		eventsToUndo.add(ClearUndoRedoEvent.class.getName());
		this.listeners.attachListener(undoRedoController, eventsToUndo);

		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		CanvasController canvasController = mediator.getCanvasController();

		List<String> eventsToListen = new ArrayList<String>();
		eventsToListen.add(BoxChangedEvent.class.getName());
		eventsToListen.add(FeatureRemovedEvent.class.getName());
		eventsToListen.add(FeatureCreatedEvent.class.getName());
		eventsToListen.add(FeatureEditedEvent.class.getName());
		eventsToListen.add(SetStyleEvent.class.getName());
		eventsToListen.add(DrawLayersEvent.class.getName());
		eventsToListen.add(GetFeatureEvent.class.getName());
		eventsToListen.add(UndoEvent.class.getName());
		eventsToListen.add(RedoEvent.class.getName());
		eventsToListen.add(AddUndoRedoActionEvent.class.getName());
		eventsToListen.add(ClearUndoRedoEvent.class.getName());
		eventsToListen.add(ClearGeometriesEvent.class.getName());		
		canvasController.getListenersHandler().attachListener(this, eventsToListen);

		List<String> eventsToCanvas = new ArrayList<String>();
		eventsToCanvas.add(DrawTilesEvent.class.getName());
		eventsToCanvas.add(CleanBufferEvent.class.getName());
		eventsToCanvas.add(UndoRedoActiveEvent.class.getName());
		this.listeners.attachListener(canvasController, eventsToCanvas);
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
	public void dispatch(EventTransmitter tc, EventObject e) throws Exception {
		tc.dispatch(e);
	}

	@Override
	public void handle(EventObject event) throws Exception {
		this.dispatch(transmitter, event);
	}

	/**
	 * Override of {@link GluePlugin}.
	 */
	@Override
	public void run() {
		AbstractCanvas canvas = new SFSCanvas(0, 0);
		GluePluginService.setCanvas(canvas);
		this.initListeners();
	}

}
