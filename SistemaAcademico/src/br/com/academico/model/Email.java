package br.com.academico.model;

public class Email {
	private String enderecoEmail;
	private int fkPessoa;

	
	//Construtor vazio para instanciar objeto sem parâmetros
	public Email() {
		
	}

	//Getters and Setters
	public String getEnderecoEmail() {
		return enderecoEmail;
	}


	public void setEnderecoEmail(String enderecoEmail) {
		this.enderecoEmail = enderecoEmail;
	}


	public int getFkPessoa() {
		return fkPessoa;
	}


	public void setFkPessoa(int fkPessoa) {
		this.fkPessoa = fkPessoa;
	}


}
