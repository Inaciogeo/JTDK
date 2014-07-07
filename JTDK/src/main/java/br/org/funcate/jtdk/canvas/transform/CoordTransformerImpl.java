package br.org.funcate.jtdk.canvas.transform;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.canvas.CanvasState;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;

/**
 * This class implements a coordinate transformation service between Window (World) coordinate system and Widget (Viewport) coordinate system.
 * 
 * This class has methods that transform a coordinate from
 * window (world) system to a value in widget coordinate system and
 * in the opposite way.
 * 
 * @author Moraes, Emerson Leite.
 *
 */
public class CoordTransformerImpl implements CoordTransformer{

	// World coordinates
	/**
	 * Window (World) x1.
	 */
	private double x1Window;
	
	/**
	 * Window (World) y1.
	 */
	private double y1Window;
	
	/**
	 * Window (World) x2.
	 */
	private double x2Window;

	/**
	 * Window (World) y2.
	 */
	private double y2Window;

	// widget coordinates
	/**
	 * Viewport (Widget) x1.
	 */
	private double x1Viewport;
	
	/**
	 * Viewport (Widget) y1.
	 */
	private double y1Viewport;
	
	/**
	 * Viewport (Widget) x2.
	 */
	private double x2Viewport;
	
	/**
	 * Viewport (Widget) y2.
	 */
	private double y2Viewport;
	
	/**
	 * Viewport width
	 */
	//private double viewportWidth;
	
	/**
	 * Viewport height
	 */
	private double viewportHeight;
	
	/**
	 * Canvas resolution.
	 */
	private double resolution;

	/**
	 * Gets the current window (with world coordinates).
	 * @return Envelope
	 */
	public Envelope getWindow() {
		this.updateBox();
		Envelope box = new Envelope(x1Window, x2Window, y1Window, y2Window);
		return box;
	}

	
	public Envelope getViewport() {
		Envelope box = new Envelope(x1Viewport, x2Viewport, y1Viewport, y2Viewport);
		return box;
	}


	public void setWindow(double x1, double y1, double x2, double y2) {
		this.x1Window = x1;
		this.y1Window = y1;
		this.x2Window = x2;
		this.y2Window = y2;
	}


	public void setWindow(Envelope box) {
		this.setWindow(box.getMinX(), box.getMinY(), box.getMaxX(), box.getMaxY());
	}


	public void setViewport(double x1, double y1, double x2, double y2) {
		this.x1Viewport = x1;
		this.y1Viewport = y1;
		this.x2Viewport = x2;
		this.y2Viewport = y2;
	}


	public void setViewport(Envelope box) {
		this.setViewport(box.getMinX(), box.getMinY(), box.getMaxX(), box.getMaxY());
	}


	public double[] window2Viewport(double x, double y) {
		
		double boxX1WorldPosition = x1Window;
		double boxY1WorldPosition = y1Window;
		
		
		double x1Increment = x - boxX1WorldPosition;
		double y1Increment = y - boxY1WorldPosition;
		
		double xFinalPosition = (x1Increment / resolution);
		double yFinalPosition = viewportHeight - (y1Increment / resolution);
		
		double [] positionInPixel = {xFinalPosition, yFinalPosition};
		
		return positionInPixel;
	}


	public Coordinate window2Viewport(Coordinate coord) {
		double [] pixelCoords = this.window2Viewport(coord.x, coord.y);
		Coordinate pixelCoord = new Coordinate(pixelCoords[0], pixelCoords[1]);
		return pixelCoord;
	}


	public Envelope window2Viewport(Envelope worldBox) {
		return null;
	}


	public double[] viewport2Window(double x, double y) {
		
		double boxX1WorldPosition = x1Window;
		double boxY1WorldPosition = y1Window;
		
		
		double x1IncrementToWorld = x * resolution;
		double y1IncrementToWorld = (viewportHeight - y) * resolution;
		
		double xFinalPosition = boxX1WorldPosition + x1IncrementToWorld;
		double yFinalPosition = boxY1WorldPosition + y1IncrementToWorld;
		
		double [] positionInWorld = {xFinalPosition, yFinalPosition};
		
		return positionInWorld;
	}


	private void updateBox(){
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		Box box = state.getBox();
		
		this.setWindow(box.getX1(), box.getY1(), box.getX2(), box.getY2());
	}
	
	public Coordinate viewport2Window(Coordinate coord) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
