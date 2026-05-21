package br.com.academico.view;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel cadastro; 
	private CardLayout cl;   
	private JTextField txtRGM;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtMunicipio;
	private JFormattedTextField txtDataNasc;
	private JFormattedTextField txtCPF;
	private JFormattedTextField txtTelefone;
	private JComboBox<String> UF;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtCurso;
	private JTable tabelaConsultar;
	private JTable tabelaExcluir;
	private JTable tabelaAlterar;
	
	// Lista simulando nosso banco de dados
	private List<Aluno> listaAlunos = new ArrayList<>();
	private JTextField txtFaltas;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	 //Classe interna para representar o modelo de dados do Aluno
	class Aluno {
		String rgm, nome, dataNasc, cpf, email, endereco, municipio, uf, telefone;
		Aluno(String rgm, String nome, String dataNasc, String cpf, String email, String endereco, String municipio, String uf, String telefone) {
			this.rgm = rgm; this.nome = nome; this.dataNasc = dataNasc; this.cpf = cpf;
			this.email = email; this.endereco = endereco; this.municipio = municipio; this.uf = uf; this.telefone = telefone;
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() throws Exception {
		// dados fictícios para teste
		listaAlunos.add(new Aluno("123456-7", "Michel Mendes", "10/05/2004", "111.222.333-44", "michel@email.com", "Rua A, 123", "São Paulo", "SP", "(11) 99999-9999"));
		//listaAlunos.add(new Aluno("765432-1", "Rafael da Silva", "22/11/2003", "555.666.777-88", "rafael@email.com", "Av B, 456", "São Paulo", "SP", "(11) 88888-8888"));
		//listaAlunos.add(new Aluno("987654-3", "Matheus Ferreira", "15/08/2002", "999.888.777-66", "matheus@email.com", "Rua C, 789", "Guarulhos", "SP", "(11) 77777-7777"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 561);
		
		cl = new CardLayout(0, 0);
		cadastro = new JPanel();
		cadastro.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cadastro);
		cadastro.setLayout(cl);

		JMenuBar menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		JMenu mnNewMenu = new JMenu("Aluno");
		menuBar_1.add(mnNewMenu);
		
		// 1. Renomeado de Alterar para Cadastrar
		JMenuItem Cadastrar = new JMenuItem("Cadastrar");
		Cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos(); // Abre limpo para novo cadastro
				cl.show(cadastro, "telaFormulario");
			}
		});
		mnNewMenu.add(Cadastrar);
		
		// 2. Novo item Alterar (que agora exibe a lista para selecionar quem alterar)
		JMenuItem Alterar = new JMenuItem("Alterar");
		Alterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabela(tabelaAlterar);
				cl.show(cadastro, "telaAlterarLista");
			}
		});
		mnNewMenu.add(Alterar);
		
		JMenuItem Consultar = new JMenuItem("Consultar");
		Consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabela(tabelaConsultar);
				cl.show(cadastro, "telaConsultarLista");
			}
		});
		mnNewMenu.add(Consultar);
		
		JMenuItem Excluir = new JMenuItem("Excluir");
		Excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabela(tabelaExcluir);
				cl.show(cadastro, "telaExcluirLista");
			}
		});
		mnNewMenu.add(Excluir);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Salvar");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Sair");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmNewMenuItem_4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem_4);
		
		
		tabelaConsultar = criarPainelComTabela("telaConsultarLista");
		tabelaExcluir = criarPainelComTabela("telaExcluirLista");
		tabelaAlterar = criarPainelComTabela("telaAlterarLista");
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		cadastro.add(tabbedPane, "telaFormulario"); 
		
		JPanel DadosPessoais = new JPanel();
		tabbedPane.addTab("Dados Pessoais", null, DadosPessoais, null);
		DadosPessoais.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("RGM");
		lblNewLabel_8.setBounds(10, 23, 51, 20);
		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 20));
		DadosPessoais.add(lblNewLabel_8);
		
		txtRGM = new JTextField();
		txtRGM.setBounds(104, 19, 226, 30);
		txtRGM.setFont(new Font("Arial", Font.PLAIN, 20));
		txtRGM.setColumns(10);
		DadosPessoais.add(txtRGM);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome");
		lblNewLabel_1_1.setBounds(346, 26, 59, 14);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		DadosPessoais.add(lblNewLabel_1_1);
		
		txtNome = new JTextField();
		txtNome.setBounds(415, 19, 403, 30);
		txtNome.setFont(new Font("Arial", Font.PLAIN, 20));
		txtNome.setColumns(10);
		DadosPessoais.add(txtNome);
		
		JLabel lblNewLabel_2_1 = new JLabel("Data de Nascimento");
		lblNewLabel_2_1.setBounds(10, 100, 183, 19);
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 20));
		DadosPessoais.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("CPF");
		lblNewLabel_3_1.setBounds(395, 103, 46, 17);
		lblNewLabel_3_1.setFont(new Font("Arial", Font.PLAIN, 20));
		DadosPessoais.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Email");
		lblNewLabel_4_1.setBounds(10, 175, 51, 20);
		lblNewLabel_4_1.setFont(new Font("Arial", Font.PLAIN, 20));
		DadosPessoais.add(lblNewLabel_4_1);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(104, 171, 714, 30);
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		DadosPessoais.add(txtEmail);
		
		JLabel lblNewLabel_5_1 = new JLabel("Endereço");
		lblNewLabel_5_1.setBounds(10, 256, 84, 17);
		lblNewLabel_5_1.setFont(new Font("Arial", Font.PLAIN, 20));
		DadosPessoais.add(lblNewLabel_5_1);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(104, 250, 549, 30);
		txtEndereco.setFont(new Font("Arial", Font.PLAIN, 20));
		txtEndereco.setColumns(10);
		DadosPessoais.add(txtEndereco);
		
		JLabel lblNewLabel_6_1 = new JLabel("Município");
		lblNewLabel_6_1.setBounds(10, 401, 84, 21);
		lblNewLabel_6_1.setFont(new Font("Arial", Font.PLAIN, 20));
		DadosPessoais.add(lblNewLabel_6_1);
		
		txtMunicipio = new JTextField();
		txtMunicipio.setBounds(104, 396, 442, 30);
		txtMunicipio.setFont(new Font("Arial", Font.PLAIN, 20));
		txtMunicipio.setColumns(10);
		DadosPessoais.add(txtMunicipio);
		
		JLabel lblNewLabel_7_1 = new JLabel("UF");
		lblNewLabel_7_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_7_1.setBounds(709, 102, 27, 20);
		DadosPessoais.add(lblNewLabel_7_1);
		
		UF = new JComboBox<String>();
		UF.setModel(new DefaultComboBoxModel<>(new String[] {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"}));
		UF.setFont(new Font("Arial", Font.PLAIN, 20));
		UF.setBounds(747, 96, 71, 31);
		DadosPessoais.add(UF);
		
		txtDataNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataNasc.setFont(new Font("Arial", Font.PLAIN, 20));
		txtDataNasc.setBounds(203, 97, 127, 30);
		DadosPessoais.add(txtDataNasc);
		
		txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCPF.setFont(new Font("Arial", Font.PLAIN, 20));
		txtCPF.setBounds(451, 98, 206, 29);
		DadosPessoais.add(txtCPF);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("Telefone");
		lblNewLabel_7_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_7_1_1.setBounds(556, 403, 77, 18);
		DadosPessoais.add(lblNewLabel_7_1_1);
		
		txtTelefone = new JFormattedTextField(new MaskFormatter("(##) ####-####"));
		txtTelefone.setFont(new Font("Arial", Font.PLAIN, 20));
		txtTelefone.setBounds(643, 396, 175, 30);
		DadosPessoais.add(txtTelefone);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(104, 324, 84, 30);
		DadosPessoais.add(textField_2);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Número");
		lblNewLabel_5_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_5_1_1.setBounds(10, 330, 84, 17);
		DadosPessoais.add(lblNewLabel_5_1_1);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Arial", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(713, 250, 115, 30);
		DadosPessoais.add(textField_3);
		
		JLabel lblNewLabel_5_1_2 = new JLabel("CEP");
		lblNewLabel_5_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_5_1_2.setBounds(663, 256, 40, 17);
		DadosPessoais.add(lblNewLabel_5_1_2);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Arial", Font.PLAIN, 20));
		textField_4.setColumns(10);
		textField_4.setBounds(334, 324, 484, 30);
		DadosPessoais.add(textField_4);
		
		JLabel lblNewLabel_5_1_1_1 = new JLabel("Complemento");
		lblNewLabel_5_1_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_5_1_1_1.setBounds(203, 330, 121, 17);
		DadosPessoais.add(lblNewLabel_5_1_1_1);
		
		JPanel Curso = new JPanel();
		tabbedPane.addTab("Curso", null, Curso, null);
		Curso.setLayout(null);
		
		JLabel lblNewLabel_7_1_2 = new JLabel("Curso");
		lblNewLabel_7_1_2.setBounds(10, 39, 72, 24);
		lblNewLabel_7_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
		Curso.add(lblNewLabel_7_1_2);
		
		JComboBox<String> UF_1 = new JComboBox<String>();
		UF_1.setModel(new DefaultComboBoxModel<>(new String[] {"Ciência da Computação", "Engenharia Civil / Engenharia de Software", "Direito", "Administração", "Medicina / Psicologia", "Sistemas de Informação"}));
		UF_1.setBounds(92, 35, 726, 32);
		UF_1.setFont(new Font("Arial", Font.PLAIN, 20));
		Curso.add(UF_1);
		
		JLabel lblNewLabel_7_1_2_1 = new JLabel("Período");
		lblNewLabel_7_1_2_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_7_1_2_1.setBounds(10, 182, 72, 24);
		Curso.add(lblNewLabel_7_1_2_1);
		
		JLabel lblNewLabel_7_1_2_2 = new JLabel("Campus");
		lblNewLabel_7_1_2_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_7_1_2_2.setBounds(10, 107, 72, 24);
		Curso.add(lblNewLabel_7_1_2_2);
		
		JComboBox<String> UF_1_2 = new JComboBox<String>();
		UF_1_2.setModel(new DefaultComboBoxModel<>(new String[] {"UNICID", "CESUCA", "UP", "UNIPE", "FSG", "FASS", "UDF"}));
		UF_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
		UF_1_2.setBounds(92, 103, 726, 32);
		Curso.add(UF_1_2);
		
		ButtonGroup grupoPeriodo = new ButtonGroup();

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Matutino");
		rdbtnNewRadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
		rdbtnNewRadioButton.setBounds(151, 184, 129, 23);
		Curso.add(rdbtnNewRadioButton);
		grupoPeriodo.add(rdbtnNewRadioButton);

		JRadioButton rdbtnVespertino = new JRadioButton("Vespertino");
		rdbtnVespertino.setFont(new Font("Arial", Font.PLAIN, 18));
		rdbtnVespertino.setBounds(403, 184, 129, 23);
		Curso.add(rdbtnVespertino);
		grupoPeriodo.add(rdbtnVespertino);

		JRadioButton rdbtnNewRadioButton_1_1 = new JRadioButton("Noturno");
		rdbtnNewRadioButton_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		rdbtnNewRadioButton_1_1.setBounds(675, 184, 129, 23);
		Curso.add(rdbtnNewRadioButton_1_1);
		grupoPeriodo.add(rdbtnNewRadioButton_1_1);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setFont(new Font("Arial", Font.PLAIN, 18));
		btnEnviar.setBounds(10, 276, 808, 30);
		Curso.add(btnEnviar);
		
		JPanel NotasFaltas = new JPanel();
		tabbedPane.addTab("Notas e Faltas", null, NotasFaltas, null);
		NotasFaltas.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("RGM");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 46, 30);
		NotasFaltas.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 18));
		textField.setBounds(94, 8, 173, 33);
		NotasFaltas.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(287, 11, 49, 30);
		NotasFaltas.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_1.setBounds(346, 8, 472, 33);
		NotasFaltas.add(textField_1);
		textField_1.setColumns(10);
		
		txtCurso = new JTextField();
		txtCurso.setEditable(false);
		txtCurso.setFont(new Font("Arial", Font.PLAIN, 18));
		txtCurso.setBounds(10, 76, 808, 33);
		NotasFaltas.add(txtCurso);
		txtCurso.setColumns(10);
		
		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDisciplina.setBounds(10, 156, 77, 30);
		NotasFaltas.add(lblDisciplina);
		
		JComboBox<String> UF_2 = new JComboBox<String>();
		UF_2.setFont(new Font("Arial", Font.PLAIN, 20));
		UF_2.setBounds(97, 156, 721, 31);
		NotasFaltas.add(UF_2);
		
		JLabel aaaa = new JLabel("Semestre");
		aaaa.setFont(new Font("Arial", Font.PLAIN, 18));
		aaaa.setBounds(10, 233, 77, 30);
		NotasFaltas.add(aaaa);
		
		JLabel lblNota = new JLabel("Nota");
		lblNota.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNota.setBounds(328, 233, 46, 30);
		NotasFaltas.add(lblNota);
		
		JLabel lblFaltas = new JLabel("Faltas");
		lblFaltas.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFaltas.setBounds(590, 233, 55, 30);
		NotasFaltas.add(lblFaltas);
		
		JComboBox<String> UF_2_1 = new JComboBox<String>();
		UF_2_1.setModel(new DefaultComboBoxModel(new String[] {"1º", "2º"}));
		UF_2_1.setFont(new Font("Arial", Font.PLAIN, 20));
		UF_2_1.setBounds(100, 232, 130, 31);
		NotasFaltas.add(UF_2_1);
		
		JComboBox<String> UF_2_1_1 = new JComboBox<String>();
		UF_2_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		UF_2_1_1.setBounds(384, 232, 130, 31);
		NotasFaltas.add(UF_2_1_1);
		
		txtFaltas = new JTextField();
		txtFaltas.setFont(new Font("Arial", Font.PLAIN, 18));
		txtFaltas.setColumns(10);
		txtFaltas.setBounds(655, 232, 163, 33);
		NotasFaltas.add(txtFaltas);
		
		JComboBox<String> UF_2_1_1_1 = new JComboBox<String>();
		UF_2_1_1_1.setModel(new DefaultComboBoxModel(new String[] {"2025", "2026"}));
		UF_2_1_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		UF_2_1_1_1.setBounds(100, 274, 132, 31);
		NotasFaltas.add(UF_2_1_1_1);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAno.setBounds(10, 275, 46, 30);
		NotasFaltas.add(lblAno);
	}

	private JTable criarPainelComTabela(String nomeCard) {
		JPanel painel = new JPanel(new BorderLayout());
		String[] colunas = {"RGM", "Nome"};
		DefaultTableModel model = new DefaultTableModel(colunas, 0) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) { return false; } 
		};
		
		JTable tabela = new JTable(model);
		tabela.setFont(new Font("Arial", Font.PLAIN, 16));
		tabela.setRowHeight(24);
		
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { 
					int linhaSelecionada = tabela.getSelectedRow();
					if (linhaSelecionada != -1) {
						
						// Se o clique acontecer na tela de EXCLUIR
						if (nomeCard.equals("telaExcluirLista")) {
							// Pergunta se o usuário tem certeza
							int resposta = JOptionPane.showConfirmDialog(
								null, 
								"Deseja realmente excluir este aluno?", 
								"Confirmar Exclusão", 
								JOptionPane.YES_NO_OPTION
							);
							
							if (resposta == JOptionPane.YES_OPTION) {
								// Remove da nossa lista dinâmica (Arraylist)
								listaAlunos.remove(linhaSelecionada);
								
								// Atualiza a tabela visual na hora para sumir o nome
								atualizarTabela(tabela);
								
								JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
							}
						} else {
							// Se for Consultar ou Alterar, mantém o comportamento de abrir o formulário
							Aluno selecionado = listaAlunos.get(linhaSelecionada);
							preencherFormulario(selecionado);
							cl.show(cadastro, "telaFormulario"); 
						}
						
					}
				}
			}
		});
		
		JScrollPane scroll = new JScrollPane(tabela);
		painel.add(scroll, BorderLayout.CENTER);
		
		// Ajusta o aviso do rodapé dependendo da tela
		String textoAviso = nomeCard.equals("telaExcluirLista") ? 
			"Dê um duplo clique sobre o aluno para EXCLUÍ-LO permanentemente" :
			"Dê um duplo clique sobre o aluno para ver e gerenciar os detalhes";
			
		JLabel lblAviso = new JLabel(textoAviso, SwingConstants.CENTER);
		lblAviso.setFont(new Font("Arial", Font.ITALIC, 14));
		painel.add(lblAviso, BorderLayout.SOUTH);
		
		cadastro.add(painel, nomeCard);
		return tabela;
	}

	//Atualiza visualmente as tabelas com os dados atualizados
	
	private void atualizarTabela(JTable tabela) {
		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		model.setRowCount(0); // Limpa registros antigos
		for (Aluno a : listaAlunos) {
			model.addRow(new Object[]{a.rgm, a.nome});
		}
	}

	//Transfere os dados do objeto Aluno para os campos de texto do formulário principal
	
	private void preencherFormulario(Aluno a) {
		txtRGM.setText(a.rgm);
		txtNome.setText(a.nome);
		txtDataNasc.setText(a.dataNasc);
		txtCPF.setText(a.cpf);
		txtEmail.setText(a.email);
		txtEndereco.setText(a.endereco);
		txtMunicipio.setText(a.municipio);
		UF.setSelectedItem(a.uf);
		txtTelefone.setText(a.telefone);
	}

	//Limpa o formulário quando o usuário clicar em cadastrar
	
	private void limparCampos() {
		txtRGM.setText("");
		txtNome.setText("");
		txtDataNasc.setValue(null);
		txtCPF.setValue(null);
		txtEmail.setText("");
		txtEndereco.setText("");
		txtMunicipio.setText("");
		UF.setSelectedIndex(0);
		txtTelefone.setValue(null);
	}
}