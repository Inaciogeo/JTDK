package br.org.funcate.jtdk.style.enumeration;

/**
 * This Enumeration represents the Line Final types.
 * 
 * @author Moraes, Emerson Leite
 *
 */
public enum LineFinalEnum {

	/**
	 * Line Final types.
	 */
	PADRAO("Padrão"), QUADRADO("Quadrado"), ARREDONDADO("Arredondado");
	
	/**
	 * Name of line final types.
	 */
	private String name;
	
	/**
	 * Constructor.
	 * @param name
	 */
	private LineFinalEnum(String name){
		this.name = name;
	}
	        
	/**
	 * The toString override. This return the name of {@link LineFinalEnum}.
	 */
	public String toString(){
		return name;
	}
}
