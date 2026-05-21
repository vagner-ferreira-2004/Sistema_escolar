package br.com.academico.util;

import java.sql.DriverManager;

import javax.swing.JOptionPane;

import java.sql.Connection;

public class ConnectionFactory {

	public static Connection getConnection() throws Exception {

		try {
			// Indica o DB mysql e aponta para o driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Conexão com o Banco
			String login = "root";
			String senha = "";
			String porta = "3307";
			String url = "jdbc:mysql://localhost:" + porta + "/cadastro";
			return DriverManager.getConnection(url, login, senha);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
}