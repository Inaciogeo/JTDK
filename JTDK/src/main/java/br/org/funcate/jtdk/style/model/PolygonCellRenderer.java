package br.org.funcate.jtdk.style.model;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

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

import br.org.funcate.jtdk.style.enumeration.AreaStyleEnum;
import br.org.funcate.jtdk.style.enumeration.LineFinalEnum;
import br.org.funcate.jtdk.style.enumeration.LineJoinEnum;
import br.org.funcate.jtdk.style.enumeration.LineStyleEnum;
import br.org.funcate.jtdk.style.model.factory.SLDBuilder;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

@SuppressWarnings("serial")
public class PolygonCellRenderer extends JPanel implements TableCellRenderer {

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
	@SuppressWarnings("unused")
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
	 * 
	 */
	private SLDBuilder SLDbuilder;

	public PolygonCellRenderer() {
		this.renderer = new StreamingRenderer();
		this.initPolygonType();
		this.box = new ReferencedEnvelope(11.0, 1.9, 11.0, -1.4737, null);
		this.SLDbuilder = new SLDBuilder();
		this.image = new BufferedImage(120, 25, BufferedImage.BITMASK);
		this.builder = new SimpleFeatureBuilder(this.type);
		this.feature = this.makePolygonFeature();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		this.cleanImage();
		if (isSelected) {
			Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
			this.setBorder(border);
		} else {
			Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
			this.setBorder(border);
		}

		PolygonStyleVisual visual = (PolygonStyleVisual) value;

		this.SLDbuilder.addSymbolizer(visual);

		this.style = SLDbuilder.buildStyle("Polygon");

		this.draw();

		return this;
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

	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Makes a Polygon feature instance.
	 * 
	 * @return
	 */
	private SimpleFeature makePolygonFeature() {
		GeometryFactory factory = new GeometryFactory();
		LinearRing shell = factory.createLinearRing(new Coordinate[] { new Coordinate(2.3523, 2.5), new Coordinate(12.0, 2.5),
				new Coordinate(12.0, 5.0), new Coordinate(2.3523, 5.0), new Coordinate(2.3523, 2.5) });

		Polygon polygon = factory.createPolygon(shell, null);

		builder.add(polygon);
		SimpleFeature polygonFeature = builder.buildFeature(null);

		return polygonFeature;
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

		this.renderer.paint(gr, rect, collection.getBounds());

		this.repaint();

		map.dispose();

	}

	public static PolygonStyleVisual createDefaultVisual() {

		LineStyleVisual contourVisual = new LineStyleVisual(Color.BLACK, LineStyleEnum.SOLIDA, LineFinalEnum.PADRAO, LineJoinEnum.MILTRA,
				1.00);

		PolygonStyleVisual visual = new PolygonStyleVisual(Color.RED, 0, AreaStyleEnum.SOLIDO, null, 0, contourVisual);

		return visual;
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

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, 2, 2, null);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(new PolygonCellRenderer().getTableCellRendererComponent(null, PolygonCellRenderer.createDefaultVisual(), true, true, 1, 1));
		frame.repaint();
	}
}
