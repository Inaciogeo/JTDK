package br.org.funcate.jtdk.canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

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
import org.geotools.renderer.GTRenderer;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.Graphic;
import org.geotools.styling.LineSymbolizer;
import org.geotools.styling.Mark;
import org.geotools.styling.PointSymbolizer;
import org.geotools.styling.Rule;
import org.geotools.styling.Stroke;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.FilterFactory;

import br.org.funcate.glue.controller.listener.CanvasAdapter;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.canvas.BufferEnum;
import br.org.funcate.glue.model.canvas.CanvasGraphicsBuffer;
import br.org.funcate.jtdk.style.LineStyleDefinition;
import br.org.funcate.jtdk.style.PointStyleDefinition;
import br.org.funcate.jtdk.style.WellKnownNames;

import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

@SuppressWarnings("serial")
public class SFSCanvas extends GeometryCanvas {

	/**
	 * This is a Renderer of GeoTools library to draw Layers into Java a
	 * Graphics2D.
	 */
	private GTRenderer renderer;

	/**
	 * This is a factory to make styles.
	 */
	private StyleFactory styleFactory;

	/**
	 * This is a factory to make filters.
	 */
	private FilterFactory filterFactory;

	/**
	 * This is a style definition for points.
	 */
	private PointStyleDefinition pointStyle;

	/**
	 * This is a style definition for lines
	 */
	private LineStyleDefinition lineStyle;

	/**
	 * This is the Geotools feature type for points.
	 */
	private SimpleFeatureType pointType;

	/**
	 * This is the Geotools feature type for lines.
	 */
	private SimpleFeatureType lineType;

	/**
	 * This is the point feature builder.
	 */
	private SimpleFeatureBuilder pointBuilder;

	/**
	 * This is the line feature builder.
	 */
	private SimpleFeatureBuilder lineBuilder;

	/**
	 * This is the Canvas background's Color.
	 */
	private Color backgroundColor;

	/**
	 * Constructor.
	 * 
	 * @param width
	 *            of Canvas
	 * @param height
	 *            of Canvas
	 */
	public SFSCanvas(int width, int height) {
		Dimension d = new Dimension(width, height);
		this.setSize(d);
		this.renderer = new StreamingRenderer();
		this.backgroundColor = Color.white;
		this.styleFactory = CommonFactoryFinder.getStyleFactory(null);
		this.filterFactory = CommonFactoryFinder.getFilterFactory(null);
		this.pointStyle = new PointStyleDefinition();
		this.lineStyle = new LineStyleDefinition();
		try {
			this.pointType = DataUtilities.createType("Point", "location:Point");
			this.lineType = DataUtilities.createType("Line", "line:LineString");
		} catch (SchemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pointBuilder = new SimpleFeatureBuilder(pointType);
		this.lineBuilder = new SimpleFeatureBuilder(lineType);
		this.setBackground(backgroundColor);
		this.addAdapter();
	}

	private void addAdapter() {
		CanvasAdapter canvasAdapter = new CanvasAdapter(this);

		this.addMouseListener(canvasAdapter);
		this.addMouseMotionListener(canvasAdapter);
		this.addMouseWheelListener(canvasAdapter);
		this.addKeyListener(canvasAdapter);

	}

	@Override
	public void drawPointW(Point point) {
		Style pointStyle = this.makePointStyle();
		SimpleFeatureCollection collection = FeatureCollections.newCollection();

		pointBuilder.add(point);

		SimpleFeature pointFeature = pointBuilder.buildFeature(null);

		collection.add(pointFeature);

		Layer layer = new FeatureLayer(collection, pointStyle);

		this.drawLayer(layer);
	}

	private void drawLayer(Layer layer) {
		MapContent map = new MapContent();

		map.addLayer(layer);

		renderer.setMapContent(map);

		CanvasGraphicsBuffer bufferManager = (CanvasGraphicsBuffer) canvasGraphicsBuffer;

		BufferedImage image = bufferManager.getBuffer(BufferEnum.EDITION);

		Rectangle imageBounds = new Rectangle(0, 0, image.getWidth(), image.getHeight());
		Box box = AppSingleton.getInstance().getCanvasState().getBox();

		// esse � o jeito que deve ser --> ReferencedEnvelope mapBounds = new
		// ReferencedEnvelope(coordTransformer.getWindow(), null);

		ReferencedEnvelope mapBounds = new ReferencedEnvelope(box.getX1(), box.getX2(), box.getY1(), box.getY2(), null);

		Graphics2D gr = (Graphics2D) image.createGraphics();

		renderer.paint(gr, imageBounds, mapBounds);

		bufferManager.setEditionX(0);
		bufferManager.setEditionY(0);

		gr.dispose();

		map.dispose();
	}

	@Override
	public void drawLineStringW(LineString line) {
		Style style = this.makeLineStyle();

		SimpleFeatureCollection collection = FeatureCollections.newCollection();

		lineBuilder.add(line);

		SimpleFeature lineFeature = lineBuilder.buildFeature(null);

		collection.add(lineFeature);

		Layer layer = new FeatureLayer(collection, style);

		this.drawLayer(layer);
	}

	@Override
	public void drawPolygonW(Polygon polygon) {
		// TODO Auto-generated method stub

	}

	/*
	 * @Override public int getWidth() { return .getWidth(); }
	 * 
	 * @Override public int getHeight() { return this.canvas.getHeight(); }
	 * 
	 * @Override public void setDimension(Dimension d) { this.canvas.setSize(d);
	 * }
	 */

	@Override
	public void setPointColor(int r, int g, int b) {
		this.pointStyle.setColor(new Color(r, g, b));
	}

	@Override
	public void setPointContourWidth(int width) {
		this.pointStyle.setContourWidth(width);

	}

	@Override
	public void setPointWellKnownName(String wellKnownName) {
		this.pointStyle.setWellKnownName(wellKnownName);

	}

	@Override
	public void setPointContourColor(int r, int g, int b) {
		this.pointStyle.setContourColor(new Color(r, g, b));
	}

	@Override
	public void setPointSize(int size) {
		this.pointStyle.setSize(size);
	}

	private Style makePointStyle() {
		Graphic gr = styleFactory.createDefaultGraphic();

		Mark mark = styleFactory.createMark();

		Color contourColor = Color.black;

		if (pointStyle.isSetContourColor()) {
			contourColor = pointStyle.getContourColor();
		}

		int contourWidth = 1;

		if (pointStyle.isSetContourWidth()) {
			contourWidth = pointStyle.getContourWidth();
		}

		Color color = Color.white;

		if (pointStyle.isSetColor()) {
			color = pointStyle.getColor();
		}

		String wellKnownName = WellKnownNames.SQUARE;

		if (pointStyle.isSetWellKnownName()) {
			wellKnownName = pointStyle.getWellKnownName();
		}

		int pointSize = 1;

		if (pointStyle.isSetSize()) {
			pointSize = pointStyle.getSize();
		}

		mark.setStroke(styleFactory.createStroke(filterFactory.literal(contourColor), filterFactory.literal(contourWidth)));
		mark.setFill(styleFactory.createFill(filterFactory.literal(color)));
		mark.setWellKnownName(filterFactory.literal(wellKnownName));

		mark.setStroke(styleFactory.createStroke(filterFactory.literal(contourColor), filterFactory.literal(contourWidth)));

		gr.graphicalSymbols().clear();
		gr.graphicalSymbols().add(mark);
		gr.setSize(filterFactory.literal(pointSize));
		gr.setRotation(filterFactory.literal(pointStyle.getRotation()));

		PointSymbolizer sym = styleFactory.createPointSymbolizer(gr, null);

		Rule rule = styleFactory.createRule();
		rule.symbolizers().add(sym);
		FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(new Rule[] { rule });
		Style style = styleFactory.createStyle();
		style.featureTypeStyles().add(fts);

		return style;
	}

	private Style makeLineStyle() {

		Color lineColor = Color.black;
		int lineWidth = 1;

		if (lineStyle.isSetColor()) {
			lineColor = lineStyle.getColor();
		}

		if (lineStyle.isSetWidth()) {
			lineWidth = lineStyle.getWidth();
		}

		Stroke stroke = styleFactory.createStroke(filterFactory.literal(lineColor), filterFactory.literal(lineWidth),
				filterFactory.literal("round"));

		/*
		 * Setting the geometryPropertyName arg to null signals that we want to
		 * draw the default geomettry of features
		 */
		LineSymbolizer line = styleFactory.createLineSymbolizer(stroke, null);

		Rule rule = styleFactory.createRule();
		rule.symbolizers().add(line);
		FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(new Rule[] { rule });
		Style style = styleFactory.createStyle();

		if (lineStyle.isSetBorder()) {
			Color borderColor = Color.black;
			int borderWidth = 1;

			LineStyleDefinition border = lineStyle.getBorder();

			if (border.isSetColor()) {
				borderColor = border.getColor();
			}

			if (border.isSetWidth()) {
				borderWidth = border.getWidth() + lineWidth;
			}

			Stroke borderStroke = styleFactory.createStroke(filterFactory.literal(borderColor), filterFactory.literal(borderWidth),
					filterFactory.literal("round"));

			LineSymbolizer borderLine = styleFactory.createLineSymbolizer(borderStroke, null);

			Rule borderRule = styleFactory.createRule();
			borderRule.symbolizers().add(borderLine);
			FeatureTypeStyle borderStyle = styleFactory.createFeatureTypeStyle(new Rule[] { borderRule });

			style.featureTypeStyles().add(borderStyle);
		}

		style.featureTypeStyles().add(fts);

		return style;
	}

	@Override
	public void setPointRotation(int rotation) {
		this.pointStyle.setRotation(rotation);

	}

	@Override
	public void setPointColor(int r, int g, int b, int alpha) {
		this.pointStyle.setColor(new Color(r, g, b, alpha));

	}

	@Override
	public void setPointContourColor(int r, int g, int b, int alpha) {
		this.pointStyle.setContourColor(new Color(r, g, b, alpha));
	}

	@Override
	public void setLineColor(int r, int g, int b, int alpha) {
		this.lineStyle.setColor(new Color(r, g, b, alpha));

	}

	@Override
	public void setLineWidth(int width) {
		this.lineStyle.setWidth(width);

	}

	@Override
	public void setLineBorderColor(int r, int g, int b, int alpha) {
		if (!this.lineStyle.isSetBorder()) {
			this.lineStyle.setBorder(new LineStyleDefinition());
		}
		this.lineStyle.getBorder().setColor(new Color(r, g, b, alpha));
	}

	@Override
	public void setLineBorderWidth(int width) {
		if (!this.lineStyle.isSetBorder()) {
			this.lineStyle.setBorder(new LineStyleDefinition());
		}
		this.lineStyle.getBorder().setWidth(width);

	}

}
