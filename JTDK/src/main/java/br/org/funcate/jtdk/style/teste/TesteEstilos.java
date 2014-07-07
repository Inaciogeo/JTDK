package br.org.funcate.jtdk.style.teste;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.xml.transform.TransformerException;

import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.Fill;
import org.geotools.styling.Graphic;
import org.geotools.styling.Mark;
import org.geotools.styling.PolygonSymbolizer;
import org.geotools.styling.Rule;
import org.geotools.styling.SLDTransformer;
import org.geotools.styling.Stroke;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.FilterFactory;

import br.org.funcate.jtdk.style.constant.PreviewConstants;
import br.org.funcate.jtdk.style.constant.WellKnownNames;
import br.org.funcate.jtdk.style.enumeration.AreaStyleEnum;
import br.org.funcate.jtdk.style.enumeration.LineFinalEnum;
import br.org.funcate.jtdk.style.enumeration.LineJoinEnum;
import br.org.funcate.jtdk.style.enumeration.LineStyleEnum;
import br.org.funcate.jtdk.style.model.LineStyleVisual;
import br.org.funcate.jtdk.style.model.PolygonStyleVisual;
import br.org.funcate.jtdk.style.model.factory.SLDBuilder;
import br.org.funcate.jtdk.style.view.PreviewCanvas;

@SuppressWarnings("serial")
public class TesteEstilos extends JFrame {

	private SimpleFeatureBuilder builder;

	private SimpleFeature polygonFeature;

	private StyleFactory styleFactory;

	private FilterFactory filterFactory;

	private SLDTransformer transformer = new SLDTransformer();

	public TesteEstilos() {
		transformer.setIndentation(1);
		this.styleFactory = CommonFactoryFinder.getStyleFactory(null);
		this.filterFactory = CommonFactoryFinder.getFilterFactory(null);
		this.setSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(150, 230);
		PreviewCanvas canvas = new PreviewCanvas(PreviewConstants.POLYGON, this.makePolygonStyle2());
		this.add(canvas);
		canvas.repaint();
		this.repaint();
	}

	private Style makePolygonStyle() {

		Mark crossMark = styleFactory.getDefaultMark();

		crossMark.setWellKnownName(filterFactory.literal(WellKnownNames.SHAPE_TIMES));

		// create a partially opaque outline stroke
		Stroke stroke = styleFactory.createStroke(filterFactory.literal(Color.BLACK), filterFactory.literal(4), filterFactory.literal(0.5));

		// create a partial opaque fill
		Fill fill = styleFactory.createFill(filterFactory.literal(Color.RED), filterFactory.literal(0.5));

		crossMark.setFill(fill);
		// crossMark.setStroke(stroke);

		Graphic graphic = styleFactory.createDefaultGraphic();
		graphic.graphicalSymbols().clear();
		graphic.graphicalSymbols().add(crossMark);

		Fill externalFill = styleFactory.getDefaultFill();
		externalFill.setGraphicFill(graphic);

		try {
			System.out.println(transformer.transform(externalFill));
		} catch (TransformerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*
		 * Setting the geometryPropertyName arg to null signals that we want to
		 * draw the default geometry of features
		 */
		PolygonSymbolizer sym = styleFactory.createPolygonSymbolizer(stroke, externalFill, null);

		Rule rule = styleFactory.createRule();
		rule.symbolizers().add(sym);
		FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(new Rule[] { rule });
		Style style = styleFactory.createStyle();
		style.featureTypeStyles().add(fts);

		try {

			System.out.println(transformer.transform(style));
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return style;
	}

	private Style makePolygonStyle2() {
		SLDBuilder styleBuilder = new SLDBuilder();
		LineStyleVisual lineVisual = new LineStyleVisual(Color.YELLOW, LineStyleEnum.SOLIDA, LineFinalEnum.QUADRADO, LineJoinEnum.BISEL, 4);
		PolygonStyleVisual visual = new PolygonStyleVisual(Color.BLACK, 10, AreaStyleEnum.CRUZ_DIAGONAL, null, 5, lineVisual);

		styleBuilder.addSymbolizer(visual);

		Style style = styleBuilder.buildStyle("Meu Estilo");

		return style;
	}

	private Style makeLineStyle() {
		SLDBuilder styleBuilder = new SLDBuilder();

		LineStyleVisual lineVisual = new LineStyleVisual(Color.BLACK, LineStyleEnum.SOLIDA, LineFinalEnum.ARREDONDADO, LineJoinEnum.BISEL,
				4);

		styleBuilder.addSymbolizer(lineVisual);

		Style style = styleBuilder.buildStyle("Meu Estilo");

		return style;
	}

	public static void main(String[] args) {
		new TesteEstilos();
	}
}
