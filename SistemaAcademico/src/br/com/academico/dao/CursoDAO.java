package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.academico.model.Curso;
import br.com.academico.util.ConnectionFactory;

public class CursoDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // CONEXÃO
    public CursoDAO() throws Exception {

        try {

            conn = ConnectionFactory.getConnection();

        } catch (Exception e) {

            throw new Exception("Erro: " + e.getLocalizedMessage());

        }

    }

    // =========================
    // SALVAR
    // =========================

    public int salvar(Curso curso) throws Exception {

        try {

            String sql = "INSERT INTO cursos(nome_curso, periodo_curso, tipo_curso) "
                       + "VALUES (?, ?, ?)";

            ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, curso.getNomeCurso());
            ps.setString(2, curso.getPeriodoCurso());
            ps.setString(3, curso.getTipoCurso());

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

    public void alterar(Curso curso) throws Exception {

        try {

            String sql = "UPDATE cursos "
                       + "SET nome_curso = ?, "
                       + "periodo_curso = ?, "
                       + "tipo_curso = ? "
                       + "WHERE pk_curso = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, curso.getNomeCurso());
            ps.setString(2, curso.getPeriodoCurso());
            ps.setString(3, curso.getTipoCurso());
            ps.setInt(4, curso.getPkCurso());

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

    public void excluir(int pkCurso) throws Exception {

        try {

            String sql = "DELETE FROM cursos "
                       + "WHERE pk_curso = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, pkCurso);

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

    public List<Curso> listar() throws Exception {

        try {

            String sql = "SELECT * FROM cursos ORDER BY nome_curso";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            List<Curso> lista = new ArrayList<>();

            while (rs.next()) {

                Curso c = new Curso();

                c.setPkCurso(rs.getInt("pk_curso"));
                c.setNomeCurso(rs.getString("nome_curso"));
                c.setPeriodoCurso(rs.getString("periodo_curso"));
                c.setTipoCurso(rs.getString("tipo_curso"));

                lista.add(c);

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