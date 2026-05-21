package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.academico.model.Curso;
import br.com.academico.util.ConnectionFactory;

public class CursoDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	
	//Chama a classe que faz conexão com o Banco
	public CursoDAO() throws Exception{
		try {
			conn = ConnectionFactory.getConnection();
		} catch(Exception e) {
			throw new Exception("Erro "+ e.getLocalizedMessage());
		}
	}
	
	//Método para salvar os dados no banco
	public int salvar(Curso curso) throws Exception {
		try {
			String sql="INSERT INTO cursos(nome_curso, periodo_curso, tipo_curso, carga_horaria_curso)  VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); //Parâmetro para retornar ID da PK gerado com AUTO INCREMENT
			ps.setString(1, curso.getNomeCurso());
			ps.setString(2, curso.getPeriodoCurso());
			ps.setString(3, curso.getTipoCurso());
			ps.setInt(4, curso.getCargaHoraria());
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
