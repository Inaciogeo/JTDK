package br.org.funcate.jtdk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.Theme;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.service.TerraJavaClient;
import br.org.funcate.jtdk.model.dto.FeatureDTO;


public class FeatureManipulationService implements FeatureManipulation {

	TerraJavaClient services;

	public FeatureManipulationService() {
		AppSingleton singleton = AppSingleton.getInstance();
		services = singleton.getServices();
	}

	@Override
	public Vector<String> saveFeatures(Theme theme, List<FeatureDTO> features) {
		services.setTheme(theme.getName(), theme.getType());
		
		FeatureTransformer transformer = new FeatureTransformer();
		List<String> geoJSONList = transformer.featuresToGeoJSON(features);
		return services.addFeatures(geoJSONList);
	}

	@Override
	public boolean removeFeatures(List<FeatureDTO> features, int representation) {		
		List<String> listIds = new ArrayList<String>();
		for (FeatureDTO feature : features) {
			listIds.add(feature.getObjectId());		
		}
		boolean result = services.deleteFeatures(listIds);
		return result;
	}

	@Override
	public boolean updateFeatures(Theme theme, List<FeatureDTO> features) {
		
		services.setTheme(theme.getName(), theme.getType());
		
		FeatureTransformer transformer = new FeatureTransformer();
		List<String> geoJSONList = transformer.featuresToGeoJSON(features);
		return services.updateFeatures(geoJSONList);		
	}

	@Override
	public ArrayList<FeatureDTO> getFeatures(Box box, Theme theme) {
		
		ArrayList<FeatureDTO> listFeatureDTO = new ArrayList<FeatureDTO>();
	
		services.setTheme(theme.getName(), theme.getType());
		Vector<String> geometries = services.getFeaturesInBox(box);

		for (String geoJSON : geometries) {
			FeatureDTO feature = new FeatureDTO(geoJSON);
			listFeatureDTO.add(feature);
		}
		
		return listFeatureDTO;

	}

	@Override
	public ArrayList<FeatureDTO> getFeatures(ArrayList<String> listIds,
			Theme theme) {
		ArrayList<FeatureDTO> listFeatureDTO = new ArrayList<FeatureDTO>();
		services.setTheme(theme.getName(), theme.getType());
		Vector<String> geometries;
		try {
			geometries = services.getFeaturesByIds(listIds);
			for (String geoJSON : geometries) {
				FeatureDTO feature = new FeatureDTO(geoJSON);
				listFeatureDTO.add(feature);
			}

		} catch (GlueServerException e) {
			e.printStackTrace();
		}

		return listFeatureDTO;
	}

	@Override
	public ArrayList<FeatureDTO> getFeatures(String restriction, Theme theme) {
		services.setTheme(theme.getName(), theme.getType());
		ArrayList<FeatureDTO> listFeatureDTO = new ArrayList<FeatureDTO>();
		try {
			Vector<String> geometries = services
					.getFeaturesWithRestriction(restriction);
			for (String geoJSON : geometries) {
				FeatureDTO feature = new FeatureDTO(geoJSON);
				listFeatureDTO.add(feature);
			}
		} catch (GlueServerException e) {
			e.printStackTrace();
		}
		return listFeatureDTO;
	}

	@Override
	public boolean removeFeatures(List<String> toDelete, Theme theme) {
		if(!services.setTheme(theme.getName(), theme.getType()))
			return false;
		
		boolean result = services.deleteFeatures(toDelete);
		return result;
	}
}
