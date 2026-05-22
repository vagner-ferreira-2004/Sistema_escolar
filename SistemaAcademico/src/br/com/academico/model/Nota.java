package br.com.academico.model;

public class Nota {

    private String fkRgm;
    private int fkSemestre;
    private int fkDisciplina;
    private double valorNota;

    
    // Construtor vazio
    public Nota() {

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

    public double getValorNota() {
        return valorNota;
    }

    public void setValorNota(double valorNota) {
        this.valorNota = valorNota;
    }

}