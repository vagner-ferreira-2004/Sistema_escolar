package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.academico.model.AlunoCurso;
import br.com.academico.util.ConnectionFactory;

public class AlunoCursoDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	
	
	public AlunoCursoDAO() throws Exception{
		try {
			conn = ConnectionFactory.getConnection();
		} catch(Exception e) {
			throw new Exception("Erro "+ e.getLocalizedMessage());
		}
	}
	
	
	public void salvar(AlunoCurso alunoCurso) throws Exception {
		try {
			String sql="INSERT INTO aluno_curso(fk_aluno, fk_curso)  VALUES (?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, alunoCurso.getFkAluno());
			ps.setInt(2, alunoCurso.getFkCurso());
			ps.executeUpdate(); //Realiza o INSERT no BD
			ps.close(); //Fecha o PreparedStatement para evitar memory Leak
			conn.close(); //Fecha conexão com BD
			
			
		} catch(Exception e) {
			throw new Exception("Erro ao salvar" + e.getMessage());
		}
	}
	
	
}
