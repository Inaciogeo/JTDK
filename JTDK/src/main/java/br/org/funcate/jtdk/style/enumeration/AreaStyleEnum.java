package br.org.funcate.jtdk.style.enumeration;
/**
 * This Enumeration defines the types of Polygon's area fill.
 * 
 * @author Moraes, Emerson Leite
 *
 */
public enum AreaStyleEnum {

	/**
	 * Fill types.
	 */
	TRANSPARENTE("Transparente"), SOLIDO("Sólido"), HORIZONTAL("Horizontal"), VERTICAL("Vertical"),
	FDIAGONAL("FDiagonal"), BDIAGONAL("BDiagonal"), CRUZ("Cruz"), CRUZ_DIAGONAL("Cruz Diagonal");
	
	/**
	 * Name of Fill type
	 */
	private String name;
	
	/**
	 * Constructor
	 * @param name be set.
	 */
	private AreaStyleEnum(String name){
		this.name = name;
	}
	
	public String toString(){
		return this.name;
	}
}
