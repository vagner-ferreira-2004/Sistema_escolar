package br.com.academico.teste;

import br.com.academico.dao.AlunoDAO;
import br.com.academico.dao.EnderecoDAO;
import br.com.academico.dao.PessoaDAO;
import br.com.academico.model.Aluno;
import br.com.academico.model.Endereco;
import br.com.academico.model.Pessoa;

public class TestaTudo {

    public static void main(String[] args) {

        try {

            // =========================
            // ENDEREÇO
            // =========================

            Endereco endereco = new Endereco();

            endereco.setLogradouro("Avenida Brasil");
            endereco.setCep("07230-120");
            endereco.setNumeroEndereco("250");
            endereco.setComplemento("apartamento");
            endereco.setMunicipio("Guarulhos");
            endereco.setUf("SP");

            EnderecoDAO enderecoDao = new EnderecoDAO();

            int idEndereco = enderecoDao.salvar(endereco);

            System.out.println("Endereço salvo com sucesso!");



            // =========================
            // PESSOA
            // =========================

            Pessoa pessoa = new Pessoa();

            pessoa.setFkEndereco(idEndereco);
            pessoa.setCpfPessoa("987.654.321-00");
            pessoa.setNomePessoa("Lucas Henrique Martins");
            pessoa.setDataNascimento("2004-09-15");

            PessoaDAO pessoaDao = new PessoaDAO();

            int idPessoa = pessoaDao.salvar(pessoa);

            System.out.println("Pessoa salva com sucesso!");



            // =========================
            // ALUNO
            // =========================

            Aluno aluno = new Aluno();

            aluno.setPkRgm("2026100458");
            aluno.setFkPessoa(idPessoa);
            aluno.setTipoAluno("PAGANTE");

            AlunoDAO alunoDao = new AlunoDAO();

            alunoDao.salvar(aluno);

            System.out.println("Aluno salvo com sucesso!");

        } catch (Exception e) {

            System.out.println("Erro: " + e.getLocalizedMessage());
        }
    }
}