package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.academico.model.Endereco;
import br.com.academico.util.ConnectionFactory;

public class EnderecoDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	
	//Chama a classe que faz conexão com o Banco
	public EnderecoDAO() throws Exception{
		try {
			conn = ConnectionFactory.getConnection();
		} catch(Exception e) {
			throw new Exception("Erro "+ e.getLocalizedMessage());
		}
	}
	
	//Método para salvar os dados no banco
	public int salvar(Endereco endereco) throws Exception {
		try {
			String sql="INSERT INTO enderecos(logradouro, cep, numero_endereco, complemento, municipio, uf)  VALUES (?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); //Parâmetro para retornar ID da PK gerado com AUTO INCREMENT
			ps.setString(1, endereco.getLogradouro());
			ps.setString(2, endereco.getCep());
			ps.setString(3, endereco.getNumeroEndereco());
			ps.setString(4, endereco.getComplemento());
			ps.setString(5, endereco.getMunicipio());
			ps.setString(6, endereco.getUf());
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
			
			return idGerado; // Retorna o ID gerado da PK para ser usado
							// como FK em outras tabelas relacionadas
		} catch(Exception e) {
			throw new Exception("Erro ao salvar" + e.getMessage());
		}
	}
	
	
}
