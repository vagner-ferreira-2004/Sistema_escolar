package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.academico.model.Email;
import br.com.academico.util.ConnectionFactory;

public class EmailDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	
	
	public EmailDAO() throws Exception{
		try {
			conn = ConnectionFactory.getConnection();
		} catch(Exception e) {
			throw new Exception("Erro "+ e.getLocalizedMessage());
		}
	}
	
	
	public void salvar(Email email) throws Exception {
		try {
			String sql="INSERT INTO emails(endereco_email, fk_pessoa)  VALUES (?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email.getEnderecoEmail());
			ps.setInt(2, email.getFkPessoa());
			ps.executeUpdate(); //Realiza o INSERT no BD
			ps.close(); //Fecha o PreparedStatement para evitar memory Leak
			conn.close(); //Fecha conexão com BD
			
			
		} catch(Exception e) {
			throw new Exception("Erro ao salvar" + e.getMessage());
		}
	}
	
	
}
