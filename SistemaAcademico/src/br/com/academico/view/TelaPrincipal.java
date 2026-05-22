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
import javax.swing.JTextArea; 
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
import java.util.Enumeration;
import javax.swing.AbstractButton;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel cadastro; 
	private CardLayout cl;   
	private JFormattedTextField txtRGM; 
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtMunicipio;
	private JFormattedTextField txtDataNasc;
	private JFormattedTextField txtCPF;
	private JFormattedTextField txtTelefone;
	private JComboBox<String> UF;
	private JComboBox<String> comboCursos; 
	private JComboBox<String> comboCampus; 
	private ButtonGroup grupoPeriodo;
	private JMenuItem mntmNewMenuItem; 
	
	private JTextField textField; // RGM de Notas e Faltas
	private JTextField textField_1; // Nome em Notas e Faltas
	private JTextField txtCurso; // Curso em Notas e Faltas
	private JTable tabelaConsultar;
	private JTable tabelaExcluir;
	private JTable tabelaAlterar;
	
	private List<Aluno> listaAlunos = new ArrayList<>();
	private Aluno alunoSendoEditado = null; 
	
	private JTextField txtFaltas;
	private JTextField textField_2; // Número
	private JFormattedTextField textField_3; // CEP
	private JTextField textField_4; // Complemento

	// COMPONENTES DO BOLETIM TORNADOS ATRIBUTOS DA CLASSE
	private JTabbedPane tabbedPane;
	private JPanel Boletim;
	private JTextArea txtAreaBoletim;

	// Classe Aluno atualizada com as variáveis que faltavam
	class Aluno {
	    String rgm, nome, dataNasc, cpf, email, endereco, numero, cep, complemento, municipio, uf, telefone, curso, campus;
	    
	    // Atributos acadêmicos
	    String disciplina, semestre, ano, nota, faltas;
	    
	    Aluno(String rgm, String nome, String dataNasc, String cpf, String email, String endereco, String numero, String cep, String complemento, String municipio, String uf, String telefone, String curso, String campus) {
	        this.rgm = rgm; 
	        this.nome = nome; 
	        this.dataNasc = dataNasc; 
	        this.cpf = cpf;
	        this.email = email; 
	        this.endereco = endereco; 
	        this.numero = numero;
	        this.cep = cep;
	        this.complemento = complemento;
	        this.municipio = municipio; 
	        this.uf = uf; 
	        this.telefone = telefone;
	        this.curso = curso; 
	        this.campus = campus;
	        
	        this.disciplina = "";
	        this.semestre = "";
	        this.ano = "";
	        this.nota = "";
	        this.faltas = "";
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
		// Aluno padrão atualizado para bater com o novo construtor
		listaAlunos.add(new Aluno("12345678", "Michel Mendes", "10/05/2004", "111.222.333-44", "michel@email.com", "Rua A", "123", "01234-567", "Apto 45", "São Paulo", "SP", "(11) 99999-9999", "Ciência da Computação", "UNICID"));

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
		
		JMenuItem Cadastrar = new JMenuItem("Cadastrar");
		Cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos(); 
				alunoSendoEditado = null; 
				setCamposEditaveis(true); 
				gerenciarBoletim(null, false); 
				cl.show(cadastro, "telaFormulario");
			}
		});
		mnNewMenu.add(Cadastrar);
		
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
		
		mntmNewMenuItem = new JMenuItem("Salvar");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String rgm = txtRGM.getText().trim();
		        String nome = txtNome.getText().trim();
		        String dataNasc = txtDataNasc.getText().trim();
		        String cpf = txtCPF.getText().trim();
		        String email = txtEmail.getText().trim();
		        String endereco = txtEndereco.getText().trim();
		        String numero = textField_2.getText().trim(); // Resgatando Número
		        String cep = textField_3.getText().trim();    // Resgatando CEP
		        String complemento = textField_4.getText().trim(); // Resgatando Complemento
		        String municipio = txtMunicipio.getText().trim();
		        String ufSelected = (String) UF.getSelectedItem();
		        String telefone = txtTelefone.getText().trim();
		        
		        String cursoSelected = (String) comboCursos.getSelectedItem();
		        String campusSelected = (String) comboCampus.getSelectedItem(); // Resgatando Campus
		        
		        boolean dataVazia = dataNasc.replace("/", "").trim().isEmpty();
		        boolean cpfVazio = cpf.replace(".", "").replace("-", "").trim().isEmpty();
		        boolean cepVazio = cep.replace("-", "").trim().isEmpty();
		        boolean telefoneVazio = telefone.replace("(", "").replace(")", "").replace("-", "").replace(" ", "").trim().isEmpty();
		        
		        if (endereco.isEmpty() || cepVazio || nome.isEmpty() || municipio.isEmpty() || cpfVazio || dataVazia || telefoneVazio) {
		            JOptionPane.showMessageDialog(
		                null, 
		                "Todos os campo obrigatorios tem que serem preenchidos: \nLogradouro, CEP, Nome, Município, CPF, Data de Nascimento e Telefone.", 
		                "Campos Obrigatórios", 
		                JOptionPane.WARNING_MESSAGE
		            );
		            return; 
		        }
		        
		        if (alunoSendoEditado != null) {
		            alunoSendoEditado.rgm = rgm;
		            alunoSendoEditado.nome = nome;
		            alunoSendoEditado.dataNasc = dataNasc;
		            alunoSendoEditado.cpf = cpf;
		            alunoSendoEditado.email = email;
		            alunoSendoEditado.endereco = endereco;
		            alunoSendoEditado.numero = numero; // Salvando alteração de Número
		            alunoSendoEditado.cep = cep;       // Salvando alteração de CEP
		            alunoSendoEditado.complemento = complemento; // Salvando alteração de Complemento
		            alunoSendoEditado.municipio = municipio;
		            alunoSendoEditado.uf = ufSelected;
		            alunoSendoEditado.telefone = telefone;
		            alunoSendoEditado.curso = cursoSelected;
		            alunoSendoEditado.campus = campusSelected; // Salvando alteração de Campus
		            
		            JOptionPane.showMessageDialog(null, "Dados do aluno " + nome + " alterados com sucesso!");
		            alunoSendoEditado = null; 
		        } else {
		            Aluno novoAluno = new Aluno(rgm, nome, dataNasc, cpf, email, endereco, numero, cep, complemento, municipio, ufSelected, telefone, cursoSelected, campusSelected);
		            listaAlunos.add(novoAluno);
		            JOptionPane.showMessageDialog(null, "Aluno " + nome + " cadastrado com sucesso!");
		        }
		        
		        limparCampos();
		        comboCursos.setSelectedIndex(0);
		        comboCampus.setSelectedIndex(0);
		        grupoPeriodo.clearSelection();
		        
		        tabbedPane.setSelectedIndex(0);
		    }
		});
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
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP); 
		cadastro.add(tabbedPane, "telaFormulario"); 
		
		JPanel DadosPessoais = new JPanel();
		tabbedPane.addTab("Dados Pessoais", null, DadosPessoais, null);
		DadosPessoais.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("RGM");
		lblNewLabel_8.setBounds(10, 23, 51, 20);
		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 20));
		DadosPessoais.add(lblNewLabel_8);
		
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
		
		UF = new JComboBox<>();
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
		
		textField_3 = new JFormattedTextField(new MaskFormatter("#####-###"));
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
		
		txtRGM = new JFormattedTextField(new MaskFormatter("########")); 
		txtRGM.setFont(new Font("Arial", Font.PLAIN, 20));
		txtRGM.setBounds(104, 19, 226, 30);
		DadosPessoais.add(txtRGM);
		
		JPanel Curso = new JPanel();
		tabbedPane.addTab("Curso", null, Curso, null);
		Curso.setLayout(null);
		
		JLabel lblNewLabel_7_1_2 = new JLabel("Curso");
		lblNewLabel_7_1_2.setBounds(10, 39, 72, 24);
		lblNewLabel_7_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
		Curso.add(lblNewLabel_7_1_2);
		
		comboCursos = new JComboBox<String>(); 
		comboCursos.setModel(new DefaultComboBoxModel<>(new String[] {"Ciência da Computação", "Engenharia Civil / Engenharia de Software", "Direito", "Administração", "Medicina / Psicologia", "Sistemas de Informação"}));
		comboCursos.setBounds(92, 35, 726, 32);
		comboCursos.setFont(new Font("Arial", Font.PLAIN, 20));
		Curso.add(comboCursos);
		
		JLabel lblNewLabel_7_1_2_1 = new JLabel("Período");
		lblNewLabel_7_1_2_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_7_1_2_1.setBounds(10, 182, 72, 24);
		Curso.add(lblNewLabel_7_1_2_1);
		
		JLabel lblNewLabel_7_1_2_2 = new JLabel("Campus");
		lblNewLabel_7_1_2_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_7_1_2_2.setBounds(10, 107, 72, 24);
		Curso.add(lblNewLabel_7_1_2_2);
		
		comboCampus = new JComboBox<String>(); 
		comboCampus.setModel(new DefaultComboBoxModel<>(new String[] {"UNICID", "CESUCA", "UP", "UNIPE", "FSG", "FASS", "UDF"}));
		comboCampus.setFont(new Font("Arial", Font.PLAIN, 20));
		comboCampus.setBounds(92, 103, 726, 32);
		Curso.add(comboCampus);
		
		grupoPeriodo = new ButtonGroup(); 

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
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rgmProcurado = textField.getText().trim();
				Aluno encontrado = null;
				
				for (Aluno a : listaAlunos) {
					if (a.rgm.equals(rgmProcurado)) {
						encontrado = a;
						break;
					}
				}
				
				if (encontrado != null) {
					textField_1.setText(encontrado.nome);
					txtCurso.setText(encontrado.curso);
				} else {
					JOptionPane.showMessageDialog(null, "Aluno com RGM " + rgmProcurado + " não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
					textField_1.setText("");
					txtCurso.setText("");
				}
			}
		});
		
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
		
		JComboBox<String> boxDisciplina = new JComboBox<String>();
		boxDisciplina.setModel(new DefaultComboBoxModel<>(new String[] {"Algoritmos e Estruturas de Dados", "Programação Orientada a Objetos", "Cálculo Diferencial e Integral", "Álgebra Linear", "Matemática Discreta", "Organização e Arquitetura de Computadores", "Sistemas Operacionais", "Redes de Computadores", "Banco de Dados", "Engenharia de Software"}));
		boxDisciplina.setFont(new Font("Arial", Font.PLAIN, 20));
		boxDisciplina.setBounds(97, 156, 721, 31);
		NotasFaltas.add(boxDisciplina);
		
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
		
		JComboBox<String> boxSemestre = new JComboBox<String>();
		boxSemestre.setModel(new DefaultComboBoxModel<>(new String[] {"1º", "2º"}));
		boxSemestre.setFont(new Font("Arial", Font.PLAIN, 20));
		boxSemestre.setBounds(100, 232, 130, 31);
		NotasFaltas.add(boxSemestre);
		
		JComboBox<String> boxNota = new JComboBox<String>();
		boxNota.setModel(new DefaultComboBoxModel<>(new String[] {"0,0", "0,5", "1,0", "1,5", "2,0", "2,5", "3,0", "3,5", "4,0", "4,5", "5,0"}));
		boxNota.setFont(new Font("Arial", Font.PLAIN, 20));
		boxNota.setBounds(384, 232, 130, 31);
		NotasFaltas.add(boxNota);
		
		txtFaltas = new JTextField();
		txtFaltas.setFont(new Font("Arial", Font.PLAIN, 18));
		txtFaltas.setColumns(10);
		txtFaltas.setBounds(655, 232, 163, 33);
		NotasFaltas.add(txtFaltas);
		
		JComboBox<String> boxAno = new JComboBox<String>();
		boxAno.setModel(new DefaultComboBoxModel<>(new String[] {"2025", "2026"}));
		boxAno.setFont(new Font("Arial", Font.PLAIN, 20));
		boxAno.setBounds(100, 274, 132, 31);
		NotasFaltas.add(boxAno);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAno.setBounds(10, 275, 46, 30);
		NotasFaltas.add(lblAno);
		
		JButton btnEnviarNota = new JButton("Enviar");
		btnEnviarNota.setFont(new Font("Arial", Font.PLAIN, 18));
		btnEnviarNota.setBounds(10, 367, 808, 30);
		NotasFaltas.add(btnEnviarNota);
		
		btnEnviarNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rgmProcurado = textField.getText().trim();
				Aluno encontrado = null;
				
				for (Aluno a : listaAlunos) {
					if (a.rgm.equals(rgmProcurado)) {
						encontrado = a;
						break;
					}
				}
				
				if (encontrado != null) {
					encontrado.disciplina = (String) boxDisciplina.getSelectedItem();
					encontrado.semestre = (String) boxSemestre.getSelectedItem();
					encontrado.ano = (String) boxAno.getSelectedItem();
					encontrado.nota = (String) boxNota.getSelectedItem();
					encontrado.faltas = txtFaltas.getText().trim();
					
					JOptionPane.showMessageDialog(null, "Notas e faltas gravadas com sucesso para o aluno: " + encontrado.nome);
					
					textField.setText("");
					textField_1.setText("");
					txtCurso.setText("");
					txtFaltas.setText("");
					boxDisciplina.setSelectedIndex(0);
					boxSemestre.setSelectedIndex(0);
					boxNota.setSelectedIndex(0);
					boxAno.setSelectedIndex(0);
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, digite um RGM válido e aperte Enter antes de salvar.", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		// CONFIGURAÇÃO DO PAINEL DO BOLETIM 
		Boletim = new JPanel();
		Boletim.setLayout(new BorderLayout());
		
		txtAreaBoletim = new JTextArea();
		txtAreaBoletim.setEditable(false);
		txtAreaBoletim.setFont(new Font("Monospaced", Font.PLAIN, 16)); 
		txtAreaBoletim.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		JScrollPane scrollBoletim = new JScrollPane(txtAreaBoletim);
		Boletim.add(scrollBoletim, BorderLayout.CENTER);
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
						if (nomeCard.equals("telaExcluirLista")) {
							int resposta = JOptionPane.showConfirmDialog(
								null, 
								"Deseja realmente excluir este aluno?", 
								"Confirmar Exclusão", 
								JOptionPane.YES_NO_OPTION
							);
							
							if (resposta == JOptionPane.YES_OPTION) {
								listaAlunos.remove(linhaSelecionada);
								atualizarTabela(tabela);
								JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
							}
						} else {
							Aluno selecionado = listaAlunos.get(linhaSelecionada);
							
							if (nomeCard.equals("telaConsultarLista")) {
								setCamposEditaveis(false);
								alunoSendoEditado = null; 
								gerenciarBoletim(selecionado, true); 
							} else if (nomeCard.equals("telaAlterarLista")) {
								setCamposEditaveis(true);
								alunoSendoEditado = selecionado; 
								gerenciarBoletim(null, false); 
							}
							
							preencherFormulario(selecionado);
							cl.show(cadastro, "telaFormulario"); 
						}
					}
				}
			}
		});
		
		JScrollPane scroll = new JScrollPane(tabela);
		painel.add(scroll, BorderLayout.CENTER);
		
		String textoAviso = nomeCard.equals("telaExcluirLista") ? 
			"Dê um duplo clique sobre o aluno para EXCLUÍ-LO permanentemente" :
			"Dê um duplo clique sobre o aluno para ver os detalhes";
			
		JLabel lblAviso = new JLabel(textoAviso, SwingConstants.CENTER);
		lblAviso.setFont(new Font("Arial", Font.ITALIC, 14));
		painel.add(lblAviso, BorderLayout.SOUTH);
		
		cadastro.add(painel, nomeCard);
		return tabela;
	}
	
	private void atualizarTabela(JTable tabela) {
		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		model.setRowCount(0); 
		for (Aluno a : listaAlunos) {
			model.addRow(new Object[]{a.rgm, a.nome});
		}
	}
	
	// Preenchimento de formulário corrigido para carregar os campos novos na tela
	private void preencherFormulario(Aluno a) {
		txtRGM.setText(a.rgm);
		txtNome.setText(a.nome);
		txtDataNasc.setText(a.dataNasc);
		txtCPF.setText(a.cpf);
		txtEmail.setText(a.email);
		txtEndereco.setText(a.endereco);
		textField_2.setText(a.numero);       // Exibe o Número salvo
		textField_3.setText(a.cep);          // Exibe o CEP salvo
		textField_4.setText(a.complemento);  // Exibe o Complemento salvo
		txtMunicipio.setText(a.municipio);
		UF.setSelectedItem(a.uf);
		txtTelefone.setText(a.telefone);
		comboCursos.setSelectedItem(a.curso);
		comboCampus.setSelectedItem(a.campus); // Exibe o Campus salvo
	}
	
	private void limparCampos() {
		txtRGM.setValue(null);
		txtNome.setText("");
		txtDataNasc.setValue(null);
		txtCPF.setValue(null);
		txtEmail.setText("");
		txtEndereco.setText("");
		txtMunicipio.setText("");
		UF.setSelectedIndex(0);
		txtTelefone.setValue(null);
		textField_2.setText("");
		textField_3.setValue(null);
		textField_4.setText("");
	}

	private void setCamposEditaveis(boolean editavel) {
		txtRGM.setEditable(editavel);
		txtNome.setEditable(editavel);
		txtDataNasc.setEditable(editavel);
		txtCPF.setEditable(editavel);
		txtEmail.setEditable(editavel);
		txtEndereco.setEditable(editavel);
		txtMunicipio.setEditable(editavel);
		txtTelefone.setEditable(editavel);
		textField_2.setEditable(editavel);
		textField_3.setEditable(editavel);
		textField_4.setEditable(editavel);
		
		UF.setEnabled(editavel);
		comboCursos.setEnabled(editavel);
		comboCampus.setEnabled(editavel);
		
		mntmNewMenuItem.setEnabled(editavel); 
		
		Enumeration<AbstractButton> botoes = grupoPeriodo.getElements();
		while (botoes.hasMoreElements()) {
			botoes.nextElement().setEnabled(editavel);
		}
	}

	// Método de exibição do boletim atualizado com os novos dados estruturados
	private void gerenciarBoletim(Aluno aluno, boolean mostrar) {
		if (mostrar && aluno != null) {
			if (tabbedPane.indexOfComponent(Boletim) == -1) {
				tabbedPane.addTab("Boletim", null, Boletim, null);
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("  RGM:                 ").append(aluno.rgm).append("\n");
			sb.append("  Nome Completo:       ").append(aluno.nome).append("\n");
			sb.append("  Data de Nascimento:  ").append(aluno.dataNasc).append("\n");
			sb.append("  CPF:                 ").append(aluno.cpf).append("\n");
			sb.append("  E-mail:              ").append(aluno.email).append("\n");
			sb.append("  Telefone:            ").append(aluno.telefone).append("\n\n");
			
			sb.append("  Logradouro:          ").append(aluno.endereco).append(", Nº ").append(aluno.numero.isEmpty() ? "S/N" : aluno.numero).append("\n");
			sb.append("  Complemento:         ").append(aluno.complemento.isEmpty() ? "-" : aluno.complemento).append("\n");
			sb.append("  CEP:                 ").append(aluno.cep).append("\n");
			sb.append("  Município/UF:        ").append(aluno.municipio).append(" - ").append(aluno.uf).append("\n\n");

			sb.append("  Campus:              ").append(aluno.campus).append("\n\n");

			sb.append("  Disciplina:          ").append(aluno.disciplina.isEmpty() ? "Não informada" : aluno.disciplina).append("\n");
			sb.append("  Semestre / Ano:      ").append(aluno.semestre.isEmpty() ? "-" : aluno.semestre).append(" / ").append(aluno.ano.isEmpty() ? "-" : aluno.ano).append("\n");
			sb.append("  Nota Atribuída:      ").append(aluno.nota.isEmpty() ? "Sem Nota" : aluno.nota).append("\n");
			sb.append("  Faltas Acumuladas:   ").append(aluno.faltas.isEmpty() ? "0" : aluno.faltas).append("\n");
			
			txtAreaBoletim.setText(sb.toString());
		} else {
			if (tabbedPane.indexOfComponent(Boletim) != -1) {
				tabbedPane.remove(Boletim);
			}
		}
	}
}