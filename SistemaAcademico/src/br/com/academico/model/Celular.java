package br.com.academico.model;

public class Celular {
	private int fkPessoa;
	private String numeroCelular;
	
	
	public Celular() {
		
	}


	public int getFkPessoa() {
		return fkPessoa;
	}


	public void setFkPessoa(int fkPessoa) {
		this.fkPessoa = fkPessoa;
	}


	public String getNumeroCelular() {
		return numeroCelular;
	}


	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	
	
}
