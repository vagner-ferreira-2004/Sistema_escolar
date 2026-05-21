package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.academico.model.Pessoa;
import br.com.academico.util.ConnectionFactory;

public class PessoaDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	
	
	public PessoaDAO() throws Exception{
		try {
			conn = ConnectionFactory.getConnection();
		} catch(Exception e) {
			throw new Exception("Erro "+ e.getLocalizedMessage());
		}
	}
	
	
	public int salvar(Pessoa pessoa) throws Exception {
		try {
			String sql="INSERT INTO pessoas(fk_endereco, cpf_pessoa, nome_pessoa, data_nascimento)  VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); //Parâmetro para retornar ID da PK gerado com AUTO INCREMENT
			ps.setInt(1, pessoa.getFkEndereco());
			ps.setString(2, pessoa.getCpfPessoa());
			ps.setString(3, pessoa.getNomePessoa());
			ps.setString(4, pessoa.getDataNascimento());
			ps.executeUpdate(); //Realiza o INSERT no BD
			
			ResultSet rs = ps.getGeneratedKeys();   // Recupera o ID (PK) gerado automaticamente pelo banco após o INSERT
			int idGerado = 0;
			
			 // Verifica se o banco retornou algum valor
			if (rs.next()) {
				// Pega a primeira coluna do resultado (que é o ID gerado)
				idGerado = rs.getInt(1); 
			}
			
			rs.close();
			ps.close(); //Fecha o PreparedStatement para evitar memory Leak
			conn.close(); //Fecha conexão com BD
			
			return idGerado;
		} catch(Exception e) {
			throw new Exception("Erro ao salvar" + e.getMessage());
		}
	}
	
	
}
