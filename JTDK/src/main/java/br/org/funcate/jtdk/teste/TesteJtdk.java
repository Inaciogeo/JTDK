package br.org.funcate.jtdk.teste;

import java.io.IOException;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.simple.SimpleFeature;

import br.org.funcate.jtdk.model.dto.FeatureDTO;

import com.vividsolutions.jts.geom.Geometry;

@SuppressWarnings("serial")
public class TesteJtdk {

	public static void main(String[] args) {
		try {
			//String geoJSON = "{\"type\":\"FeatureCollection\",\"features\": [{\"type\":\"Feature\",\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-45.2796900000,-23.8515620000],[-45.2775300000,-23.8518740000],[-45.2775630000,-23.8531750000],[-45.2779700000,-23.8535570000],[-45.2795210000,-23.8540760000],[-45.2796900000,-23.8515620000]]]},\"properties\":{\"object_id\":\"106\",\"geometry_id\":106,\"linkcolumn\":\"194\",\"regiao\":\"Sudeste\",\"object_id_9\":\"106\"}}]}";
			String geoJSON = "{ \"type\": \"FeatureCollection\",\"features\": [{ \"type\": \"Feature\",\"geometry\": {\"type\": \"LineString\",\"coordinates\": [[102.0, 0.0], [103.0, 1.0], [104.0, 0.0], [105.0, 1.0]]},\"properties\": {\"prop0\": \"value0\",\"prop1\": 0.0} }] }";
			FeatureJSON featureJSON = new FeatureJSON();
			SimpleFeatureCollection featureCollection = (SimpleFeatureCollection) featureJSON
					.readFeatureCollection(geoJSON);
			SimpleFeatureIterator iterator = featureCollection.features();
			while (iterator.hasNext()) {
				SimpleFeature feature = iterator.next();
				FeatureDTO featureDTO = new FeatureDTO(feature);
				Geometry geometry = (Geometry) featureDTO.getSimpleFeature().getDefaultGeometry();
			}

			System.out.println(featureCollection.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
