package br.com.precatorio.domain;

public enum EnumEstadoCivil {

	SOLTEIRO("SOLTEIRO"), CASADO("CASADO");

	private String valor;

	EnumEstadoCivil(String value) {
		this.valor = value;
	}


	public String getValor() {
		return this.valor;
	}
}
