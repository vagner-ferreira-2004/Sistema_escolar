package br.com.academico.model;

public class Aluno {
	private String pkRgm;
	private int fkPessoa;
	private String tipoAluno;
	
	public Aluno() { //Construtor vazio para instanciar objeto sem parâmetros
		
	}
	

	public String getPkRgm() {
		return pkRgm;
	}

	public void setPkRgm(String pkRgm) {
		this.pkRgm = pkRgm;
	}

	public int getFkPessoa() {
		return fkPessoa;
	}

	public void setFkPessoa(int fkPessoa) {
		this.fkPessoa = fkPessoa;
	}

	public String getTipoAluno() {
		return tipoAluno;
	}

	public void setTipoAluno(String tipo_aluno) {
		this.tipoAluno = tipo_aluno;
	}
	
	
}
