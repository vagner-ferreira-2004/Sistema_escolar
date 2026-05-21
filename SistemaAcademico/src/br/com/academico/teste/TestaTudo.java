package br.com.academico.teste;

import br.com.academico.dao.AlunoCursoDAO;
import br.com.academico.dao.AlunoDAO;
import br.com.academico.dao.CursoDAO;
import br.com.academico.dao.EmailDAO;
import br.com.academico.dao.EnderecoDAO;
import br.com.academico.dao.PessoaDAO;

import br.com.academico.model.Aluno;
import br.com.academico.model.AlunoCurso;
import br.com.academico.model.Curso;
import br.com.academico.model.Email;
import br.com.academico.model.Endereco;
import br.com.academico.model.Pessoa;

public class TestaTudo {

    public static void main(String[] args) {

        try {

            // ENDEREÇO

            // Instancia objeto Endereco
            Endereco endereco = new Endereco();

            // Preenche os dados do endereço
            endereco.setLogradouro("Rua das Flores");
            endereco.setCep("08090-410");
            endereco.setNumeroEndereco("78");
            endereco.setComplemento("casa B");
            endereco.setMunicipio("São Paulo");
            endereco.setUf("SP");

            // Instancia DAO responsável pela tabela enderecos
            EnderecoDAO enderecoDao = new EnderecoDAO();

            // Salva o endereço no banco de dados.
            //
            // O método salvar() retorna o valor da PK gerada
            // automaticamente pelo AUTO_INCREMENT da tabela enderecos.
            //
            // Exemplo:
            // pk_enderecos = 7
            //
            // Esse ID será armazenado na variável idEndereco
            // para posteriormente ser usado como FK na tabela pessoas.
            int idEndereco = enderecoDao.salvar(endereco);

            System.out.println("Endereço salvo com sucesso!");


            // PESSOA

            // Instancia objeto Pessoa
            Pessoa pessoa = new Pessoa();

            // A FK fk_endereco da tabela pessoas precisa receber
            // o ID do endereço salvo anteriormente.
            //
            // Exemplo:
            // pessoas.fk_endereco = 7
            //
            // Isso cria o relacionamento entre:
            // pessoa -> endereço
            pessoa.setFkEndereco(idEndereco);

            // Preenche os dados da pessoa
            pessoa.setCpfPessoa("741.852.963-10");
            pessoa.setNomePessoa("Mariana Oliveira Santos");
            pessoa.setDataNascimento("2003-11-22");

            // Instancia DAO responsável pela tabela pessoas
            PessoaDAO pessoaDao = new PessoaDAO();

            // Salva a pessoa no banco de dados.
            //
            // O método salvar() retorna o valor da PK gerada
            // automaticamente pela tabela pessoas.
            //
            // Exemplo:
            // pk_pessoa = 12
            //
            // Esse ID será armazenado na variável idPessoa
            // para posteriormente ser usado como FK na tabela alunos.
            int idPessoa = pessoaDao.salvar(pessoa);

            System.out.println("Pessoa salva com sucesso!");


            // ALUNO

            // Instancia objeto Aluno
            Aluno aluno = new Aluno();

            // Define o RGM do aluno
            aluno.setPkRgm("2026201145");

            // A FK fk_pessoa da tabela alunos precisa receber
            // o ID da pessoa salva anteriormente.
            //
            // Exemplo:
            // alunos.fk_pessoa = 12
            //
            // Isso cria o relacionamento entre:
            // aluno -> pessoa
            aluno.setFkPessoa(idPessoa);

            // Define o tipo do aluno
            aluno.setTipoAluno("BOLSISTA_PARCIAL");

            // Instancia DAO responsável pela tabela alunos
            AlunoDAO alunoDao = new AlunoDAO();

            // Salva aluno no banco
            alunoDao.salvar(aluno);

            System.out.println("Aluno salvo com sucesso!");


            // CURSO

            // Instancia objeto Curso
            Curso curso = new Curso();

            // Preenche os dados do curso
            curso.setNomeCurso("Análise e Desenvolvimento de Sistemas");
            curso.setPeriodoCurso("NOTURNO");
            curso.setTipoCurso("Presencial");
            curso.setCargaHoraria(2400);

            // Instancia DAO responsável pela tabela cursos
            CursoDAO cursoDao = new CursoDAO();

            // Salva o curso no banco de dados.
            //
            // O método salvar() retorna o valor da PK gerada
            // automaticamente pela tabela cursos.
            //
            // Exemplo:
            // pk_curso = 3
            //
            // Esse ID será utilizado na tabela associativa aluno_curso
            // para vincular o aluno ao curso.
            int idCurso = cursoDao.salvar(curso);

            System.out.println("Curso salvo com sucesso!");


            // ALUNO_CURSO

            // Instancia objeto responsável pelo relacionamento
            // entre aluno e curso
            AlunoCurso alunoCurso = new AlunoCurso();

            // A FK fk_aluno da tabela aluno_curso
            // recebe o RGM do aluno cadastrado.
            //
            // Exemplo:
            // aluno_curso.fk_aluno = 2026201145
            //
            // Isso cria o relacionamento:
            // aluno_curso -> aluno
            alunoCurso.setFkAluno(aluno.getPkRgm());

            // A FK fk_curso da tabela aluno_curso precisa receber
            // o ID do curso salvo anteriormente.
            //
            // Exemplo:
            // aluno_curso.fk_curso = 3
            //
            // Isso cria o relacionamento:
            // aluno_curso -> curso
            alunoCurso.setFkCurso(idCurso);

            // Instancia DAO da tabela associativa
            AlunoCursoDAO alunoCursoDao = new AlunoCursoDAO();

            // Salva relacionamento entre aluno e curso
            alunoCursoDao.salvar(alunoCurso);

            System.out.println("Aluno vinculado ao curso com sucesso!");
            
            
            // =========================
            // EMAIL
            // =========================

            Email email = new Email();

            // Email fictício
            email.setEnderecoEmail("mariana.santos@gmail.com");

            // FK da pessoa já cadastrada no banco
            email.setFkPessoa(idPessoa);

            // Campo tipoEmail existe na model,
            // mas o banco irá usar DEFAULT 'Pessoal'
            // então não é necessário utilizar setter agora


            EmailDAO emailDao = new EmailDAO();

            emailDao.salvar(email);

            System.out.println("Email salvo com sucesso!");


            System.out.println("Cadastro completo realizado!");

        } catch (Exception e) {

            // Exibe erro caso aconteça alguma exceção
            System.out.println("Erro: " + e.getLocalizedMessage());
        }
    }
}