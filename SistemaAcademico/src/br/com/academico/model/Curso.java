package br.com.academico.model;

public class Curso {
	private int pkCurso;
	private String nomeCurso;
	private String periodoCurso;
	private String tipoCurso;
	
	
	//Construtor vazio para instanciar objeto sem parâmetros
	public Curso() {
		
	}

	//Getters and Setters
	public int getPkCurso() {
		return pkCurso;
	}


	public void setPkCurso(int pkCurso) {
		this.pkCurso = pkCurso;
	}


	public String getNomeCurso() {
		return nomeCurso;
	}


	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}


	public String getPeriodoCurso() {
		return periodoCurso;
	}


	public void setPeriodoCurso(String periodoCurso) {
		this.periodoCurso = periodoCurso;
	}


	public String getTipoCurso() {
		return tipoCurso;
	}


	public void setTipoCurso(String tipoCurso) {
		this.tipoCurso = tipoCurso;
	}

	
}
