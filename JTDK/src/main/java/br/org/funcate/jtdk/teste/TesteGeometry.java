package br.org.funcate.jtdk.teste;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;

public class TesteGeometry {

	public static void main(String[] args) {
		GeometryFactory factory = new GeometryFactory();
		
		Point p = factory.createPoint(new Coordinate(1, 1));
		
		Coordinate [] coordinates = new Coordinate[]{new Coordinate(0,0), new Coordinate(1, 1), new Coordinate(0, 0)};
		
		LineString line = factory.createLineString(coordinates);
		
		GeometryCollection c = factory.createGeometryCollection(null);
		
		Geometry [] geoms = new Geometry[]{p, line, c};
		
		
		GeometryCollection collection = factory.createGeometryCollection(geoms);

		System.out.println(collection);
	}
}
