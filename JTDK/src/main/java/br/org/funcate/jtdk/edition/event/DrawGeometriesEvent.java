package br.org.funcate.jtdk.edition.event;

import java.util.EventObject;
import java.util.List;

import com.vividsolutions.jts.geom.Geometry;

public class DrawGeometriesEvent extends EventObject{

	private static final long serialVersionUID = 43934599655330097L;
	
	private List<Geometry> geometries;
	
	public DrawGeometriesEvent(Object source, List<Geometry> geometries) {
		super(source);
		this.setGeometries(geometries);
	}

	public List<Geometry> getGeometries() {
		return geometries;
	}

	public void setGeometries(List<Geometry> geometries) {
		this.geometries = geometries;
	}

}
