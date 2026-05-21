package br.com.academico.model;

public class AlunoCurso {
	private String fkAluno;
	private int fkCurso;
	
	
	//Construtor vazio para instanciar objeto sem parâmetros
	public AlunoCurso() {
		
	}

	
	//Getters and Setters
	public String getFkAluno() {
		return fkAluno;
	}


	public void setFkAluno(String fkAluno) {
		this.fkAluno = fkAluno;
	}


	public int getFkCurso() {
		return fkCurso;
	}


	public void setFkCurso(int fkCurso) {
		this.fkCurso = fkCurso;
	}


}
	
	
	
