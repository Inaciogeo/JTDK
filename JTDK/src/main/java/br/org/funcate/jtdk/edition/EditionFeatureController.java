package br.org.funcate.jtdk.edition;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureCollections;
import org.geotools.styling.Style;
import org.opengis.feature.simple.SimpleFeature;

import br.org.funcate.eagles.kernel.dispatcher.EventDispatcher;
import br.org.funcate.eagles.kernel.dispatcher.EventHandler;
import br.org.funcate.eagles.kernel.listener.EventListener;
import br.org.funcate.eagles.kernel.listener.ListenersHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandlerImpl;
import br.org.funcate.eagles.kernel.transmitter.DirectedEventTransmitter;
import br.org.funcate.eagles.kernel.transmitter.EventTransmitter;
import br.org.funcate.glue.event.BoxChangedEvent;
import br.org.funcate.glue.event.CleanBufferEvent;
import br.org.funcate.glue.event.DrawLayersEvent;
import br.org.funcate.glue.event.DrawTilesEvent;
import br.org.funcate.glue.model.canvas.BufferEnum;
import br.org.funcate.jtdk.canvas.GeometryCanvas;
import br.org.funcate.jtdk.edition.event.ClearGeometriesEvent;
import br.org.funcate.jtdk.edition.event.FeatureCreatedEvent;
import br.org.funcate.jtdk.edition.event.FeatureEditedEvent;
import br.org.funcate.jtdk.edition.event.FeatureRemovedEvent;
import br.org.funcate.jtdk.edition.event.GetFeatureEvent;
import br.org.funcate.jtdk.edition.event.SetStyleEvent;
import br.org.funcate.jtdk.edition.undoredo.FeatureCreatedAction;
import br.org.funcate.jtdk.edition.undoredo.FeatureEditedAction;
import br.org.funcate.jtdk.edition.undoredo.FeatureRemovedAction;
import br.org.funcate.jtdk.edition.undoredo.UndoRedo;
import br.org.funcate.jtdk.edition.undoredo.UndoRedoAction;
import br.org.funcate.jtdk.edition.undoredo.UndoRedoActionSet;
import br.org.funcate.jtdk.edition.undoredo.UndoRedoController;
import br.org.funcate.jtdk.edition.undoredo.event.AddUndoRedoActionEvent;
import br.org.funcate.jtdk.services.GeoToolsGraphicalService;

/**
 * This class is responsible to handle the {@link SimpleFeature} related events.
 * This implements the {@link UndoRedo} interface and is responsible to control the undoredoable
 * features.
 *  
 * @author Moraes, Emerson Leite
 *
 */
public class EditionFeatureController implements EventDispatcher, EventListener, UndoRedo{

	private ListenersHandler listeners;
	
	private EventHandler eventHandler;
	
	private EventTransmitter transmitter;
	
	private Map<String, SimpleFeatureCollection> featureMap;
	
	private Map<String, Style> featureStyleMap;
	
	private Map<String, SimpleFeatureCollection> featureEditedMap;
	
	private List<String> layersToDraw;
	
	private GeoToolsGraphicalService graphicalService;
	
	private UndoRedoActionSet actionSet;
	
	/**
	 * Constructor.
	 */
	public EditionFeatureController (){
		this.listeners = new ListenersHandlerImpl();
		this.eventHandler = new EventHandler();
		this.transmitter = new DirectedEventTransmitter(this);
		this.featureMap = new HashMap<String, SimpleFeatureCollection>();
		this.featureEditedMap = new HashMap<String, SimpleFeatureCollection>();
		this.featureStyleMap = new HashMap<String, Style>();
		this.layersToDraw = new ArrayList<String>();
		this.graphicalService = new GeoToolsGraphicalService();
		this.actionSet = new UndoRedoActionSet();
	}
	
	/**
	 * Adds a {@link SimpleFeature} in a specified FeatureMap.
	 * 
	 * @param layerName name of the layer that the feature will be add.
	 * @param feature to be add.
	 * @param featureMap the map that the feature will be add.
	 */
	private void addFeature(String layerName, SimpleFeature feature, Map<String, SimpleFeatureCollection> featureMap){
		
		SimpleFeatureCollection featureCollection = this.getOrCreateFeatureCollection(layerName, featureMap);
		
		featureCollection.add(feature);
	}
	
	/**
	 * Removes a {@link SimpleFeature} from a specified FeatureMap.
	 * @param layerName name of the layer that the feature will be removed.
	 * @param feature to be removed.
	 * @param featureMap the map that the feature will be removed.
	 */
	private void removeFeature (String layerName, SimpleFeature feature, Map<String, SimpleFeatureCollection> featureMap){
		SimpleFeatureCollection featureCollection = this.getOrCreateFeatureCollection(layerName, featureMap);
		featureCollection.remove(feature);
	}
	
	/**
	 * Gets or create a {@link SimpleFeatureCollection} with specified layer name.
	 * 
	 * @param layerName name of the layer that will be retrieved.
	 * @param featureMap map that the feature collection will be retrieved.
	 * @return
	 */
	private SimpleFeatureCollection getOrCreateFeatureCollection(String layerName, Map<String, SimpleFeatureCollection> featureMap){
		SimpleFeatureCollection featureCollection = featureMap.get(layerName);
		
		if (featureCollection == null){
			featureCollection = FeatureCollections.newCollection();
			featureMap.put(layerName, featureCollection);
		}
		
		return featureCollection;
	}
	
	/**
	 * Adds a {@link Style} to be used with a specified layer.
	 * @param layerName name of the layer that the {@link Style} will be associated.
	 * @param style the style.
	 */
	private void addStyle(String layerName, Style style){
		featureStyleMap.put(layerName, style);
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
	public void handle(EventObject event) {
		
		if (event instanceof FeatureCreatedEvent){
			this.handle((FeatureCreatedEvent) event);
		} else if (event instanceof FeatureRemovedEvent){
			this.handle((FeatureRemovedEvent) event);
		} else if (event instanceof SetStyleEvent){
			this.handle((SetStyleEvent) event);
		} else if (event instanceof BoxChangedEvent){
			this.handle((BoxChangedEvent) event);
		} else if (event instanceof DrawLayersEvent){
			this.handle((DrawLayersEvent)event);
		} else if (event instanceof FeatureEditedEvent){
			this.handle((FeatureEditedEvent)event);
		} else if (event instanceof GetFeatureEvent){
			this.handle((GetFeatureEvent) event);
		} else if (event instanceof ClearGeometriesEvent){
			this.handle((ClearGeometriesEvent) event);
		}
		
	}
	/**
	 * Draws the specified layers on the buffer that was selected
	 * in {@link DrawLayersEvent}.
	 * This method clean the specified buffer before the draw.
	 * And add all the actions that user added on {@link UndoRedoController} undo list.
	 * 
	 * @param event with the names of the layers and the buffer that this layers will be draw.
	 */
	public void handle(DrawLayersEvent event){
		if (event == null || event.getLayerNames().size() == 0){
			return;
		}
		
		this.layersToDraw = event.getLayerNames();
		
		try {
			this.dispatch(transmitter, new CleanBufferEvent(this, event.getBufferId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (actionSet.getActionSet().size() > 0){
			try {
				this.dispatch(transmitter, new AddUndoRedoActionEvent(this, this.actionSet));
				this.actionSet = new UndoRedoActionSet();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (String layerName : event.getLayerNames()){
			SimpleFeatureCollection featureCollection = this.featureMap.get(layerName);
			Style style = this.featureStyleMap.get(layerName);
			
			if (featureCollection != null && style != null && featureCollection.size() > 0){
				this.graphicalService.drawFeatureCollection((GeometryCanvas)event.getCanvas(), featureCollection, style, event.getBufferId());
				DrawTilesEvent drawTilesEvent = new DrawTilesEvent(this);
				try {
					this.dispatch(transmitter, drawTilesEvent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Handle the {@link FeatureCreatedEvent} adding the feature in a specified layers.
	 * If the event is undoredoable this method creates a {@link FeatureCreatedAction} and add into
	 * the {@link UndoRedoActionSet}.
	 * 
	 * @param event with a feature and layer name.
	 */
	public void handle(FeatureCreatedEvent event){
		
		if (event == null || event.getFeature() == null || event.getLayerName() == null){
			return;
		}
		
		this.addFeature(event.getLayerName(), event.getFeature(), this.featureMap);
		
		FeatureCreatedAction createAction = new FeatureCreatedAction(this, event.getFeature(), event.getLayerName());
		
		try {
			if (event.isUndoRedoable()){
				//this.dispatch(transmitter, new AddUndoRedoActionEvent(this, createAction));
				this.actionSet.getActionSet().add(createAction);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Handle the {@link FeatureRemovedEvent} removing the feature from a specified layer.
	 * If the event is undoredoable this method creates a {@link FeatureRemovedAction} and add into
	 * the {@link UndoRedoActionSet}.
	 * 
	 * @param event with a feature and layer name.
	 */
	public void handle(FeatureRemovedEvent event){
		
		if (event == null || event.getFeature() == null || event.getLayerName() == null){
			return;
		}
		
		this.removeFeature(event.getLayerName(), event.getFeature(), this.featureMap);
		
		FeatureRemovedAction removeAction = new FeatureRemovedAction(this, event.getFeature(), event.getLayerName());
		
		try {
			if (event.isUndoRedoable()){
				//this.dispatch(transmitter, new AddUndoRedoActionEvent(this, removeAction));
				this.actionSet.getActionSet().add(removeAction);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//this.dispatch(transmitter, new CleanBufferEvent(this, BufferEnum.EDITION));
		
	}
	
	/**
	 * Handle the {@link FeatureEditedEvent} removing the "beforeFeature" of the layer and adding the "afterFeature"
	 * in the layer.
	 * If the event is undoredoable this creates a {@link FeatureEditedAction} and add into the {@link UndoRedoActionSet}.
	 * 
	 * @param event features and layer name.
	 */
	public void handle(FeatureEditedEvent event){
		
		this.removeFeature(event.getLayerName(), event.getBeforeFeature(), this.featureMap);
		this.addFeature(event.getLayerName(), event.getBeforeFeature(), this.featureEditedMap);
		this.addFeature(event.getLayerName(), event.getAfterFeature(), this.featureMap);
		
		if (event.isUndoRedoable()){
			FeatureEditedAction editAction = new FeatureEditedAction(this, event.getBeforeFeature(), event.getAfterFeature(), event.getLayerName());
			this.actionSet.getActionSet().add(editAction);
		}
		//this.dispatch(transmitter, new CleanBufferEvent(this, BufferEnum.EDITION));
	}
	
	/**
	 * Handle the {@link SetStyleEvent} associating a style with
	 * the specified layer.
	 * 
	 * @param event with style and layer name.
	 */
	public void handle(SetStyleEvent event){
		this.addStyle(event.getLayerName(), event.getStyle());
	}
	
	/**
	 * Handle the {@link BoxChangedEvent} redrawing the current features.
	 * 
	 * @param event
	 */
	public void handle(BoxChangedEvent event){
		
		for (String layerName : layersToDraw){
			SimpleFeatureCollection featureCollection = this.featureMap.get(layerName);
			Style style = this.featureStyleMap.get(layerName);
			
			if (featureCollection != null && style != null && featureCollection.size() > 0){
				this.graphicalService.drawFeatureCollection((GeometryCanvas)event.getCanvas(), featureCollection, style, BufferEnum.EDITION);
				DrawTilesEvent drawTilesEvent = new DrawTilesEvent(this);
				try {
					this.dispatch(transmitter, drawTilesEvent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Handle the {@link GetFeatureEvent} populating this event
	 * with a {@link SimpleFeatureCollection} with the specified layer features.
	 * 
	 * @param event with layer name.
	 */
	public void handle(ClearGeometriesEvent event){
		if (event == null || event.getLayerName() == null){
			return;
		}		
		featureMap.remove(event.getLayerName());
	}
	
	
	/**
	 * Handle the {@link GetFeatureEvent} populating this event
	 * with a {@link SimpleFeatureCollection} with the specified layer features.
	 * 
	 * @param event with layer name.
	 */
	public void handle(GetFeatureEvent event){
		if (event == null || event.getLayerName() == null){
			return;
		}
		
		SimpleFeatureCollection featureCollection = this.getOrCreateFeatureCollection(event.getLayerName(), this.featureMap);
		
		event.setFeatureCollection(featureCollection);
	}


	@Override
	public boolean undo(UndoRedoAction action, EventDispatcher dispatcher) {
		
		if (action instanceof FeatureCreatedAction){
			
			this.undo((FeatureCreatedAction)action, dispatcher);
			
		} else if (action instanceof FeatureEditedAction){
			
			this.undo((FeatureEditedAction)action, dispatcher);
			
		} else if (action instanceof FeatureRemovedAction){
			
			this.undo((FeatureRemovedAction)action, dispatcher);
			
		}
		
		return true;
	}
	
	public boolean undo(FeatureCreatedAction action, EventDispatcher dispatcher){
		SimpleFeatureCollection collection = this.featureMap.get(action.getLayerName());
		
		collection.remove(action.getFeature());
		
		
		return true;
	}
	
	public boolean undo(FeatureEditedAction action, EventDispatcher dispatcher){
		
		this.removeFeature(action.getLayerName(), action.getAfterFeature(), featureMap);
		this.addFeature(action.getLayerName(), action.getBeforeFeature(), featureMap);
		
		return true;
	}
	
	public boolean undo(FeatureRemovedAction action, EventDispatcher dispatcher){
		SimpleFeatureCollection collection = this.featureMap.get(action.getLayerName());
		
		collection.add(action.getFeature());
		
		return true;
	}

	@Override
	public boolean redo(UndoRedoAction action, EventDispatcher dispatcher) {
		
		if (action instanceof FeatureCreatedAction){
			
			this.redo((FeatureCreatedAction)action, dispatcher);
			
		} else if (action instanceof FeatureEditedAction){
			
			this.redo((FeatureEditedAction)action, dispatcher);
			
		} else if (action instanceof FeatureRemovedAction){
			
			this.redo((FeatureRemovedAction)action, dispatcher);
			
		}
		
		return true;
	}
	
	public boolean redo(FeatureCreatedAction action, EventDispatcher dispatcher){
		SimpleFeatureCollection collection = this.featureMap.get(action.getLayerName());
		
		collection.add(action.getFeature());
		
		return true;
	}
	
	public boolean redo(FeatureRemovedAction action, EventDispatcher dispatcher){
		SimpleFeatureCollection collection = this.featureMap.get(action.getLayerName());
		
		collection.remove(action.getFeature());
		
		return true;
	}
	public boolean redo(FeatureEditedAction action, EventDispatcher dispatcher){
		
		this.removeFeature(action.getLayerName(), action.getBeforeFeature(), featureMap);
		this.addFeature(action.getLayerName(), action.getAfterFeature(), featureMap);
	
		return true;
	}
}
