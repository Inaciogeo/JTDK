package br.org.funcate.jtdk.style.view;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureCollections;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.renderer.GTRenderer;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.Style;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import br.org.funcate.jtdk.style.constant.PreviewConstants;
import br.org.funcate.jtdk.util.ImageIconLoader;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

/**
 * This canvas can be used to draw a feature with a geometry (Point, LineString
 * or Polygon).
 * 
 * @author Moraes, Emerson Leite.
 * 
 */
@SuppressWarnings("serial")
public class PreviewCanvas extends Canvas {

	/**
	 * Geotools renderer
	 */
	private GTRenderer renderer;

	/**
	 * Buffer to be draw.
	 */
	private BufferedImage image;

	/**
	 * Feature type to define the type to be draw.
	 */
	private SimpleFeatureType type;

	/**
	 * Feature Builder.
	 */
	private SimpleFeatureBuilder builder;

	/**
	 * Box of this canvas.
	 */
	private ReferencedEnvelope box;

	/**
	 * Feature to be draw.
	 */
	private SimpleFeature feature;

	/**
	 * Style of feature to be draw.
	 */
	private Style style;

	/**
	 * Geometry type See {@link PreviewConstants}
	 */
	private int geometryType;

	/**
	 * Polygon background image
	 */
	private ImageIcon backgroundImage;

	/**
	 * Constructor.
	 * 
	 * @param geometryType
	 * @param style
	 */
	public PreviewCanvas(int geometryType, Style style) {
		this.backgroundImage = ImageIconLoader.createImageIcon("br/org/funcate/jtdk/style/img/world.gif", PreviewCanvas.class);
		this.renderer = new StreamingRenderer();
		this.geometryType = geometryType;
		this.image = new BufferedImage(140, 174, BufferedImage.BITMASK);
		this.box = new ReferencedEnvelope(11.0, 1.9, 11.0, -1.4737, null);
		this.initFeatureType();
		this.setStyle(style);
	}

	/**
	 * Initialize the type, builder and feature.
	 */
	private void initFeatureType() {
		if (this.geometryType == PreviewConstants.POLYGON) {
			this.initPolygonType();
			this.builder = new SimpleFeatureBuilder(type);
			this.feature = this.makePolygonFeature();
		} else if (this.geometryType == PreviewConstants.LINE) {
			this.initLineType();
			this.builder = new SimpleFeatureBuilder(type);
			this.feature = this.makeLineFeature();
		} else if (this.geometryType == PreviewConstants.POINT) {
			this.initPointType();

		}

	}

	/**
	 * Initialize Polygon feature type.
	 */
	private void initPolygonType() {
		try {
			type = DataUtilities.createType("Poligono", "poligono:Polygon");
		} catch (SchemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize LineString feature type.
	 */
	private void initLineType() {
		try {
			type = DataUtilities.createType("Linha", "linha:LineString");
		} catch (SchemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize Point feature type.
	 */
	private void initPointType() {
		try {
			type = DataUtilities.createType("Point", "point:Point");
		} catch (SchemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Makes a Polygon feature instance.
	 * 
	 * @return
	 */
	private SimpleFeature makePolygonFeature() {
		GeometryFactory factory = new GeometryFactory();
		LinearRing shell = factory.createLinearRing(new Coordinate[] { new Coordinate(2.3523, 6.8), new Coordinate(4.0, 7.8),
				new Coordinate(2.4, 8.5), new Coordinate(4.5, 9.9), new Coordinate(5.5, 8.3), new Coordinate(7.0, 9.8),
				new Coordinate(8.0, 8.0), new Coordinate(9.3, 9.3), new Coordinate(9.7, 7.0), new Coordinate(9.0, 5.3),
				new Coordinate(9.5, 1.0), new Coordinate(8.0, 2.5), new Coordinate(7.2, 0.7), new Coordinate(6.0, 1.3),
				new Coordinate(4.0, 0.8), new Coordinate(2.4, 2.0), new Coordinate(3.0, 3.8), new Coordinate(2.3523, 6.8) });

		Polygon polygon = factory.createPolygon(shell, null);

		builder.add(polygon);
		SimpleFeature polygonFeature = builder.buildFeature(null);

		return polygonFeature;
	}

	/**
	 * Makes a LineString feature instance.
	 * 
	 * @return
	 */
	private SimpleFeature makeLineFeature() {
		GeometryFactory factory = new GeometryFactory();

		LineString line = factory.createLineString(new Coordinate[] { new Coordinate(2.3523, 1.0), new Coordinate(9.0, 1.0),
				new Coordinate(2.3523, 10.0), new Coordinate(9.0, 10.0) });

		builder.add(line);
		SimpleFeature lineFeature = builder.buildFeature(null);

		return lineFeature;
	}

	/**
	 * Draw the current feature with current style on the bufferedImage.
	 */
	private void draw() {
		SimpleFeatureCollection collection = FeatureCollections.newCollection();

		collection.add(feature);

		Layer layer = new FeatureLayer(collection, this.style);

		MapContent map = new MapContent();

		map.addLayer(layer);

		this.renderer.setMapContent(map);

		Graphics2D gr = image.createGraphics();
		Rectangle rect = new Rectangle(image.getWidth(), image.getHeight());

		this.renderer.paint(gr, rect, box);

		this.repaint();

		map.dispose();
	}

	/**
	 * Sets the new style and draw the current feature with this new style.
	 * 
	 * @param style
	 *            new style.
	 */
	public void setStyle(Style style) {
		this.style = style;
		if (this.style != null) {
			this.cleanImage();
			this.draw();
		}
	}

	/**
	 * Clean buffer.
	 */
	private void cleanImage() {
		Graphics2D g2d = image.createGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
		Rectangle2D rect = new Rectangle2D.Double(0, 0, image.getWidth(), image.getHeight());
		g2d.fill(rect);
	}

	/**
	 * Paint override.
	 */
	public void paint(Graphics g) {
		if (image == null) {
			return;
		}

		if (geometryType == PreviewConstants.POLYGON) {
			this.backgroundImage.paintIcon(this, g, 0, 0);
		}

		g.drawImage(image, 0, 0, null);
	}
}
