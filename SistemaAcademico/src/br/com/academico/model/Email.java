package br.com.academico.model;

public class Email {
	private String enderecoEmail;
	private int fkPessoa;
	private String tipoEmail; // Campo atualmente utilizando DEFAULT no banco de dados,
    						  // porém mantido na model pensando em futuras implementações
    						  // como emails institucionais, acadêmicos ou comerciais.
	
	
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


	public String getTipoEmail() {
		return tipoEmail;
	}


	public void setTipoEmail(String tipoEmail) {
		this.tipoEmail = tipoEmail;
	}
	
}
