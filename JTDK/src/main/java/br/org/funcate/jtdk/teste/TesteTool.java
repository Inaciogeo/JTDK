package br.org.funcate.jtdk.teste;

import java.awt.Color;
import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.FeatureCollections;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.styling.ExternalGraphic;
import org.geotools.styling.ExternalGraphicImpl;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.Graphic;
import org.geotools.styling.Mark;
import org.geotools.styling.PointSymbolizer;
import org.geotools.styling.Rule;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.FilterFactory;

import br.org.funcate.eagles.kernel.dispatcher.EventHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandlerImpl;
import br.org.funcate.eagles.kernel.transmitter.DirectedEventTransmitter;
import br.org.funcate.eagles.kernel.transmitter.EventTransmitter;
import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.event.AfterToolChangedEvent;
import br.org.funcate.glue.event.DrawLayersEvent;
import br.org.funcate.glue.event.MouseClickedEvent;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.canvas.BufferEnum;
import br.org.funcate.glue.tool.Tool;
import br.org.funcate.jtdk.edition.event.FeatureCreatedEvent;
import br.org.funcate.jtdk.edition.event.SetStyleEvent;
import br.org.funcate.jtdk.util.ImageIconLoader;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class TesteTool implements Tool{

	private ListenersHandler listeners;
	
	private EventTransmitter transmitter;
	
	private EventHandler eventHandler;
	
	private SimpleFeatureCollection featureCollection;
	
	private List<String> eventsToListen;
	
	private SimpleFeatureType TYPE;
	
	private SimpleFeatureBuilder featureBuilder;
	
	private GeometryFactory geometryFactory = new GeometryFactory();
	
	public static void main(String[] args) throws SchemaException, IOException {
		TesteTool tool = new TesteTool();
		tool.init();
	}

	public TesteTool(){
		this.initListeners();
	}
	
	private void initListeners() {	
		this.transmitter = new DirectedEventTransmitter(this);
		this.listeners = new ListenersHandlerImpl();
		this.eventHandler = new EventHandler();
		this.eventsToListen = new ArrayList<String>();
		this.eventsToListen.add(AfterToolChangedEvent.class.getName());
		this.eventsToListen.add(MouseClickedEvent.class.getName());
		//this.eventsToListen.add(BoxChangedEvent.class.getName());
	}

	private void init() throws SchemaException, IOException {
		JFileChooser chooser = new JFileChooser();
		
		chooser.showOpenDialog(null);
		
        File file = chooser.getSelectedFile();
        
        
        featureCollection = FeatureCollections.newCollection();
        
        TYPE = DataUtilities.createType("Location",
                "location:Point," + // <- the geometry attribute: Point type
                        "name:String," + // <- a String attribute
                        "number:Integer" // a number attribute
        );
        
        /*
         * GeometryFactory will be used to create the geometry attribute of each feature (a Point
         * object for the location)
         */
         //JTSFactoryFinder.getGeometryFactory(null);

        featureBuilder = new SimpleFeatureBuilder(TYPE);

        
        if (file == null) {
        	try {
				this.dispatch(transmitter, new SetStyleEvent(this, this.createPointStyle2(), "pontos"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return;
        }
		
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            /* First line of the data file is the header */
            String line = reader.readLine();
            System.out.println("Header: " + line);

            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.trim().length() > 0) { // skip blank lines
                    String tokens[] = line.split("\\,");

                    double latitude = Double.parseDouble(tokens[0]);
                    double longitude = Double.parseDouble(tokens[1]);
                    String name = tokens[2].trim();
                    int number = Integer.parseInt(tokens[3].trim());

                    /* Longitude (= x coord) first ! */
                    Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));

                    featureBuilder.add(point);
                    featureBuilder.add(name);
                    featureBuilder.add(number);
                    SimpleFeature feature = featureBuilder.buildFeature(null);
                    featureCollection.add(feature);
                }
            }
        } finally {
            reader.close();
        }
        try {
			this.dispatch(transmitter, new SetStyleEvent(this, this.createPointStyle2(), "pontos"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        SimpleFeatureIterator iterator = featureCollection.features();
        
        while (iterator.hasNext()){
        	SimpleFeature feature = iterator.next();

        	try {
				this.dispatch(transmitter, new FeatureCreatedEvent(this, feature, "pontos", false));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}



	@Override
	public void dispatch(EventTransmitter tc, EventObject e) throws Exception {
		tc.dispatch(e);
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
	public void handle(EventObject event) {
		if (event instanceof AfterToolChangedEvent){
			this.handle((AfterToolChangedEvent) event);
		} else if (event instanceof MouseClickedEvent){
			this.handle((MouseClickedEvent) event);
		}
	}

	public void handle(AfterToolChangedEvent event) {
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		if (this == mediator.getCurrentTool()){
			try {
				this.init();
			} catch (SchemaException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void handle (MouseClickedEvent event){
		
		if (event == null){
			return;
		}
		
		Point point = geometryFactory.createPoint(new Coordinate(event.getX(), event.getY()));
		featureBuilder.add(point);
		SimpleFeature feature = featureBuilder.buildFeature(null);
		

		try {
			this.dispatch(transmitter, new FeatureCreatedEvent(this,feature, "pontos", false));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DrawLayersEvent drawLayers = new DrawLayersEvent(this, BufferEnum.EDITION);
		drawLayers.getLayerNames().add("pontos");
		try {
			this.dispatch(transmitter, drawLayers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void setCursor(Cursor cursor) {
		
	}

	@Override
	public Cursor getCursor() {
		return new Cursor(0);
	}
	

	private Style createPointStyle2(){
		StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory(null);
    	FilterFactory filterFactory = CommonFactoryFinder.getFilterFactory(null);
    	
    	//Objeto que define como uma geometria ou um raster devem ser exibidos, com rela��o a cores, tamanho, etc.
        Graphic gr = styleFactory.createDefaultGraphic();

        // Faz parte da composi��o do Graphic.
        Mark mark = styleFactory.getCircleMark();
        mark.setStroke(styleFactory.createStroke(filterFactory.literal(Color.BLACK), filterFactory.literal(1)));
        mark.setFill(styleFactory.createFill(filterFactory.literal(Color.red)));
        
        
        gr.graphicalSymbols().clear();
        gr.graphicalSymbols().add(mark);
        gr.setSize(filterFactory.literal(10));
        
        //Cria um Symbolizer para ponto com base no que foi setado em Graphic.
        PointSymbolizer pointSymbolizer = styleFactory.createPointSymbolizer(gr, null);
    	
        //Regra para um estilo de fei��o.
        Rule rule = styleFactory.createRule();
        //Adiciona o PointSymbolizer na regra.
        rule.symbolizers().add(pointSymbolizer);
        
        //Adiciona uma ou N regras em uma estilo de uma determinada fei��o.
        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(new Rule[]{rule});
        
        //Cria o estilo (SLD).
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);
        
        return style;
	}
	
	/**
     * Create a Style to draw point features as circles with blue outlines
     * and cyan fill
     * @throws URISyntaxException 
     */
    public static Style createPointStyle() {
    	StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory(null);
    	FilterFactory filterFactory = CommonFactoryFinder.getFilterFactory(null);
    	
        Graphic gr = styleFactory.createDefaultGraphic();

        Mark mark = styleFactory.getCircleMark();
		
		ImageIcon imageIcon = ImageIconLoader.createImageIcon("br/org/funcate/jtdk/teste/fogo2.png", TesteTool.class);
		
		java.net.URL imgURL = TesteTool.class.getClassLoader().getResource("br/org/funcate/jtdk/teste/fogo2.png");
		
		
        mark.setStroke(styleFactory.createStroke(filterFactory.literal(Color.BLACK), filterFactory.literal(1)));

		ExternalGraphic external = new ExternalGraphicImpl(imageIcon, null, null);
		external.setFormat("image/png");
		external.setLocation(imgURL);

        gr.graphicalSymbols().clear();
        gr.graphicalSymbols().add(mark);
        gr.graphicalSymbols().add(external);
        gr.setSize(filterFactory.literal(20));

        /*
         * Setting the geometryPropertyName arg to null signals that we want to
         * draw the default geomettry of features
         */
        PointSymbolizer sym = styleFactory.createPointSymbolizer(gr, null);

        Rule rule = styleFactory.createRule();
        rule.symbolizers().add(sym);
        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(new Rule[]{rule});
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }

	@Override
	public List<String> getEventsToListen() {
		return this.eventsToListen;
	}
}
