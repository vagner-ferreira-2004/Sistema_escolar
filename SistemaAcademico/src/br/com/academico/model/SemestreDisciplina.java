package br.com.academico.model;

public class SemestreDisciplina {
	private int fkSemestre;
	private int fkDisciplina;
	
	
	public SemestreDisciplina() {
	}


	public int getFkSemestre() {
		return fkSemestre;
	}


	public void setFkSemestre(int fkSemestre) {
		this.fkSemestre = fkSemestre;
	}


	public int getFkDisciplina() {
		return fkDisciplina;
	}


	public void setFkDisciplina(int fkDisciplina) {
		this.fkDisciplina = fkDisciplina;
	}
	
	
}
