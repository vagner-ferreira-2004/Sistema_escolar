package br.com.academico.teste;

import br.com.academico.dao.CampusDAO;
import br.com.academico.model.Campus;

public class TesteCampus {

    public static void main(String[] args) {

        try {

            // =========================
            // CAMPUS
            // =========================

            // Instancia objeto Campus
            Campus campus = new Campus();

            // Preenche os dados
            campus.setNomeCampus("UNICID - Tatuapé");

            // Instancia DAO responsável pela tabela campus
            CampusDAO campusDao = new CampusDAO();

            // Salva no banco
            int idCampus = campusDao.salvar(campus);

            System.out.println("Campus salvo com sucesso!");
            System.out.println("ID gerado: " + idCampus);

        } catch (Exception e) {

            // Exibe erro caso aconteça problema
            System.out.println("Erro: " + e.getLocalizedMessage());
        }
    }
}