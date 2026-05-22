package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.academico.model.Endereco;
import br.com.academico.util.ConnectionFactory;

public class EnderecoDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // CONEXÃO
    public EnderecoDAO() throws Exception {

        try {

            conn = ConnectionFactory.getConnection();

        } catch (Exception e) {

            throw new Exception("Erro: " + e.getLocalizedMessage());

        }

    }

    // =========================
    // SALVAR
    // =========================

    public int salvar(Endereco endereco) throws Exception {

        try {

            String sql = "INSERT INTO enderecos("
                    + "logradouro, "
                    + "cep, "
                    + "numero_endereco, "
                    + "complemento, "
                    + "municipio, "
                    + "uf"
                    + ") VALUES (?, ?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getCep());
            ps.setString(3, endereco.getNumeroEndereco());
            ps.setString(4, endereco.getComplemento());
            ps.setString(5, endereco.getMunicipio());
            ps.setString(6, endereco.getUf());

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

    public void alterar(Endereco endereco) throws Exception {

        try {

            String sql = "UPDATE enderecos "
                    + "SET logradouro = ?, "
                    + "cep = ?, "
                    + "numero_endereco = ?, "
                    + "complemento = ?, "
                    + "municipio = ?, "
                    + "uf = ? "
                    + "WHERE pk_endereco = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getCep());
            ps.setString(3, endereco.getNumeroEndereco());
            ps.setString(4, endereco.getComplemento());
            ps.setString(5, endereco.getMunicipio());
            ps.setString(6, endereco.getUf());
            ps.setInt(7, endereco.getPkEnderecos());

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

    public void excluir(int pkEndereco) throws Exception {

        try {

            String sql = "DELETE FROM enderecos "
                    + "WHERE pk_endereco = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, pkEndereco);

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

    public List<Endereco> listar() throws Exception {

        try {

            String sql = "SELECT * FROM enderecos ORDER BY logradouro";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            List<Endereco> lista = new ArrayList<>();

            while (rs.next()) {

                Endereco e = new Endereco();

                e.setPkEnderecos(rs.getInt("pk_endereco"));
                e.setLogradouro(rs.getString("logradouro"));
                e.setCep(rs.getString("cep"));
                e.setNumeroEndereco(rs.getString("numero_endereco"));
                e.setComplemento(rs.getString("complemento"));
                e.setMunicipio(rs.getString("municipio"));
                e.setUf(rs.getString("uf"));

                lista.add(e);

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