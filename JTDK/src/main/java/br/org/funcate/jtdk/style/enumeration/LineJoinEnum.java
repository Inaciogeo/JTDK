package br.org.funcate.jtdk.style.enumeration;

/**
 * This Enumeration represents the Line Join types.
 * 
 * @author Moraes, Emerson Leite
 *
 */
public enum LineJoinEnum {

	/**
	 * Line Join types.
	 */
	MILTRA("Miltra"), BISEL("Bisel"), ARREDONDADO("Arrendondado");
	
	/**
	 * Name of line join types.
	 */
	private String name;
	
	/**
	 * Constructor.
	 * @param name
	 */
	private LineJoinEnum(String name){
		this.name = name;
	}
	
	/**
	 * The toString override. This return the name of {@link LineJoinEnum}.
	 */
	public String toString(){
		return name;
	}
}
