package br.org.funcate.jtdk.style.model.factory;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import org.geotools.factory.CommonFactoryFinder;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.Fill;
import org.geotools.styling.Graphic;
import org.geotools.styling.LineSymbolizer;
import org.geotools.styling.Mark;
import org.geotools.styling.PolygonSymbolizer;
import org.geotools.styling.Rule;
import org.geotools.styling.Stroke;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.geotools.styling.Symbolizer;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory;

import br.org.funcate.jtdk.style.constant.WellKnownNames;
import br.org.funcate.jtdk.style.enumeration.AreaStyleEnum;
import br.org.funcate.jtdk.style.enumeration.LineFinalEnum;
import br.org.funcate.jtdk.style.enumeration.LineJoinEnum;
import br.org.funcate.jtdk.style.enumeration.LineStyleEnum;
import br.org.funcate.jtdk.style.model.LineStyleVisual;
import br.org.funcate.jtdk.style.model.PolygonStyleVisual;

public class SLDBuilder {

	private StyleFactory styleFactory;

	private FilterFactory filterFactory;

	private List<Symbolizer> symbolizers;

	public SLDBuilder() {
		this.styleFactory = CommonFactoryFinder.getStyleFactory(null);
		this.filterFactory = CommonFactoryFinder.getFilterFactory(null);
		this.symbolizers = new ArrayList<Symbolizer>();
	}

	public void addSymbolizer(LineStyleVisual lineVisual) {

		Stroke stroke = this.createStroke(lineVisual);

		LineSymbolizer sym = styleFactory.createLineSymbolizer(stroke, null);

		this.symbolizers.add(sym);
	}

	private Stroke createStroke(LineStyleVisual lineVisual) {
		Color lineColor = lineVisual.getColor();
		double lineWidth = lineVisual.getWidth();
		LineJoinEnum lineJoin = lineVisual.getJoin();
		LineFinalEnum lineFinal = lineVisual.getLineFinal();
		LineStyleEnum style = lineVisual.getStyle();

		Stroke stroke = styleFactory.createStroke(filterFactory.literal(lineColor), filterFactory.literal(lineWidth));

		switch (style) {

		case SOLIDA:
			break;

		case PONTILHADA:
			stroke.setDashArray(new float[] { 2.0f, 2.0f });
			break;

		case TRACEJADA:
			stroke.setDashArray(new float[] { 10, 6 });
			break;
		}

		switch (lineFinal) {

		case PADRAO:
			stroke.setLineCap(filterFactory.literal(WellKnownNames.LINE_BUTT));
			break;

		case QUADRADO:
			stroke.setLineCap(filterFactory.literal(WellKnownNames.LINE_SQUARE));
			break;

		case ARREDONDADO:
			stroke.setLineCap(filterFactory.literal(WellKnownNames.LINE_ROUND));
			break;
		}

		switch (lineJoin) {

		case MILTRA:
			stroke.setLineJoin(filterFactory.literal(WellKnownNames.LINE_MITER));
			break;

		case ARREDONDADO:
			stroke.setLineJoin(filterFactory.literal(WellKnownNames.LINE_ROUND));
			break;

		case BISEL:
			stroke.setLineJoin(filterFactory.literal(WellKnownNames.LINE_BEVEL));
			break;
		}

		return stroke;
	}

	public void addSymbolizer(PolygonStyleVisual polygonVisual) {
		Fill polygonFill = this.createFill(polygonVisual);
		Stroke polygonStroke = this.createStroke(polygonVisual.getContour());

		PolygonSymbolizer sym = styleFactory.createPolygonSymbolizer(polygonStroke, polygonFill, null);

		this.symbolizers.add(sym);
	}

	private Fill createFill(PolygonStyleVisual polygonVisual) {
		Color areaColor = polygonVisual.getAreaColor();
		double areaHeight = polygonVisual.getAreaHeight();
		AreaStyleEnum areaStyle = polygonVisual.getAreaStyle();
		double areaTransparency = (100.0 - polygonVisual.getAreaTransparency()) / 100.0;
		Image image = polygonVisual.getImage();

		Fill fill = styleFactory.createFill(filterFactory.literal(areaColor), filterFactory.literal(areaTransparency));

		Mark mark = null;

		switch (areaStyle) {
		case SOLIDO:
			break;

		case TRANSPARENTE:
			fill.setOpacity(filterFactory.literal(0.0));
			break;

		case VERTICAL:
			mark = styleFactory.createMark();
			mark.setWellKnownName(filterFactory.literal(WellKnownNames.SHAPE_VERT_LINE));
			break;

		case HORIZONTAL:
			mark = styleFactory.createMark();
			mark.setWellKnownName(filterFactory.literal(WellKnownNames.SHAPE_HOR_LINE));
			break;

		case BDIAGONAL:
			mark = styleFactory.createMark();
			mark.setWellKnownName(filterFactory.literal(WellKnownNames.SHAPE_SLASH));
			break;

		case FDIAGONAL:
			mark = styleFactory.createMark();
			mark.setWellKnownName(filterFactory.literal(WellKnownNames.SHAPE_BACKSLASH));
			break;

		case CRUZ:
			mark = styleFactory.createMark();
			mark.setWellKnownName(filterFactory.literal(WellKnownNames.SHAPE_PLUS));
			break;

		case CRUZ_DIAGONAL:
			mark = styleFactory.createMark();
			mark.setWellKnownName(filterFactory.literal(WellKnownNames.SHAPE_TIMES));
			break;
		}

		if (mark == null) {
			return fill;
		} else {
			Stroke stroke = styleFactory.createStroke(fill.getColor(), filterFactory.literal(2));
			mark.setStroke(stroke);

			Graphic graphic = styleFactory.createDefaultGraphic();
			graphic.graphicalSymbols().clear();
			graphic.graphicalSymbols().add(mark);

			Fill externalFill = styleFactory.getDefaultFill();
			externalFill.setGraphicFill(graphic);

			return externalFill;
		}
	}

	public Rule buildRule(String name) {
		return buildRule(name, null, null, null);
	}

	public Rule buildRule(String name, Double minScale, Double maxScale, Filter filter) {

		Rule rule = styleFactory.createRule();

		if (minScale != null && maxScale != null) {
			rule.setMaxScaleDenominator(maxScale);
			rule.setMinScaleDenominator(minScale);
		}

		if (filter != null) {
			rule.setFilter(filter);
		}

		for (Symbolizer sym : symbolizers) {
			rule.symbolizers().add(sym);
		}

		this.clearSymbolizers();

		return rule;
	}

	public FeatureTypeStyle buildFeatureTypeStyle(String name) {
		Rule rule = this.buildRule(null);

		Rule[] rules = new Rule[] { rule };

		return buildFeatureTypeStyle(rules, name);
	}

	public FeatureTypeStyle buildFeatureTypeStyle(List<Rule> rules, String name) {
		return this.buildFeatureTypeStyle(rules.toArray(new Rule[0]), name);
	}

	public FeatureTypeStyle buildFeatureTypeStyle(Rule[] rules, String name) {
		FeatureTypeStyle typeStyle = styleFactory.createFeatureTypeStyle(rules);

		if (name != null && !name.equals("")) {
			typeStyle.setName(name);
		}

		return typeStyle;
	}

	public Style buildStyle(String name) {
		FeatureTypeStyle typeStyle = this.buildFeatureTypeStyle(null);

		return this.buildStyle(new FeatureTypeStyle[] { typeStyle }, name);
	}

	public Style buildStyle(List<FeatureTypeStyle> typeStyles, String name) {
		return this.buildStyle(typeStyles.toArray(new FeatureTypeStyle[0]), name);
	}

	public Style buildStyle(FeatureTypeStyle[] typeStyles, String name) {
		Style style = styleFactory.createStyle();

		if (name != null && !name.equals("")) {
			style.setName(name);
		}

		style.featureTypeStyles().clear();

		for (FeatureTypeStyle typeStyle : typeStyles) {
			style.featureTypeStyles().add(typeStyle);
		}

		return style;
	}

	public void clearSymbolizers() {
		this.symbolizers.clear();
	}
}
