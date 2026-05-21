package br.com.academico.teste;

import br.com.academico.dao.AlunoCursoDAO;
import br.com.academico.model.AlunoCurso;

public class TesteAlunoCurso {

    public static void main(String[] args) {

        try {

            AlunoCurso alunoCurso = new AlunoCurso();

            alunoCurso.setFkAluno("2026100458");
            alunoCurso.setFkCurso(1);

            AlunoCursoDAO dao = new AlunoCursoDAO();

            dao.salvar(alunoCurso);

            System.out.println("Aluno vinculado ao curso com sucesso!");

        } catch (Exception e) {

            System.out.println("Erro: " + e.getLocalizedMessage());
        }
    }
}