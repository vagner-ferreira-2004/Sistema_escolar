package br.com.academico.teste;

import br.com.academico.dao.SemestreDAO;
import br.com.academico.model.Semestre;

public class TesteSemestre {

    public static void main(String[] args) {

        try {

            // =========================
            // SEMESTRE
            // =========================

            // Instancia objeto Semestre
            Semestre semestre = new Semestre();

            // Preenche os dados do semestre
            semestre.setAno(2026);
            semestre.setSemestre(1);

            // Instancia DAO responsável pela tabela semestres
            SemestreDAO semestreDao = new SemestreDAO();

            // Salva o semestre no banco de dados.
            //
            // O método salvar() retorna o valor da PK gerada
            // automaticamente pelo AUTO_INCREMENT da tabela semestres.
            //
            // Exemplo:
            // pk_semestre = 1
            int idSemestre = semestreDao.salvar(semestre);

            System.out.println("Semestre salvo com sucesso!");

            System.out.println("ID do semestre gerado: " + idSemestre);

        } catch (Exception e) {

            // Exibe erro caso aconteça alguma exceção
            System.out.println("Erro: " + e.getLocalizedMessage());
        }
    }
}