package br.com.academico.model;

public class CampusCurso {

    private int fkCampus;
    private int fkCurso;

    
    // Construtor vazio
    public CampusCurso() {

    }

    
    // GETTERS AND SETTERS

    public int getFkCampus() {
        return fkCampus;
    }

    public void setFkCampus(int fkCampus) {
        this.fkCampus = fkCampus;
    }

    public int getFkCurso() {
        return fkCurso;
    }

    public void setFkCurso(int fkCurso) {
        this.fkCurso = fkCurso;
    }

}