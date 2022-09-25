package br.com.precatorio.domain;

public enum EnumEstadoCivil {
	
	SOLTEIRO("Casado"), CASADO("Solteiro");
	
	private String valor;

	EnumEstadoCivil(String value) {
		this.valor = value;
	}


	public String valor() {
		return this.valor;
	}
}
