package br.com.academico.teste;

import br.com.academico.dao.CursoDAO;
import br.com.academico.model.Curso;

public class TestaAlterar {

    public static void main(String[] args) {

        try {

            // =========================
            // CURSO
            // =========================

            // Cria objeto
            Curso curso = new Curso();

            // ID do curso que será alterado
            curso.setPkCurso(1);

            // Novos dados completos
            curso.setNomeCurso("Análise e Desenvolvimento de Sistemas");
            curso.setPeriodoCurso("Matutino");
            curso.setTipoCurso("EAD");

            // DAO
            CursoDAO cursoDao = new CursoDAO();

            // Altera
            cursoDao.alterar(curso);

            System.out.println("Curso alterado com sucesso!");

            // Exibe dados alterados
            System.out.println("ID: " + curso.getPkCurso());
            System.out.println("Nome: " + curso.getNomeCurso());
            System.out.println("Período: " + curso.getPeriodoCurso());
            System.out.println("Tipo: " + curso.getTipoCurso());

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());

        }

    }

}