package br.com.academico.teste;

import br.com.academico.dao.PessoaDAO;
import br.com.academico.model.Pessoa;

public class TestePessoa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Pessoa pessoa = new Pessoa();
			
			pessoa.setFkEndereco(1);
			pessoa.setCpfPessoa("123.456.789-12");
			pessoa.setNomePessoa("Erick Luiz de Andrade Carrera");
			pessoa.setDataNascimento("2007-02-06");
			
			
			PessoaDAO dao = new PessoaDAO();
			
			dao.salvar(pessoa);
			
			System.out.println("Pessoa salvo com sucesso! ");
		} catch(Exception e) {
			System.out.println("Erro" + e.getLocalizedMessage());
		}

	}
	

}
