package br.com.academico.teste;

import br.com.academico.dao.CursoDAO;
import br.com.academico.model.Curso;

public class TesteCurso {

    public static void main(String[] args) {

        try {

            Curso curso = new Curso();

            curso.setNomeCurso("Ciência da Computação");
            curso.setPeriodoCurso("NOTURNO");
            curso.setTipoCurso("Presencial");

            CursoDAO dao = new CursoDAO();

            dao.salvar(curso);

            System.out.println("Curso salvo com sucesso!");

        } catch (Exception e) {

            System.out.println("Erro: " + e.getLocalizedMessage());
        }
    }
}