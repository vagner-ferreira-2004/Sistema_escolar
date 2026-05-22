package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.academico.model.Falta;
import br.com.academico.util.ConnectionFactory;

public class FaltaDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // CONEXÃO
    public FaltaDAO() throws Exception {

        try {

            conn = ConnectionFactory.getConnection();

        } catch (Exception e) {

            throw new Exception("Erro: " + e.getLocalizedMessage());

        }

    }

    // =========================
    // SALVAR
    // =========================

    public void salvar(Falta falta) throws Exception {

        try {

            String sql = "INSERT INTO faltas("
                    + "fk_rgm, "
                    + "fk_semestre, "
                    + "fk_disciplina, "
                    + "qtd_falta"
                    + ") VALUES (?, ?, ?, ?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1, falta.getFkRgm());
            ps.setInt(2, falta.getFkSemestre());
            ps.setInt(3, falta.getFkDisciplina());
            ps.setInt(4, falta.getQtdFalta());

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

    public void alterar(Falta falta) throws Exception {

        try {

            String sql = "UPDATE faltas "
                    + "SET qtd_falta = ? "
                    + "WHERE fk_rgm = ? "
                    + "AND fk_semestre = ? "
                    + "AND fk_disciplina = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, falta.getQtdFalta());
            ps.setString(2, falta.getFkRgm());
            ps.setInt(3, falta.getFkSemestre());
            ps.setInt(4, falta.getFkDisciplina());

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

    public void excluir(String fkRgm,
                        int fkSemestre,
                        int fkDisciplina) throws Exception {

        try {

            String sql = "DELETE FROM faltas "
                    + "WHERE fk_rgm = ? "
                    + "AND fk_semestre = ? "
                    + "AND fk_disciplina = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, fkRgm);
            ps.setInt(2, fkSemestre);
            ps.setInt(3, fkDisciplina);

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

    public List<Falta> listar() throws Exception {

        try {

            String sql = "SELECT * FROM faltas ORDER BY fk_rgm";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            List<Falta> lista = new ArrayList<>();

            while (rs.next()) {

                Falta f = new Falta();

                f.setFkRgm(rs.getString("fk_rgm"));
                f.setFkSemestre(rs.getInt("fk_semestre"));
                f.setFkDisciplina(rs.getInt("fk_disciplina"));
                f.setQtdFalta(rs.getInt("qtd_falta"));

                lista.add(f);

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