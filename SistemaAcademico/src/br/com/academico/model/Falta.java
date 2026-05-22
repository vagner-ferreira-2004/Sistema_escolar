package br.com.academico.model;

public class Falta {

    private String fkRgm;
    private int fkSemestre;
    private int fkDisciplina;
    private int qtdFalta;

    
    // Construtor vazio
    public Falta() {

    }

    
    // GETTERS AND SETTERS

    public String getFkRgm() {
        return fkRgm;
    }

    public void setFkRgm(String fkRgm) {
        this.fkRgm = fkRgm;
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

    public int getQtdFalta() {
        return qtdFalta;
    }

    public void setQtdFalta(int qtdFalta) {
        this.qtdFalta = qtdFalta;
    }

}