package br.org.funcate.jtdk.canvas;

import java.awt.Color;

import br.org.funcate.glue.view.AbstractCanvas;
import br.org.funcate.jtdk.canvas.transform.CoordTransformer;
import br.org.funcate.jtdk.canvas.transform.CoordTransformerImpl;
import br.org.funcate.jtdk.style.WellKnownNames;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

@SuppressWarnings("serial")
public abstract class GeometryCanvas extends AbstractCanvas{

	/**
	 * This provides coordinate transformation of world / widget coordinates.
	 */
	protected CoordTransformer coordTransformer = new CoordTransformerImpl();
	
	/**
	 * Draw a {@link Point} with world coordinate on Canvas.
	 * @param point with World coordinate.
	 */
	public abstract void drawPointW (Point point);
	
	/**
	 * Sets the color of {@link Point} geometries.
	 * @param color {@link Color} of points.
	 */
	public void setPointColor (Color color){
		if (color == null){
			return;
		}
		
		this.setPointColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}
	/**
	 * Sets the color of {@link Point} geometries.
	 * @param r Red component color value.
	 * @param g Green component color value.
	 * @param b Blue component color value.
	 */
	public abstract void setPointColor (int r, int g, int b);
	
	/**
	 * Sets the color of {@link Point} geometries.
	 * @param r Red component color value.
	 * @param g Green component color value.
	 * @param b Blue component color value.
	 * @param alpha component opacity value. 0 - 255, 255 is opaque and 0 is transparent.
	 */
	public abstract void setPointColor (int r, int g, int b, int alpha);
	
	/**
	 * Sets width of contour of {@link Point}.
	 * @param width
	 */
	public abstract void setPointContourWidth(int width);
	
	/**
	 * Sets the color of {@link Point} contour.
	 * @param color
	 */
	public void setPointContourColor (Color color){
		if (color == null){
			return;
		}
		
		this.setPointContourColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}
	
	/**
	 * Sets the color of {@link Point} contour.
	 * @param r Red component color value.
	 * @param g Green component color value.
	 * @param b Blue component color value.
	 */
	public abstract void setPointContourColor (int r, int g, int b);
	
	/**
	 * Sets the color of {@link Point} contour.
	 * @param r Red component color value.
	 * @param g Green component color value.
	 * @param b Blue component color value.
	 * @param alpha component opacity value. 0 - 255, 255 is opaque and 0 is transparent.
	 */
	public abstract void setPointContourColor (int r, int g, int b, int alpha);

	/**
	 * Sets {@link Point} size.
	 * @param size
	 */
	public abstract void setPointSize(int size);
	
	/**
	 * Sets {@link Point} wellKnownName, this defines the style of points (example circle, rect, etc.).
	 * @see WellKnownNames
	 * @param wellKnownName
	 */
	public abstract void setPointWellKnownName(String wellKnownName);
	
	/**
	 * Sets {@link Point} size.
	 * @param rotation
	 */
	public abstract void setPointRotation(int rotation);
	
	//public abstract void 
	/**
	 * Draw a {@link MultiPoint} with world coordinates on Canvas.
	 * @param multiPoint with World coordinates.
	 */
	public void drawMultiPointW (MultiPoint multiPoint){
		
		if (multiPoint.isEmpty()){
			return;
		}
		
		for (int i = 0; i < multiPoint.getNumGeometries(); i++){
			Point point = (Point) multiPoint.getGeometryN(i);
			this.drawPointW(point);
		}
	}
	
	/**
	 * Draw a {@link LineString} with world coordinates on Canvas.
	 * @param line with World coordinates.
	 */
	public abstract void drawLineStringW(LineString line);
	
	/**
	 * Sets {@link LineString} color.
	 * @param color Color
	 */
	public  void setLineColor(Color color){
		if (color == null){
			return;
		}
		
		this.setLineColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}
	
	/**
	 * Sets {@link LineString} color.
	 * @param r Red component color value.
	 * @param g Green component color value.
	 * @param b Blue component color value.
	 * @param alpha component opacity value. 0 - 255, 255 is opaque and 0 is transparent.
	 */
	public abstract void setLineColor(int r, int g, int b, int alpha);
	
	/**
	 * Sets {@link LineString} width.
	 * @param width to be set.
	 */
	public abstract void setLineWidth(int width);
	
	/**
	 * Sets {@link LineString} border color.
	 * @param r Red component color value.
	 * @param g Green component color value.
	 * @param b Blue component color value.
	 * @param alpha component opacity value. 0 - 255, 255 is opaque and 0 is transparent.
	 */
	public abstract void setLineBorderColor(int r, int g, int b, int alpha);
	
	/**
	 * Sets {@link LineString} border color.
	 * @param color Color
	 */
	public void setLineBorderColor(Color color){
		if (color == null){
			return;
		}
		
		this.setLineBorderColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}
	
	/**
	 * Sets {@link LineString} border
	 * @param width to be set.
	 */
	public abstract void setLineBorderWidth(int width);
	
	/**
	 * Draw a {@link MultiLineString} with world coordinates on Canvas.
	 * @param multiLine with World coordinates.
	 */
	public void drawMultiLineStringW(MultiLineString multiLine){
		
		if (multiLine.isEmpty()){
			return;
		}
		
		for (int i = 0; i < multiLine.getNumGeometries(); i++){
			LineString line = (LineString) multiLine.getGeometryN(i);
			this.drawLineStringW(line);
		}
	}
	
	/**
	 * Draw a {@link Polygon} with world coordinates on Canvas.
	 * @param polygon with World coordinates.
	 */
	public abstract void drawPolygonW(Polygon polygon);
	
	/**
	 * Draw a {@link MultiPolygon} with world coordinates on Canvas.
	 * @param multiPolygon with World coordinates.
	 */
	public void drawMultiPolygonW(MultiPolygon multiPolygon){
		
		if (multiPolygon.isEmpty()){
			return;
		}
		
		for (int i = 0; i < multiPolygon.getNumGeometries(); i++){
			Polygon polygon = (Polygon) multiPolygon.getGeometryN(i);
			this.drawPolygonW(polygon);
		}
	}
	
	/**
	 * Draw a {@link Geometry} with world coordinates on Canvas.
	 * @param geometry with World coordinates.
	 */
	public void drawGeometryW(Geometry geometry){
		
		if (geometry instanceof Point){
			Point point = (Point) geometry;
			this.drawPointW(point);
		}
		
		if (geometry instanceof LineString){
			LineString line = (LineString) geometry;
			this.drawLineStringW(line);
		}
		
		if (geometry instanceof Polygon){
			Polygon polygon = (Polygon) geometry;
			this.drawPolygonW(polygon);
		}
		
		if (geometry instanceof GeometryCollection){
			GeometryCollection collection = (GeometryCollection) geometry;
			this.drawGeometryCollection(collection);
		}
	}
	
	/**
	 * Draw a {@link GeometryCollection} with world coordinates on Canvas.
	 * @param collection with World coordinates.
	 */
	public void drawGeometryCollection (GeometryCollection collection){
		
		if (collection == null || collection.isEmpty()){
			return;
		}
		
		if (collection instanceof MultiPoint){
			MultiPoint multiPoint = (MultiPoint) collection;
			this.drawMultiPointW(multiPoint);
			return;
		}
		
		if (collection instanceof MultiLineString){
			MultiLineString multiLine = (MultiLineString) collection;
			this.drawMultiLineStringW(multiLine);
			return;
		}
		
		if (collection instanceof MultiPolygon){
			MultiPolygon multiPolygon = (MultiPolygon) collection;
			this.drawMultiPolygonW(multiPolygon);
			return;
		}
		
		for (int i = 0; i < collection.getNumGeometries(); i++){
			this.drawGeometryW(collection.getGeometryN(i));
		}
	} 
	
	/**
	 * Adjusts world (or window) coordinates area (supposing a cartesian reference system).
	 * @param x1 Lower left abscissa.
	 * @param y1 Lower left ordinate.
	 * @param x2 Upper left abscissa.
	 * @param y2 Upper left ordinate.
	 */
	public void setWindow(double x1, double y1, double x2, double y2){
		coordTransformer.setWindow(x1, y1, x2, y2);
	}
	
	/**
	 * Returns the current box of {@link AbstractCanvas}.
	 * @return Envelope
	 */
	public Envelope getWindow(){
		return coordTransformer.getWindow();
	}
}
