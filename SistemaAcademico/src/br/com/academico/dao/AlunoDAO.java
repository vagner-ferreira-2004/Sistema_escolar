package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.academico.model.Aluno;
import br.com.academico.util.ConnectionFactory;

public class AlunoDAO {

<<<<<<< HEAD
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // CONEXÃO
    public AlunoDAO() throws Exception {

        try {

            conn = ConnectionFactory.getConnection();

        } catch (Exception e) {

            throw new Exception("Erro: " + e.getLocalizedMessage());

        }

    }

    // =========================
    // SALVAR
    // =========================

    public void salvar(Aluno aluno) throws Exception {

        try {

            String sql = "INSERT INTO alunos("
                    + "pk_rgm, "
                    + "fk_pessoa, "
                    + "tipo_aluno"
                    + ") VALUES (?, ?, ?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1, aluno.getPkRgm());
            ps.setInt(2, aluno.getFkPessoa());

            ps.executeUpdate();

        } catch (Exception e) {

            throw new Exception("Erro ao salvar: " + e.getMessage());

        } finally {

            if (ps != null) {
                ps.close();
            }

            if (conn != null) {
                conn.close();
            }

        }

    }

    // =========================
    // ALTERAR
    // =========================

    public void alterar(Aluno aluno) throws Exception {

        try {

            String sql = "UPDATE alunos "
                    + "SET fk_pessoa = ?, "
                    + "WHERE pk_rgm = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, aluno.getFkPessoa());
            ps.setString(3, aluno.getPkRgm());

            ps.executeUpdate();

        } catch (Exception e) {

            throw new Exception("Erro ao alterar: " + e.getMessage());

        } finally {

            if (ps != null) {
                ps.close();
            }

            if (conn != null) {
                conn.close();
            }

        }

    }

    // =========================
    // EXCLUIR
    // =========================

    public void excluir(String pkRgm) throws Exception {

        try {

            String sql = "DELETE FROM alunos "
                    + "WHERE pk_rgm = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, pkRgm);

            ps.executeUpdate();

        } catch (Exception e) {

            throw new Exception("Erro ao excluir: " + e.getMessage());

        } finally {

            if (ps != null) {
                ps.close();
            }

            if (conn != null) {
                conn.close();
            }

        }

    }

    // =========================
    // LISTAR
    // =========================

    public List<Aluno> listar() throws Exception {

        try {

            String sql = "SELECT * FROM alunos ORDER BY pk_rgm";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            List<Aluno> lista = new ArrayList<>();

            while (rs.next()) {

                Aluno a = new Aluno();

                a.setPkRgm(rs.getString("pk_rgm"));
                a.setFkPessoa(rs.getInt("fk_pessoa"));

                lista.add(a);

            }

            return lista;

        } catch (Exception e) {

            throw new Exception("Erro ao listar: " + e.getMessage());

        } finally {

            if (rs != null) {
                rs.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (conn != null) {
                conn.close();
            }

        }

    }

}
=======
	
	public void salvar(Aluno aluno) throws Exception {
		try {
			String sql="INSERT INTO alunos(pk_rgm, fk_pessoa, tipo_aluno)  VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, aluno.getPkRgm());
			ps.setInt(2, aluno.getFkPessoa());
			ps.executeUpdate(); //Realiza o INSERT no BD
			ps.close(); //Fecha o PreparedStatement para evitar memory Leak
			conn.close(); //Fecha conexão com BD
			
			
		} catch(Exception e) {
			throw new Exception("Erro ao salvar" + e.getMessage());
		}
	}
	
	
}
>>>>>>> 853cd2ca5700838311deef39e264989737cd330c
