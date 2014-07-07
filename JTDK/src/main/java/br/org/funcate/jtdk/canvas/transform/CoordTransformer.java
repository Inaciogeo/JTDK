package br.org.funcate.jtdk.canvas.transform;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;

/**
 * This interface provides a coordinate transformation service between Window (World) coordinate system and Widget (Viewport) coordinate system.
 * 
 * This interface has methods that transform a coordinate from
 * window (world) system to a value in widget coordinate system and
 * in the opposite way.
 * 
 * @author Moraes, Emerson Leite
 *
 */
public interface CoordTransformer {
	
	/**
	 * Returns the world (window) coordinates area (supposing a cartesian reference system).
	 * 
	 * @return {@link Envelope} with world coordinates.
	 */
	public Envelope getWindow();
	
	/**
	 * Returns the widget (Viewport) coordinates area (supposing a cartesian reference system).
	 * 
	 * @return {@link Envelope} with widget coordinates.
	 */
	public Envelope getViewport();
	/**
	 * Adjusts world (or window) coordinates area (supposing a cartesian reference system).
	 * @param x1 Lower left abscissa.
	 * @param y1 Lower left ordinate.
	 * @param x2 Upper left abscissa.
	 * @param y2 Upper left ordinate.
	 */
	public void setWindow (double x1, double y1, double x2, double y2);
	
	/**
	 * Adjusts world (or window) coordinates area (supposing a cartesian reference system).
	 * @param box an {@link Envelope} with coordinates.
	 */
	public void setWindow (Envelope box);
	
	/**
	 * Adjusts widget coordinates area (supposing a cartesian reference system).
	 * @param x1 Lower left abscissa.
	 * @param y1 Lower left ordinate.
	 * @param x2 Upper left abscissa.
	 * @param y2 Upper left ordinate.
	 */
	public void setViewport (double x1, double y1, double x2, double y2);
	
	/**
	 * Adjusts widget coordinates area (supposing a cartesian reference system).
	 * @param box an {@link Envelope} with coordinates.
	 */
	public void setViewport (Envelope box);
	
	/**
	 * Transform world (or Window) coordinates to widget (or Viewport) coordinates.
	 * @param x World (or Window) abscissa.
	 * @param y World (or Window) ordinate.
	 * @return double[] with Viewport coordinates x on [0] and y on [1]. 
	 */
	public double[] window2Viewport (double x, double y);
	
	/**
	 * Transform world (or Window) coordinates to widget (or Viewport) coordinates.
	 * @param coord World coordinate.
	 * @return a {@link Coordinate} with Viewport x and y.
	 */
	public Coordinate window2Viewport (Coordinate coord);
	
	/**
	 * Transform world (or Window) box to widget (or Viewport) box.
	 * @param worldBox World box {@link Envelope}
	 * @return an {@link Envelope} with Viewport coordinates.
	 */
	public Envelope window2Viewport (Envelope worldBox);
	
	/**
	 * Transform widget (or Viewport) coordinates to world (or Window) coordinates.
	 * @param x Widget (or Viewport) abscissa.
	 * @param y Widget (or Viewport) ordinate.
	 * @return double[] with world coordinates x on [0] and y on [1]. 
	 */
	public double[] viewport2Window(double x, double y);
	
	/**
	 * Transform widget (or Viewport) coordinates to world (or Window) coordinates.
	 * @param coord Viewport coordinate.
	 * @return a {@link Coordinate} with World x and y.
	 */
	public Coordinate viewport2Window (Coordinate coord);
	
}
