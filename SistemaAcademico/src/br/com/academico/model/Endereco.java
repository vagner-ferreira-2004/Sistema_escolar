package br.com.academico.model;

public class Endereco {
	private int pkEndereco;
	private String logradouro;
	private String cep;
	private String numeroEndereco;
	private String complemento;
	private String municipio;
	private String uf;
	
	
	public Endereco() {//Construtor vazio para instanciar objeto sem parâmetros
		
	}
	
	

	//Getters and Setters
	public int getPkEnderecos() {
		return pkEndereco;
	}


	public void setPkEnderecos(int pkEnderecos) {
		this.pkEndereco = pkEnderecos;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getNumeroEndereco() {
		return numeroEndereco;
	}


	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getMunicipio() {
		return municipio;
	}


	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}


	public String getUf() {
		return uf;
	}


	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
	
}


