package br.org.funcate.jtdk.style.teste;

public enum TesteEnum {

	VALOR_1("valor1"), VALOR_2("valor2");
	
	private String name;
	
	private TesteEnum(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
	public static void main(String[] args) {
		System.out.println(TesteEnum.VALOR_1);
		System.out.println(TesteEnum.VALOR_2);
	}
}
