package br.org.funcate.jtdk.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.geojson.feature.FeatureJSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.opengis.feature.simple.SimpleFeature;

import br.org.funcate.glue.model.Attribute;
import br.org.funcate.glue.model.AttributeTable;
import br.org.funcate.glue.model.Layer;
import br.org.funcate.glue.model.Representation;
import br.org.funcate.glue.model.Theme;
import br.org.funcate.jtdk.model.dto.FeatureDTO;

public class FeatureTransformer {

	public List<String> featuresToGeoJSON(List<FeatureDTO> features) {
		List<String> list = new ArrayList<String>();
		for (FeatureDTO feature : features) {
			list.add(feature.toGeoJSON());
		}
		return list;
	}

	public String getAttributeListAsString(Theme theme) {

		Layer layer = theme.getLayer();
		List<AttributeTable> attributeTables = layer.getAttributeTables();

		// Creates a string with all layer's attributes
		String attributes = "";
		for (AttributeTable attributeTable : attributeTables) {
			List<Attribute> attributeList = attributeTable.getAttributes();

			for (Attribute attribute : attributeList) {
				if (attributes.isEmpty() == false) {
					attributes += ",";
				}
				if (attribute.getDataType().equals("byte[]")) {
					attributes += "*" + attribute.getName() + ":" + "Geometry";
				} else {
					attributes += attribute.toString();
				}
			}

		}

		// Adds attribute 'selected' to the list
		attributes += ",selected:Boolean";

		return attributes;
	}

	public SimpleFeature geoJSONToFeature(Theme theme, String geoJSON) {
		System.out.println(geoJSON);
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject obj = (JSONObject) parser.parse(geoJSON);
			JSONArray faeturesArray = (JSONArray) obj.get("features");
			for (int i = 0; i < faeturesArray.size(); i++) {
				JSONObject featureObj = (JSONObject) faeturesArray.get(i);
				JSONObject properties = (JSONObject) featureObj.get("properties");
				if(properties.containsKey("selected") == false){
					properties.put("selected", false);
				}
			}
			//geoJSON = obj.toJSONString();
		} catch (ParseException e1) {
			e1.printStackTrace();
			return null;
		}
		
		System.out.println(geoJSON);
		
		SimpleFeature feature = null;
		try {
			if (geoJSON.isEmpty() == false) {
				FeatureJSON featureJSON = new FeatureJSON();
				SimpleFeatureCollection featureCollection = (SimpleFeatureCollection) featureJSON
						.readFeatureCollection(geoJSON);
				SimpleFeatureIterator iterator = featureCollection.features();
				if (iterator.hasNext()) {
					feature = iterator.next();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return feature;
		
	}
	
	// Gets representation type
	String getRepresentationAsString(List<Representation> representationList) {

		String repType = "FeatureCollection";
		if (representationList.size() == 1) {
			Representation representation = representationList.get(0);
			if (representation.getId() == Representation.LINE) {
				repType = "LineString";
			} else if (representation.getId() == Representation.POINT) {
				repType = "Point";
			} else if (representation.getId() == Representation.POLYGON) {
				repType = "Polygon";
			}
		}
		return repType;
	}

}
