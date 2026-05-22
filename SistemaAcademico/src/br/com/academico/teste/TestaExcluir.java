package br.com.academico.teste;

import br.com.academico.dao.CursoDAO;

public class TestaExcluir {

    public static void main(String[] args) {

        try {

            // ID que será excluído
            int idCurso = 1;

            // DAO
            CursoDAO cursoDao = new CursoDAO();

            // Exclui
            cursoDao.excluir(idCurso);

            System.out.println("Curso excluído com sucesso!");
            System.out.println("ID removido: " + idCurso);

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());

        }

    }

}