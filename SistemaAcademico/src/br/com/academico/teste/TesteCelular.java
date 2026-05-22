package br.com.academico.teste;

import br.com.academico.dao.CelularDAO;
import br.com.academico.model.Celular;

public class TesteCelular {

    public static void main(String[] args) {

        try {

            // =========================
            // CELULAR
            // =========================

            // Cria objeto celular
            Celular celular = new Celular();

            // Preenche dados
            celular.setFkPessoa(1); // precisa existir na tabela pessoas
            celular.setNumeroCelular("(11) 99999-9999");

            // Instancia DAO
            CelularDAO dao = new CelularDAO();

            // Salva no banco
            dao.salvar(celular);

            System.out.println("Celular salvo com sucesso!");
            System.out.println("Pessoa FK: " + celular.getFkPessoa());
            System.out.println("Número: " + celular.getNumeroCelular());

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}