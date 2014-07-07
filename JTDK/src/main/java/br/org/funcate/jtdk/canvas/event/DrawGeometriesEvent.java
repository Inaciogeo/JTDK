package br.org.funcate.jtdk.canvas.event;

import java.util.EventObject;
import java.util.List;

import br.org.funcate.jtdk.style.PointStyleDefinition;

import com.vividsolutions.jts.geom.Geometry;

@SuppressWarnings("serial")
public class DrawGeometriesEvent extends EventObject{

	private List<Geometry> geometries;
	
	private PointStyleDefinition pointStyle;
	
	public DrawGeometriesEvent(Object source, List<Geometry> geometries, PointStyleDefinition pointStyle) {
		super(source);
		this.setGeometries(geometries);
		this.setPointStyle(pointStyle);
	}

	public List<Geometry> getGeometries() {
		return geometries;
	}

	public void setGeometries(List<Geometry> geometries) {
		this.geometries = geometries;
	}

	public PointStyleDefinition getPointStyle() {
		return pointStyle;
	}

	public void setPointStyle(PointStyleDefinition pointStyle) {
		this.pointStyle = pointStyle;
	}

}
