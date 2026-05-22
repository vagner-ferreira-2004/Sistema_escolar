package br.com.academico.teste;

import br.com.academico.dao.DisciplinaDAO;
import br.com.academico.model.Disciplina;

public class TesteDisciplina {

    public static void main(String[] args) {

        try {

            // =========================
            // DISCIPLINA
            // =========================

            // Cria objeto disciplina
            Disciplina disciplina = new Disciplina();

            // Preenche dados
            disciplina.setNomeDisciplina("Programação Orientada a Objetos");

            // Instancia DAO
            DisciplinaDAO disciplinaDao = new DisciplinaDAO();

            // Salva no banco
            int idDisciplina = disciplinaDao.salvar(disciplina);

            System.out.println("Disciplina salva com sucesso!");
            
            System.out.println("ID gerado: " + idDisciplina);

        } catch (Exception e) {

            System.out.println("Erro: " + e.getLocalizedMessage());
        }
    }
}