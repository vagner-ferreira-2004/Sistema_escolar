package br.com.academico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.academico.model.AlunoCurso;
import br.com.academico.util.ConnectionFactory;

public class AlunoCursoDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public AlunoCursoDAO() throws Exception {

        try {

        	
            conn = ConnectionFactory.getConnection();

        } catch (Exception e) {

            throw new Exception("Erro ao conectar: " + e.getMessage());

        }

    }

    // =========================
    // SALVAR
    // =========================

    public void salvar(AlunoCurso alunoCurso) throws Exception {

        try {

            String sql = "INSERT INTO aluno_curso (fk_aluno, fk_curso) VALUES (?, ?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1, alunoCurso.getFkAluno());
            ps.setInt(2, alunoCurso.getFkCurso());

            ps.executeUpdate();

        } catch (Exception e) {

            throw new Exception("Erro ao salvar: " + e.getMessage());

        } finally {

            ps.close();
            conn.close();

        }

    }

    // =========================
    // ALTERAR
    // =========================

    public void alterar(AlunoCurso alunoCurso,
                        String fkAlunoAntigo,
                        int fkCursoAntigo) throws Exception {

        try {

            String sql = "UPDATE aluno_curso "
                       + "SET fk_aluno = ?, fk_curso = ? "
                       + "WHERE fk_aluno = ? AND fk_curso = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, alunoCurso.getFkAluno());
            ps.setInt(2, alunoCurso.getFkCurso());

            ps.setString(3, fkAlunoAntigo);
            ps.setInt(4, fkCursoAntigo);

            ps.executeUpdate();

        } catch (Exception e) {

            throw new Exception("Erro ao alterar: " + e.getMessage());

        } finally {

            ps.close();
            conn.close();

        }

    }

    // =========================
    // EXCLUIR
    // =========================

    public void excluir(String fkAluno,
                        int fkCurso) throws Exception {

        try {

            String sql = "DELETE FROM aluno_curso "
                       + "WHERE fk_aluno = ? AND fk_curso = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, fkAluno);
            ps.setInt(2, fkCurso);

            ps.executeUpdate();

        } catch (Exception e) {

            throw new Exception("Erro ao excluir: " + e.getMessage());

        } finally {

            ps.close();
            conn.close();

        }

    }

    // =========================
    // LISTAR
    // =========================

    public List<AlunoCurso> listar() throws Exception {

        try {

            String sql = "SELECT * FROM aluno_curso";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            List<AlunoCurso> lista = new ArrayList<>();

            while (rs.next()) {

                AlunoCurso ac = new AlunoCurso();

                ac.setFkAluno(rs.getString("fk_aluno"));
                ac.setFkCurso(rs.getInt("fk_curso"));

                lista.add(ac);

            }

            return lista;

        } catch (Exception e) {

            throw new Exception("Erro ao listar: " + e.getMessage());

        } finally {

            ps.close();
            conn.close();

        }

    }

}