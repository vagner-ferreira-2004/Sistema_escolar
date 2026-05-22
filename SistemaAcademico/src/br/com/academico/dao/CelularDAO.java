package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.academico.model.Celular;
import br.com.academico.util.ConnectionFactory;

public class CelularDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // CONEXÃO
    public CelularDAO() throws Exception {

        try {

            conn = ConnectionFactory.getConnection();

        } catch (Exception e) {

            throw new Exception("Erro: " + e.getLocalizedMessage());

        }

    }

    // =========================
    // SALVAR
    // =========================

    public void salvar(Celular celular) throws Exception {

        try {

            String sql = "INSERT INTO celulares(fk_pessoa, numero_celular) "
                       + "VALUES (?, ?)";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, celular.getFkPessoa());
            ps.setString(2, celular.getNumeroCelular());

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

    public void alterar(Celular celular) throws Exception {

        try {

            String sql = "UPDATE celulares "
                       + "SET numero_celular = ? "
                       + "WHERE fk_pessoa = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, celular.getNumeroCelular());
            ps.setInt(2, celular.getFkPessoa());

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

    public void excluir(int fkPessoa) throws Exception {

        try {

            String sql = "DELETE FROM celulares "
                       + "WHERE fk_pessoa = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, fkPessoa);

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

    public List<Celular> listar() throws Exception {

        try {

            String sql = "SELECT * FROM celulares";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            List<Celular> lista = new ArrayList<>();

            while (rs.next()) {

                Celular c = new Celular();

                c.setFkPessoa(rs.getInt("fk_pessoa"));
                c.setNumeroCelular(rs.getString("numero_celular"));

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