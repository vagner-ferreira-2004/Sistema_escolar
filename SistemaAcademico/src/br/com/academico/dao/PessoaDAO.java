package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.academico.model.Pessoa;
import br.com.academico.util.ConnectionFactory;

public class PessoaDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // CONEXÃO
    public PessoaDAO() throws Exception {

        try {

            conn = ConnectionFactory.getConnection();

        } catch (Exception e) {

            throw new Exception("Erro: " + e.getLocalizedMessage());

        }

    }

    // =========================
    // SALVAR
    // =========================

    public int salvar(Pessoa pessoa) throws Exception {

        try {

            String sql = "INSERT INTO pessoas("
                    + "fk_endereco, "
                    + "cpf_pessoa, "
                    + "nome_pessoa, "
                    + "data_nascimento"
                    + ") VALUES (?, ?, ?, ?)";

            ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, pessoa.getFkEndereco());
            ps.setString(2, pessoa.getCpfPessoa());
            ps.setString(3, pessoa.getNomePessoa());
            ps.setString(4, pessoa.getDataNascimento());

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

    public void alterar(Pessoa pessoa) throws Exception {

        try {

            String sql = "UPDATE pessoas "
                    + "SET fk_endereco = ?, "
                    + "cpf_pessoa = ?, "
                    + "nome_pessoa = ?, "
                    + "data_nascimento = ? "
                    + "WHERE pk_pessoa = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, pessoa.getFkEndereco());
            ps.setString(2, pessoa.getCpfPessoa());
            ps.setString(3, pessoa.getNomePessoa());
            ps.setString(4, pessoa.getDataNascimento());
            ps.setInt(5, pessoa.getPkPessoa());

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

    public void excluir(int pkPessoa) throws Exception {

        try {

            String sql = "DELETE FROM pessoas "
                    + "WHERE pk_pessoa = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, pkPessoa);

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

    public List<Pessoa> listar() throws Exception {

        try {

            String sql = "SELECT * FROM pessoas ORDER BY nome_pessoa";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            List<Pessoa> lista = new ArrayList<>();

            while (rs.next()) {

                Pessoa p = new Pessoa();

                p.setPkPessoa(rs.getInt("pk_pessoa"));
                p.setFkEndereco(rs.getInt("fk_endereco"));
                p.setCpfPessoa(rs.getString("cpf_pessoa"));
                p.setNomePessoa(rs.getString("nome_pessoa"));
                p.setDataNascimento(rs.getString("data_nascimento"));

                lista.add(p);

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