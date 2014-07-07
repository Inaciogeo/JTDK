package br.org.funcate.jtdk.model.dto;

import java.io.IOException;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.FeatureCollections;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.simple.SimpleFeature;

/**
 * Feature DTO object
 * 
 * @author Severino
 * 
 */
public class FeatureDTO {
	SimpleFeature simpleFeature;
	
	public FeatureDTO(String geoJSON) {
		try {
			
			if(geoJSON.isEmpty() == false){
				FeatureJSON featureJSON = new FeatureJSON();
				SimpleFeatureCollection featureCollection = (SimpleFeatureCollection) featureJSON.readFeatureCollection(geoJSON);
				SimpleFeatureIterator iterator = featureCollection.features();
				if(iterator.hasNext()) {
					simpleFeature = iterator.next();
				}
			}
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FeatureDTO(SimpleFeature feature) {
		this.simpleFeature = feature;
	}

	public String toGeoJSON() {
		try {
			FeatureJSON featureJSON = new FeatureJSON();
			String geoJSON;
			SimpleFeatureCollection collection = FeatureCollections.newCollection();
			collection.add(simpleFeature);
			geoJSON = featureJSON.toString(collection);

			return geoJSON;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getObjectId() {
		String id = (String) simpleFeature.getID();
		return id;
	}
	
	public void setObjectId(String id) {
		simpleFeature.setAttribute("id", id);
	}


	public SimpleFeature getSimpleFeature() {
		return simpleFeature;
	}

	public void setSimpleFeature(SimpleFeature simpleFeature) {
		this.simpleFeature = simpleFeature;
	}
	
}
