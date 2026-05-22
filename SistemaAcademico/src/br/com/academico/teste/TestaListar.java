package br.com.academico.teste;

import java.util.List;

import br.com.academico.dao.CursoDAO;
import br.com.academico.model.Curso;

public class TestaListar {

    public static void main(String[] args) {

        try {

            // DAO
            CursoDAO cursoDao = new CursoDAO();

            // Lista todos os cursos
            List<Curso> lista = cursoDao.listar();

            // Percorre lista
            for (Curso curso : lista) {

                System.out.println("==================================");
                System.out.println("ID CURSO: " + curso.getPkCurso());
                System.out.println("NOME CURSO: " + curso.getNomeCurso());
                System.out.println("PERÍODO: " + curso.getPeriodoCurso());
                System.out.println("TIPO: " + curso.getTipoCurso());
                System.out.println("==================================");

            }

            System.out.println("Total de cursos: " + lista.size());

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());

        }

    }

}