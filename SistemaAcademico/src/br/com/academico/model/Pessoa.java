package br.com.academico.model;

public class Pessoa {
	private int pkPessoa;
	private int fkEndereco;
	private String cpfPessoa;
	private String nomePessoa;
	private String dataNascimento;
	
	public Pessoa() { //Construtor vazio para instanciar objeto sem parâmetros
		
	}

	public int getPkPessoa() {
		return pkPessoa;
	}

	public void setPkPessoa(int pkPessoa) {
		this.pkPessoa = pkPessoa;
	}

	public int getFkEndereco() {
		return fkEndereco;
	}

	public void setFkEndereco(int fkEndereco) {
		this.fkEndereco = fkEndereco;
	}

	public String getCpfPessoa() {
		return cpfPessoa;
	}

	public void setCpfPessoa(String cpfPessoa) {
		this.cpfPessoa = cpfPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
}
