package br.com.academico.teste;

import br.com.academico.dao.EmailDAO;
import br.com.academico.model.Email;

public class TesteEmail {

    public static void main(String[] args) {

        try {

            // =========================
            // EMAIL
            // =========================

            Email email = new Email();

            // Email fictício
            email.setEnderecoEmail("mariana.santos@gmail.com");

            // FK da pessoa já cadastrada no banco
            email.setFkPessoa(1);

            // Campo tipoEmail existe na model,
            // mas o banco irá usar DEFAULT 'Pessoal'
            // então não é necessário utilizar setter agora


            EmailDAO emailDao = new EmailDAO();

            emailDao.salvar(email);

            System.out.println("Email salvo com sucesso!");

        } catch (Exception e) {

            System.out.println("Erro: " + e.getLocalizedMessage());
        }
    }
}