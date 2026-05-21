package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.academico.model.Semestre;
import br.com.academico.util.ConnectionFactory;

public class SemestreDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	
	//Chama a classe que faz conexão com o Banco
	public SemestreDAO() throws Exception{
		try {
			conn = ConnectionFactory.getConnection();
		} catch(Exception e) {
			throw new Exception("Erro "+ e.getLocalizedMessage());
		}
	}
	
	//Método para salvar os dados no banco
	public int salvar(Semestre semestre) throws Exception {
		try {
			String sql="INSERT INTO semestres(ano, semestre_ano)  VALUES (?, ?)";
			ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); //Parâmetro para retornar ID da PK gerado com AUTO INCREMENT
			ps.setInt(1, semestre.getAno());
			ps.setInt(2, semestre.getSemestre());

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
