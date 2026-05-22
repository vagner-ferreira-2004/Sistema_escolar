package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.academico.model.SemestreDisciplina;
import br.com.academico.util.ConnectionFactory;

public class SemestreDisciplinaDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // CONEXÃO
    public SemestreDisciplinaDAO() throws Exception {

        try {

            conn = ConnectionFactory.getConnection();

        } catch (Exception e) {

            throw new Exception("Erro: " + e.getLocalizedMessage());

        }

    }

    // =========================
    // SALVAR
    // =========================

    public void salvar(SemestreDisciplina semestreDisciplina) throws Exception {

        try {

            String sql = "INSERT INTO semestre_disciplina("
                    + "fk_semestre, "
                    + "fk_disciplina"
                    + ") VALUES (?, ?)";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, semestreDisciplina.getFkSemestre());
            ps.setInt(2, semestreDisciplina.getFkDisciplina());

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

    public void alterar(SemestreDisciplina semestreDisciplina,
                         int fkSemestreAntigo,
                         int fkDisciplinaAntiga) throws Exception {

        try {

            String sql = "UPDATE semestre_disciplina "
                    + "SET fk_semestre = ?, "
                    + "fk_disciplina = ? "
                    + "WHERE fk_semestre = ? "
                    + "AND fk_disciplina = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, semestreDisciplina.getFkSemestre());
            ps.setInt(2, semestreDisciplina.getFkDisciplina());

            ps.setInt(3, fkSemestreAntigo);
            ps.setInt(4, fkDisciplinaAntiga);

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

    public void excluir(int fkSemestre,
                         int fkDisciplina) throws Exception {

        try {

            String sql = "DELETE FROM semestre_disciplina "
                    + "WHERE fk_semestre = ? "
                    + "AND fk_disciplina = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, fkSemestre);
            ps.setInt(2, fkDisciplina);

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

    public List<SemestreDisciplina> listar() throws Exception {

        try {

            String sql = "SELECT * FROM semestre_disciplina";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            List<SemestreDisciplina> lista = new ArrayList<>();

            while (rs.next()) {

                SemestreDisciplina sd = new SemestreDisciplina();

                sd.setFkSemestre(rs.getInt("fk_semestre"));
                sd.setFkDisciplina(rs.getInt("fk_disciplina"));

                lista.add(sd);

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