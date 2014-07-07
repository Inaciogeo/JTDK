package br.org.funcate.jtdk.teste;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.FeatureCollections;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.metadata.iso.citation.OnLineResourceImpl;
import org.geotools.renderer.GTRenderer;
import org.geotools.renderer.lite.StreamingRenderer;
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
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.style.ColorReplacement;

import br.org.funcate.jtdk.util.ImageIconLoader;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class ManipulandoFeatures {

	
	public static SimpleFeatureCollection criarFeatureCollection() throws SchemaException, IOException{
		JFileChooser chooser = new JFileChooser();
		
		chooser.showOpenDialog(null);
		
        File file = chooser.getSelectedFile();
        if (file == null) {
            return null;
        }
        
        /*
         * We use the DataUtilities class to create a FeatureType that will describe the data in our
         * shapefile.
         * 
         * See also the createFeatureType method below for another, more flexible approach.
         */
        final SimpleFeatureType TYPE = DataUtilities.createType("Location",
                "location:Point," + // <- the geometry attribute: Point type
                        "name:String," + // <- a String attribute
                        "number:Integer" // a number attribute
        );
        
        /*
         * We create a FeatureCollection into which we will put each Feature created from a record
         * in the input csv data file
         */
        SimpleFeatureCollection collection = FeatureCollections.newCollection();
        
        /*
         * GeometryFactory will be used to create the geometry attribute of each feature (a Point
         * object for the location)
         */
        GeometryFactory geometryFactory = new GeometryFactory(); //JTSFactoryFinder.getGeometryFactory(null);

        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);

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
                    collection.add(feature);
                }
            }
        } finally {
            reader.close();
        }
        
        
        return collection;
	}
	
	public static void main(String[] args) throws SchemaException, IOException {
		SimpleFeatureCollection collection = criarFeatureCollection();
		
		MapContent mapContent = new MapContent();
		
		Layer layer = new FeatureLayer(collection, ManipulandoFeatures.createPointStyle());
		mapContent.addLayer(layer);
		
		GTRenderer renderer = new StreamingRenderer();
	    renderer.setMapContent(mapContent);
	    
	    
	    int imageWidth = 500;
	    
	    Rectangle imageBounds = null;
	    ReferencedEnvelope mapBounds = null;
	    try {
	        mapBounds = mapContent.getMaxBounds();
	        //double heightToWidth = mapBounds.getSpan(1) / mapBounds.getSpan(0);
	        imageBounds = new Rectangle(
	                0, 0, imageWidth, (int) 200);

	    } catch (Exception e) {
	        // failed to access map layers
	        throw new RuntimeException(e);
	    }

	    BufferedImage image = new BufferedImage(imageBounds.width, imageBounds.height, BufferedImage.TYPE_INT_RGB);

	    Graphics2D gr = image.createGraphics();
	    gr.setPaint(Color.WHITE);
	    gr.fill(imageBounds);
	    
	    renderer.paint(gr, imageBounds, mapBounds);
	    
	    new Tela(image);
		//JMapFrame.showMap(mapContent);
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
		
		ImageIcon imageIcon = ImageIconLoader.createImageIcon("org/funcate/feature/fogo2.png", ManipulandoFeatures.class);
		
		java.net.URL imgURL = ManipulandoFeatures.class.getClassLoader().getResource("org/funcate/jtdk/teste/fogo2.png");
		
		
        mark.setStroke(styleFactory.createStroke(filterFactory.literal(Color.BLACK), filterFactory.literal(1)));

        //mark.setFill(styleFactory.createFill(filterFactory.literal(Color.red)));
        @SuppressWarnings("unused")
		OnLineResource online = null;
        
        try {
			 online = new OnLineResourceImpl(imgURL.toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("unused")
		List<ColorReplacement> lista = new ArrayList<ColorReplacement>();
		ExternalGraphic external = new ExternalGraphicImpl(imageIcon, null, null);
		external.setFormat("image/png");
		external.setLocation(imgURL);
        System.out.println(external.toString());
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
}
