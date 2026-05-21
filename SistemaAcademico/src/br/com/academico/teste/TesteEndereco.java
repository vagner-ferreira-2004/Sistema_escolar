package br.com.academico.teste;

import br.com.academico.dao.EnderecoDAO;
import br.com.academico.model.Endereco;

public class TesteEndereco {
	public static void main(String[] args) {
		
		try {
			Endereco endereco = new Endereco();
			endereco.setLogradouro("Rua do Limao");
			endereco.setCep("08550-000");
			endereco.setNumeroEndereco("145");
			endereco.setComplemento("casa A");
			endereco.setMunicipio("Itaquaquecetuba");
			endereco.setUf("SP");
			
			EnderecoDAO dao = new EnderecoDAO();
			
			dao.salvar(endereco);
			
			System.out.println("Endereco salvo com sucesso!");
			
		} catch(Exception e) {
			System.out.println("Erro: " + e.getLocalizedMessage());
		}
	}
	
	
}
