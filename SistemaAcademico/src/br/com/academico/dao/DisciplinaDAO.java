package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.academico.model.Disciplina;
import br.com.academico.util.ConnectionFactory;

public class DisciplinaDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // CONEXÃO
    public DisciplinaDAO() throws Exception {

        try {
 
            conn = ConnectionFactory.getConnection();

        } catch (Exception e) {

            throw new Exception("Erro: " + e.getLocalizedMessage());

        }

    }

    // =========================
    // SALVAR
    // =========================

    public int salvar(Disciplina disciplina) throws Exception {

        try {

            String sql = "INSERT INTO disciplinas(nome_disciplina) "
                       + "VALUES (?)";

            ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, disciplina.getNomeDisciplina());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            int idGerado = 0;

            if (rs.next()) {

                idGerado = rs.getInt(1);

            }

            return idGerado;

        } catch (Exception e) {

            throw new Exception("Erro ao salvar: " + e.getMessage());

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

    // =========================
    // ALTERAR
    // =========================

    public void alterar(Disciplina disciplina) throws Exception {

        try {

            String sql = "UPDATE disciplinas "
                       + "SET nome_disciplina = ? "
                       + "WHERE pk_disciplina = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, disciplina.getNomeDisciplina());
            ps.setInt(2, disciplina.getPkDisciplina());

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

    public void excluir(int pkDisciplina) throws Exception {

        try {

            String sql = "DELETE FROM disciplinas "
                       + "WHERE pk_disciplina = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, pkDisciplina);

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

    public List<Disciplina> listar() throws Exception {

        try {

            String sql = "SELECT * FROM disciplinas ORDER BY nome_disciplina";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            List<Disciplina> lista = new ArrayList<>();

            while (rs.next()) {

                Disciplina d = new Disciplina();

                d.setPkDisciplina(rs.getInt("pk_disciplina"));
                d.setNomeDisciplina(rs.getString("nome_disciplina"));

                lista.add(d);

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