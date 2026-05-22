package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.academico.model.Campus;
import br.com.academico.util.ConnectionFactory;

public class CampusDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // CONEXÃO
    public CampusDAO() throws Exception {

        try {

            conn = ConnectionFactory.getConnection();

        } catch (Exception e) {

            throw new Exception("Erro: " + e.getLocalizedMessage());

        }

    }

    // =========================
    // SALVAR
    // =========================

    public int salvar(Campus campus) throws Exception {

        try {

            String sql = "INSERT INTO campus(nome_campus) VALUES (?)";

            ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, campus.getNomeCampus());

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

    public void alterar(Campus campus) throws Exception {

        try {

            String sql = "UPDATE campus "
                       + "SET nome_campus = ? "
                       + "WHERE pk_campus = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, campus.getNomeCampus());
            ps.setInt(2, campus.getPkCampus());

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

    public void excluir(int pkCampus) throws Exception {

        try {

            String sql = "DELETE FROM campus "
                       + "WHERE pk_campus = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, pkCampus);

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

    public List<Campus> listar() throws Exception {

        try {

            String sql = "SELECT * FROM campus ORDER BY nome_campus";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            List<Campus> lista = new ArrayList<>();

            while (rs.next()) {

                Campus c = new Campus();

                c.setPkCampus(rs.getInt("pk_campus"));
                c.setNomeCampus(rs.getString("nome_campus"));

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