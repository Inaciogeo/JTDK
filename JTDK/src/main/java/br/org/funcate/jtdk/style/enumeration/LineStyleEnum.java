package br.org.funcate.jtdk.style.enumeration;

/**
 * This Enumeration represents the Line style types.
 * 
 * @author Moraes, Emerson Leite.
 *
 */
public enum LineStyleEnum {

	/**
	 * Line types.
	 */
	SOLIDA("Linha S�lida"), TRACEJADA("Linha Tracejada"), PONTILHADA("Linha Pontilhada"),
	TRACO_PONTO("Linha Tra�o-Ponto"), TRACO_PONTO_PONTO("Linha Tra�o-Ponto-Ponto");
	
	/**
	 * Name of line type.
	 */
	private String name;
	
	/**
	 * Constructor.
	 * @param name
	 */
	private LineStyleEnum(String name){
		this.name = name;
	}
	
	/**
	 * The toString override. This return the name of LineStyleEnum.
	 */
	public String toString(){
		return name;
	}
}
