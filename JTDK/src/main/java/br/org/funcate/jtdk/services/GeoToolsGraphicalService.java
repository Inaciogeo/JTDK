package br.org.funcate.jtdk.services;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureLayer;
import org.geotools.map.MapContent;
import org.geotools.renderer.GTRenderer;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.Style;
import org.opengis.feature.simple.SimpleFeature;

import br.org.funcate.glue.model.canvas.BufferEnum;
import br.org.funcate.glue.model.canvas.CanvasGraphicsBuffer;
import br.org.funcate.jtdk.canvas.GeometryCanvas;

import com.vividsolutions.jts.geom.Envelope;

/**
 * This class is a service to draw GeoTools / SFS objects like
 * {@link SimpleFeatureCollection} and {@link SimpleFeature} on GLUE's buffer
 * with SLDs.
 * 
 * @author Moraes, Emerson Leite.
 * 
 */
public class GeoToolsGraphicalService {

	/**
	 * This is a Renderer of GeoTools library to draw Layers into Java a
	 * Graphics2D.
	 */
	private GTRenderer renderer;

	public GeoToolsGraphicalService() {
		this.renderer = new StreamingRenderer();
	}

	/**
	 * This method draws a {@link SimpleFeatureCollection} with SLD on a GLUE's
	 * buffer.
	 * 
	 * @param featureCollection
	 * @param style
	 */
	public void drawFeatureCollection(GeometryCanvas canvas, SimpleFeatureCollection featureCollection, Style style, BufferEnum bufferId) {
		BufferedImage editionBuffer = canvas.getBuffer(bufferId);

		FeatureLayer layer = new FeatureLayer(featureCollection, style);

		MapContent map = new MapContent();

		map.addLayer(layer);

		Graphics2D gr = editionBuffer.createGraphics();

		Envelope env = canvas.getWindow();

		Rectangle imageBounds = new Rectangle(0, 0, editionBuffer.getWidth(), editionBuffer.getHeight());

		ReferencedEnvelope mapBounds = new ReferencedEnvelope(env, null);

		this.renderer.setMapContent(map);

		this.renderer.paint(gr, imageBounds, mapBounds);

		CanvasGraphicsBuffer bufferManager = canvas.getBufferManager();

		bufferManager.setEditionX(0);
		bufferManager.setEditionY(0);

		gr.dispose();

		map.dispose();
		
	}
}
