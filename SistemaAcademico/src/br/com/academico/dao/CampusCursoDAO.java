package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.academico.model.CampusCurso;
import br.com.academico.util.ConnectionFactory;

public class CampusCursoDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // CONEXÃO
    public CampusCursoDAO() throws Exception {

        try {

            conn = ConnectionFactory.getConnection();

        } catch (Exception e) {

            throw new Exception("Erro: " + e.getLocalizedMessage());

        }

    }

    // =========================
    // SALVAR
    // =========================

    public void salvar(CampusCurso campusCurso) throws Exception {

        try {

            String sql = "INSERT INTO campus_curso("
                    + "fk_campus, "
                    + "fk_curso"
                    + ") VALUES (?, ?)";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, campusCurso.getFkCampus());
            ps.setInt(2, campusCurso.getFkCurso());

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

    public void alterar(CampusCurso campusCurso,
                         int fkCampusAntigo,
                         int fkCursoAntigo) throws Exception {

        try {

            String sql = "UPDATE campus_curso "
                    + "SET fk_campus = ?, "
                    + "fk_curso = ? "
                    + "WHERE fk_campus = ? "
                    + "AND fk_curso = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, campusCurso.getFkCampus());
            ps.setInt(2, campusCurso.getFkCurso());
            ps.setInt(3, fkCampusAntigo);
            ps.setInt(4, fkCursoAntigo);

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

    public void excluir(int fkCampus,
                         int fkCurso) throws Exception {

        try {

            String sql = "DELETE FROM campus_curso "
                    + "WHERE fk_campus = ? "
                    + "AND fk_curso = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, fkCampus);
            ps.setInt(2, fkCurso);

            ps.executeUpdate();

        } catch (Exception e) {

            throw new Exception("Erro ao excluir: " + e.getMessage());

        } finally {

            if (ps != null) {
                ps.close();
            }

            if (conn !=null) {
                conn.close();
            }

        }

    }

    // =========================
    // LISTAR
    // =========================

    public List<CampusCurso> listar() throws Exception {

        try {

            String sql = "SELECT * FROM campus_curso";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            List<CampusCurso> lista = new ArrayList<>();

            while (rs.next()) {

                CampusCurso cc = new CampusCurso();

                cc.setFkCampus(rs.getInt("fk_campus"));
                cc.setFkCurso(rs.getInt("fk_curso"));

                lista.add(cc);

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