package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.academico.model.Nota;
import br.com.academico.util.ConnectionFactory;

public class NotaDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // CONEXÃO
    public NotaDAO() throws Exception {

        try {

            conn = ConnectionFactory.getConnection();

        } catch (Exception e) {

            throw new Exception("Erro: " + e.getLocalizedMessage());

        }

    }

    // =========================
    // SALVAR
    // =========================

    public void salvar(Nota nota) throws Exception {

        try {

            String sql = "INSERT INTO notas("
                    + "fk_rgm, "
                    + "fk_semestre, "
                    + "fk_disciplina, "
                    + "valor_nota"
                    + ") VALUES (?, ?, ?, ?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1, nota.getFkRgm());
            ps.setInt(2, nota.getFkSemestre());
            ps.setInt(3, nota.getFkDisciplina());
            ps.setDouble(4, nota.getValorNota());

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

    public void alterar(Nota nota) throws Exception {

        try {

            String sql = "UPDATE notas "
                    + "SET valor_nota = ? "
                    + "WHERE fk_rgm = ? "
                    + "AND fk_semestre = ? "
                    + "AND fk_disciplina = ?";

            ps = conn.prepareStatement(sql);

            ps.setDouble(1, nota.getValorNota());
            ps.setString(2, nota.getFkRgm());
            ps.setInt(3, nota.getFkSemestre());
            ps.setInt(4, nota.getFkDisciplina());

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

            String sql = "DELETE FROM notas "
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

    public List<Nota> listar() throws Exception {

        try {

            String sql = "SELECT * FROM notas ORDER BY fk_rgm";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            List<Nota> lista = new ArrayList<>();

            while (rs.next()) {

                Nota n = new Nota();

                n.setFkRgm(rs.getString("fk_rgm"));
                n.setFkSemestre(rs.getInt("fk_semestre"));
                n.setFkDisciplina(rs.getInt("fk_disciplina"));
                n.setValorNota(rs.getDouble("valor_nota"));

                lista.add(n);

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