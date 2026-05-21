package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.academico.model.Aluno;
import br.com.academico.util.ConnectionFactory;

public class AlunoDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	
	
	public AlunoDAO() throws Exception{
		try {
			conn = ConnectionFactory.getConnection();
		} catch(Exception e) {
			throw new Exception("Erro "+ e.getLocalizedMessage());
		}
	}

	
	public void salvar(Aluno aluno) throws Exception {
		try {
			String sql="INSERT INTO alunos(pk_rgm, fk_pessoa, tipo_aluno)  VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, aluno.getPkRgm());
			ps.setInt(2, aluno.getFkPessoa());
			ps.setString(3, aluno.getTipoAluno());
			ps.executeUpdate(); //Realiza o INSERT no BD
			ps.close(); //Fecha o PreparedStatement para evitar memory Leak
			conn.close(); //Fecha conexão com BD
			
			
		} catch(Exception e) {
			throw new Exception("Erro ao salvar" + e.getMessage());
		}
	}
	
	
}
