package br.org.funcate.jtdk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.geotools.data.simple.SimpleFeatureCollection;

import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.Theme;
import br.org.funcate.jtdk.model.dto.FeatureDTO;

/**
 * This class is responsible to handle the {@link SimpleFeatureCollection}, to commit changes in the database
 * @author Severino, Bruno de Oliveira
 */
public interface FeatureManipulation {
	
	/**
	 * Saves a ArrayList of a DTO Features in database
	 * @param features - {@link FeatureDTO}
	 */
	public Vector<String> saveFeatures(Theme theme, List<FeatureDTO> features);
	
	public boolean removeFeatures(List<FeatureDTO> features, int representation);

	public boolean updateFeatures(Theme theme, List<FeatureDTO> features);
	
	public ArrayList<FeatureDTO> getFeatures(Box box, Theme theme);
	
	public ArrayList<FeatureDTO> getFeatures(ArrayList<String> objectID, Theme theme);
	
	public ArrayList<FeatureDTO> getFeatures(String restriction, Theme theme);

	public boolean removeFeatures(List<String> toDelete, Theme theme);
}
