package br.com.academico.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.academico.dao.EnderecoDAO;
import br.com.academico.model.Endereco;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel cadastro; 
	private CardLayout cl;   
	
	// Campos da aba de Cadastro Geral
	private JFormattedTextField txtRGM; 
	private JTextField txtNome;
	private JTextField txtEmail;
	private JFormattedTextField txtCPF;
	private JFormattedTextField txtDataNasc;
	private JFormattedTextField txtTelefone;

	// Campos de Endereço (Aba Cadastro)
	private JTextField txtEndereco;
	private JTextField txtNumero;       
	private JFormattedTextField txtCEP;  
	private JTextField txtComplemento; 
	private JTextField txtMunicipio;
	private JComboBox<String> UF;
	
	// Campos de Curso/Campus (Aba Curso)
	private JComboBox<String> comboCursos; 
	private JComboBox<String> comboCampus; 
	private JComboBox<String> comboTipoCurso; 
	private ButtonGroup grupoPeriodo;;
	private JMenuItem mntmNewMenuItem; 
	
	// Campos da aba de Notas e Faltas
	private JTextField txtRgmNotas;    
	private JTextField txtNomeNotas;   
	private JTextField txtCurso; 
	private JTextField txtFaltas;
	private JTextField txtNotaField; 
	private JComboBox<String> boxSemestre;
	private JComboBox<String> boxDisciplina;
	
	// Tabelas de Listagem (Cards)
	private JTable tabelaConsultar;
	private JTable tabelaExcluir;
	private JTable tabelaAlterar;
	
	// Controle de Dados Internos (Simulação/Cache)
	private List<Aluno> listaAlunos = new ArrayList<>();
	private Aluno alunoSendoEditado = null; 

	// Componentes Dinâmicos do Boletim
	private JTabbedPane tabbedPane;
	private JPanel Boletim;
	private JTextArea txtAreaBoletim;
	protected ButtonGroup grupoModalidade;

	// Classe Aluno
	class Aluno {
	    String rgm, nome, dataNasc, cpf, email, endereco, numero, cep, complemento, municipio, uf, telefone, curso, campus, tipoCurso;
	    String disciplina, semestre, nota, faltas;
	    
	    Aluno(String rgm, String nome, String dataNasc, String cpf, String email, String endereco, String numero, String cep, String complemento, String municipio, String uf, String telefone, String curso, String campus, String tipoCurso) {
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
	        this.tipoCurso = tipoCurso; 
	        
	        this.disciplina = "";
	        this.semestre = "";
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
		// Aluno padrão de testes
		listaAlunos.add(new Aluno("12345678", "Michel Mendes", "10/05/2004", "111.222.333-44", "michel@email.com", "Rua A", "123", "01234-567", "Apto 45", "São Paulo", "SP", "(11)99999-9999", "Ciência da Computação", "UNICID", "Presencial"));

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
				gerenciarBoletim(null); 
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
		        // 1. Resgatando os dados e limpando os espaços extras
		        String rgm = txtRGM.getText().trim();
		        String nome = txtNome.getText().trim();
		        String dataNasc = txtDataNasc.getText().trim();
		        String cpf = txtCPF.getText().trim();
		        String email = txtEmail.getText().trim();
		        String endereco = txtEndereco.getText().trim();
		        String numero = txtNumero.getText().trim();       
		        String cep = txtCEP.getText().trim();             
		        String complemento = txtComplemento.getText().trim(); 
		        String municipio = txtMunicipio.getText().trim();
		        String ufSelected = (String) UF.getSelectedItem();
		        String telefone = txtTelefone.getText().trim();
		        
		        String cursoSelected = (String) comboCursos.getSelectedItem();
		        String campusSelected = (String) comboCampus.getSelectedItem(); 
		        
		        String tipoCursoSelected = "";
		        if (grupoModalidade.getSelection() != null) {
		            tipoCursoSelected = grupoModalidade.getSelection().getActionCommand();
		        }
		        
		        // Extraindo o texto "limpo" das máscaras (sem pontuação) para validar o tamanho real
		        String dataLimpa = dataNasc.replace("/", "").trim();
		        String cpfLimpo = cpf.replace(".", "").replace("-", "").trim();
		        String cepLimpo = cep.replace("-", "").trim();
		        String telefoneLimpo = telefone.replace("(", "").replace(")", "").replace("-", "").replace(" ", "").trim();
		        
		        // VALIDAÇÕES CRUENCIAIS DE TAMANHO E PREENCHIMENTO
		        
		        if (rgm.length() < 8) {
		            JOptionPane.showMessageDialog(null, "O RGM deve conter exatamente 8 dígitos.", "Validação", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        if (nome.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "O campo Nome é obrigatório.", "Validação", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        if (!nome.matches("[A-Za-zÀ-ÿ\\s]+")) {
				    JOptionPane.showMessageDialog(
				        null, 
				        "O campo Nome deve conter apenas letras e espaços! Remova os números ou símbolos.", 
				        "Nome Inválido", 
				        JOptionPane.WARNING_MESSAGE
				    );
				    return;
				}
		        if (dataLimpa.length() < 8) {
		            JOptionPane.showMessageDialog(null, "A Data de Nascimento está incompleta. Digite o formato DD/MM/AAAA.", "Validação", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        if (cpfLimpo.length() < 11) {
		            JOptionPane.showMessageDialog(null, "O CPF está incompleta. Ele deve conter exatamente 11 números.", "Validação", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        if (email.isEmpty() || !email.contains("@")) { // Validação extra de e-mail simples
		            JOptionPane.showMessageDialog(null, "Insira um endereço de E-mail válido.", "Validação", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        if (endereco.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "O campo Endereço é obrigatório.", "Validação", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        if (numero.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "O campo Número é obrigatório. Caso não tenha, digite 'S/N'.", "Validação", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        if (cepLimpo.length() < 8) {
		            JOptionPane.showMessageDialog(null, "O CEP está incompleto. Ele deve conter exatamente 8 dígitos.", "Validação", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        if (municipio.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "O campo Município é obrigatório.", "Validação", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        if (telefoneLimpo.length() < 10) { // Aceita fixo (10) ou celular (11)
		            JOptionPane.showMessageDialog(null, "O número de Telefone está incompleto.", "Validação", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        if (grupoPeriodo.getSelection() == null) {
		            JOptionPane.showMessageDialog(null, "Selecione um Período na aba Curso.", "Validação", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        if (tipoCursoSelected.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Selecione uma Modalidade na aba Curso.", "Validação", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		        if (alunoSendoEditado != null) {

		            try {

		                // =========================
		                // SALVANDO ENDEREÇO NO BANCO
		                // =========================

		                Endereco sendereco = new Endereco();
		                EnderecoDAO enderecoDao = new EnderecoDAO();

		                sendereco.setLogradouro(endereco);
		                sendereco.setCep(cep);
		                sendereco.setNumeroEndereco(numero);
		                sendereco.setComplemento(complemento);
		                sendereco.setMunicipio(municipio);
		                sendereco.setUf(ufSelected);

		                enderecoDao.salvar(sendereco);

		                System.out.println("Endereco salvo com sucesso!");


		                JOptionPane.showMessageDialog(
		                    null,
		                    "Dados do aluno " + nome + " alterados com sucesso!"
		                );

		                alunoSendoEditado = null;

		            } catch (Exception ex) {

		                ex.printStackTrace();

		                JOptionPane.showMessageDialog(
		                    null,
		                    "Erro ao salvar endereço:\n" + ex.getMessage(),
		                    "Erro",
		                    JOptionPane.ERROR_MESSAGE
		                );
		            }

		        } else {

		            Aluno novoAluno = new Aluno(
		                rgm,
		                nome,
		                dataNasc,
		                cpf,
		                email,
		                endereco,
		                numero,
		                cep,
		                complemento,
		                municipio,
		                ufSelected,
		                telefone,
		                cursoSelected,
		                campusSelected,
		                tipoCursoSelected
		            );

		            listaAlunos.add(novoAluno);

		            JOptionPane.showMessageDialog(
		                null,
		                "Aluno " + nome + " cadastrado com sucesso!"
		            );
		        }
		        
		        limparCampos();
		        comboCursos.setSelectedIndex(0);
		        comboCampus.setSelectedIndex(0);
		        grupoPeriodo.clearSelection();
		        gerenciarBoletim(null);
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
		
		// CONFIGURAÇÃO DA ABA: DADOS PESSOAIS
		JPanel DadosPessoais = new JPanel();
		tabbedPane.addTab("Dados Pessoais", null, DadosPessoais, null);
		DadosPessoais.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("RGM");
		lblNewLabel_8.setBounds(10, 23, 51, 20);
		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 20));
		DadosPessoais.add(lblNewLabel_8);
		
		// Certifique-se de que a criação do txtNome está aqui antes do KeyListener!
		txtNome = new JTextField();
		txtNome.setBounds(415, 19, 403, 30); // Ajuste os valores de bounds se necessário
		txtNome.setFont(new Font("Arial", Font.PLAIN, 20));
		txtNome.setColumns(10);
		DadosPessoais.add(txtNome);
		
		// BLOQUEIO DE NÚMEROS 
		txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				char c = e.getKeyChar();
				// Se o caractere for um número, consome o evento (o número não aparece na tela)
				if (Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		
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
		txtDataNasc.setFocusLostBehavior(JFormattedTextField.PERSIST);
		txtDataNasc.setFont(new Font("Arial", Font.PLAIN, 20));
		txtDataNasc.setBounds(203, 97, 127, 30);
		DadosPessoais.add(txtDataNasc);
		
		txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCPF.setFocusLostBehavior(JFormattedTextField.PERSIST);
		txtCPF.setFont(new Font("Arial", Font.PLAIN, 20));
		txtCPF.setBounds(451, 98, 206, 29);
		DadosPessoais.add(txtCPF);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("Telefone");
		lblNewLabel_7_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_7_1_1.setBounds(556, 403, 77, 18);
		DadosPessoais.add(lblNewLabel_7_1_1);
		
		txtTelefone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		txtTelefone.setFocusLostBehavior(JFormattedTextField.PERSIST);
		txtTelefone.setFont(new Font("Arial", Font.PLAIN, 20));
		txtTelefone.setBounds(643, 396, 175, 30);
		DadosPessoais.add(txtTelefone);
		
		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Arial", Font.PLAIN, 20));
		txtNumero.setColumns(10);
		txtNumero.setBounds(104, 324, 84, 30);
		DadosPessoais.add(txtNumero);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Número");
		lblNewLabel_5_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_5_1_1.setBounds(10, 330, 84, 17);
		DadosPessoais.add(lblNewLabel_5_1_1);
		
		txtCEP = new JFormattedTextField(new MaskFormatter("#####-###"));
		txtCEP.setFont(new Font("Arial", Font.PLAIN, 20));
		txtCEP.setFocusLostBehavior(JFormattedTextField.PERSIST);
		txtCEP.setColumns(10);
		txtCEP.setBounds(713, 250, 115, 30);
		DadosPessoais.add(txtCEP);
		
		JLabel lblNewLabel_5_1_2 = new JLabel("CEP");
		lblNewLabel_5_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_5_1_2.setBounds(663, 256, 40, 17);
		DadosPessoais.add(lblNewLabel_5_1_2);
		
		txtComplemento = new JTextField();
		txtComplemento.setFont(new Font("Arial", Font.PLAIN, 20));
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(334, 324, 484, 30);
		DadosPessoais.add(txtComplemento);
		
		JLabel lblNewLabel_5_1_1_1 = new JLabel("Complemento");
		lblNewLabel_5_1_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_5_1_1_1.setBounds(203, 330, 121, 17);
		DadosPessoais.add(lblNewLabel_5_1_1_1);
		
		txtRGM = new JFormattedTextField(new MaskFormatter("########")); 
		txtRGM.setFont(new Font("Arial", Font.PLAIN, 20));
		txtRGM.setBounds(104, 19, 226, 30);
		DadosPessoais.add(txtRGM);
		
		// CONFIGURAÇÃO DA ABA: CURSO
		JPanel Curso = new JPanel();
		tabbedPane.addTab("Curso", null, Curso, null);
		Curso.setLayout(null);
		
		JLabel lblNewLabel_7_1_2 = new JLabel("Curso");
		lblNewLabel_7_1_2.setBounds(10, 39, 72, 24);
		lblNewLabel_7_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
		Curso.add(lblNewLabel_7_1_2);
		
		comboCursos = new JComboBox<String>(); 
		comboCursos.setModel(new DefaultComboBoxModel<>(new String[] {
		    "Ciência da Computação", 
		    "Engenharia Civil", 
		    "Engenharia de Software", 
		    "Direito", 
		    "Administração", 
		    "Medicina", 
		    "Psicologia", 
		    "Sistemas de Informação"
		}));
		comboCursos.setBounds(92, 35, 726, 32);
		comboCursos.setFont(new Font("Arial", Font.PLAIN, 20));
		Curso.add(comboCursos);
		
		JLabel lblNewLabel_7_1_2_1 = new JLabel("Período");
		lblNewLabel_7_1_2_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_7_1_2_1.setBounds(10, 235, 72, 24);
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
		
				// SEÇÃO: PERÍODO 
				JLabel lblNewLabel_7_1_2_14 = new JLabel("Período");
				lblNewLabel_7_1_2_14.setFont(new Font("Arial", Font.PLAIN, 20));
				lblNewLabel_7_1_2_14.setBounds(10, 235, 72, 24);
				Curso.add(lblNewLabel_7_1_2_14);
				
				grupoPeriodo = new ButtonGroup(); 

				JRadioButton rdbtnMatutino = new JRadioButton("Matutino");
				rdbtnMatutino.setFont(new Font("Arial", Font.PLAIN, 18));
				rdbtnMatutino.setBounds(153, 237, 129, 23);
				Curso.add(rdbtnMatutino);
				grupoPeriodo.add(rdbtnMatutino);

				JRadioButton rdbtnVespertino = new JRadioButton("Vespertino");
				rdbtnVespertino.setFont(new Font("Arial", Font.PLAIN, 18));
				rdbtnVespertino.setBounds(345, 237, 129, 23);
				Curso.add(rdbtnVespertino);
				grupoPeriodo.add(rdbtnVespertino);

				JRadioButton rdbtnNoturno = new JRadioButton("Noturno");
				rdbtnNoturno.setFont(new Font("Arial", Font.PLAIN, 18));
				rdbtnNoturno.setBounds(550, 237, 129, 23);
				Curso.add(rdbtnNoturno);
				grupoPeriodo.add(rdbtnNoturno);

				// SEÇÃO: MODALIDADE 
				
				JLabel lblModalidade = new JLabel("Modalidade");
				lblModalidade.setFont(new Font("Arial", Font.PLAIN, 20));
				lblModalidade.setBounds(10, 339, 110, 24); 
				Curso.add(lblModalidade);

				// ESSA LINHA AQUI É A CHAVE DO ERRO! 
				
				grupoModalidade = new ButtonGroup(); 

				JRadioButton rdbtnPresencial = new JRadioButton("Presencial");
				rdbtnPresencial.setActionCommand("Presencial");
				rdbtnPresencial.setFont(new Font("Arial", Font.PLAIN, 18));
				rdbtnPresencial.setBounds(153, 341, 129, 23); 
				Curso.add(rdbtnPresencial);
				grupoModalidade.add(rdbtnPresencial); 

				JRadioButton rdbtnEAD = new JRadioButton("EAD");
				rdbtnEAD.setActionCommand("EAD");
				rdbtnEAD.setFont(new Font("Arial", Font.PLAIN, 18));
				rdbtnEAD.setBounds(345, 341, 129, 23); 
				Curso.add(rdbtnEAD);
				grupoModalidade.add(rdbtnEAD);

				JRadioButton rdbtnHibrido = new JRadioButton("Híbrido");
				rdbtnHibrido.setActionCommand("Híbrido");
				rdbtnHibrido.setFont(new Font("Arial", Font.PLAIN, 18));
				rdbtnHibrido.setBounds(550, 341, 129, 23); 
				Curso.add(rdbtnHibrido);
				grupoModalidade.add(rdbtnHibrido);
				
		// CONFIGURAÇÃO DA ABA: NOTAS E FALTAS
		JPanel NotasFaltas = new JPanel();
		tabbedPane.addTab("Notas e Faltas", null, NotasFaltas, null);
		NotasFaltas.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("RGM");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 46, 30);
		NotasFaltas.add(lblNewLabel);
		
		txtRgmNotas = new JTextField();
		txtRgmNotas.setFont(new Font("Arial", Font.PLAIN, 18));
		txtRgmNotas.setBounds(94, 8, 173, 33);
		NotasFaltas.add(txtRgmNotas);
		txtRgmNotas.setColumns(10);
		
		txtRgmNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rgmProcurado = txtRgmNotas.getText().trim();
				Aluno encontrado = null;
				
				for (Aluno a : listaAlunos) {
					if (a.rgm.equals(rgmProcurado)) {
						encontrado = a;
						break;
					}
				}
				
				if (encontrado != null) {
					txtNomeNotas.setText(encontrado.nome);
					txtCurso.setText(encontrado.curso);
					gerenciarBoletim(encontrado); 
					JOptionPane.showMessageDialog(null, "Aluno com RGM " + rgmProcurado + " não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
					txtNomeNotas.setText("");
					txtCurso.setText("");
					gerenciarBoletim(null);
				}
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(287, 11, 49, 30);
		NotasFaltas.add(lblNewLabel_1);
		
		txtNomeNotas = new JTextField();
		txtNomeNotas.setEditable(false);
		txtNomeNotas.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNomeNotas.setBounds(346, 8, 472, 33);
		NotasFaltas.add(txtNomeNotas);
		txtNomeNotas.setColumns(10);
		
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
		
		boxDisciplina = new JComboBox<String>();
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
		
		boxSemestre = new JComboBox<String>();
		boxSemestre.setModel(new DefaultComboBoxModel<>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		boxSemestre.setFont(new Font("Arial", Font.PLAIN, 20));
		boxSemestre.setBounds(100, 232, 130, 31);
		NotasFaltas.add(boxSemestre);
		
		txtNotaField = new JTextField();
		txtNotaField.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNotaField.setBounds(384, 232, 130, 33);
		NotasFaltas.add(txtNotaField);
		
		txtFaltas = new JTextField();
		txtFaltas.setFont(new Font("Arial", Font.PLAIN, 18));
		txtFaltas.setColumns(10);
		txtFaltas.setBounds(655, 232, 163, 33);
		NotasFaltas.add(txtFaltas);
		
		JButton btnEnviarNota = new JButton("Enviar");
		btnEnviarNota.setFont(new Font("Arial", Font.PLAIN, 18));
		btnEnviarNota.setBounds(10, 416, 808, 35);
		NotasFaltas.add(btnEnviarNota);
		
		btnEnviarNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rgmProcurado = txtRgmNotas.getText().trim();
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
					encontrado.nota = txtNotaField.getText().trim().replace(",", "."); 
					encontrado.faltas = txtFaltas.getText().trim();
					
					JOptionPane.showMessageDialog(null, "Notas e faltas gravadas com sucesso para o aluno: " + encontrado.nome);
					
					gerenciarBoletim(encontrado); // Atualiza o boletim no momento exato do envio!
					
					txtRgmNotas.setText("");
					txtNomeNotas.setText("");
					txtCurso.setText("");
					txtFaltas.setText("");
					txtNotaField.setText("");
					boxDisciplina.setSelectedIndex(0);
					boxSemestre.setSelectedIndex(0);
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, digite um RGM válido e aperte Enter antes de salvar.", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		// CONFIGURAÇÃO DO PAINEL DO BOLETIM (AJUSTADO: Agora é uma aba fixa)
		Boletim = new JPanel();
		Boletim.setLayout(new BorderLayout());
		
		txtAreaBoletim = new JTextArea();
		txtAreaBoletim.setEditable(false);
		txtAreaBoletim.setFont(new Font("Monospaced", Font.PLAIN, 16)); 
		txtAreaBoletim.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		JScrollPane scrollBoletim = new JScrollPane(txtAreaBoletim);
		Boletim.add(scrollBoletim, BorderLayout.CENTER);
		
		// Insere o Boletim como a 4ª aba nativa e permanente do formulário
		tabbedPane.addTab("Boletim", null, Boletim, null);
		gerenciarBoletim(null); // Inicia com o aviso de vazio padrão
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
							} else if (nomeCard.equals("telaAlterarLista")) {
								setCamposEditaveis(true);
								alunoSendoEditado = selecionado; 
							}
							
							gerenciarBoletim(selecionado); // Carrega o boletim desse aluno selecionado
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
	
	private void preencherFormulario(Aluno a) {
		// Preenche Dados Pessoais e Endereço
		txtRGM.setText(a.rgm);
		txtNome.setText(a.nome);
		txtDataNasc.setText(a.dataNasc);
		txtCPF.setText(a.cpf);
		txtEmail.setText(a.email);
		txtEndereco.setText(a.endereco);
		txtNumero.setText(a.numero);       
		txtCEP.setText(a.cep);          
		txtComplemento.setText(a.complemento);  
		txtMunicipio.setText(a.municipio);
		UF.setSelectedItem(a.uf);
		txtTelefone.setText(a.telefone);
		
		// Preenche Curso e Modalidade
		comboCursos.setSelectedItem(a.curso);
		comboCampus.setSelectedItem(a.campus); 
		
		// Sincroniza a Modalidade nos botões de rádio
		if (a.tipoCurso != null && grupoModalidade != null) {
			Enumeration<AbstractButton> botoes = grupoModalidade.getElements();
			while (botoes.hasMoreElements()) {
				AbstractButton botao = botoes.nextElement();
				if (botao.getActionCommand().equals(a.tipoCurso)) {
					botao.setSelected(true);
					break;
				}
			}
		}

		// ==================== NOVO: SINCRONIZA A ABA NOTAS E FALTAS ====================
		txtRgmNotas.setText(a.rgm);
		txtNomeNotas.setText(a.nome);
		txtCurso.setText(a.curso);
		txtNotaField.setText(a.nota);
		txtFaltas.setText(a.faltas);
		
		// Se o aluno já tiver disciplina e semestre salvos, seleciona nos combos
		if (a.disciplina != null && !a.disciplina.isEmpty()) {
			boxDisciplina.setSelectedItem(a.disciplina);
		}
		if (a.semestre != null && !a.semestre.isEmpty()) {
			boxSemestre.setSelectedItem(a.semestre);
		}
	}
	
	private void limparCampos() {
		// Limpa primeira aba
		txtRGM.setValue(null);
		txtRGM.setText("");
		txtNome.setText("");
		txtDataNasc.setValue(null);
		txtDataNasc.setText(""); 
		txtCPF.setValue(null);
		txtCPF.setText(""); 
		txtEmail.setText("");
		txtEndereco.setText("");
		txtMunicipio.setText("");
		UF.setSelectedIndex(0);
		txtTelefone.setValue(null);
		txtTelefone.setText(""); 
		txtNumero.setText("");
		txtCEP.setValue(null);
		txtCEP.setText(""); 
		txtComplemento.setText("");
		

		comboCursos.setSelectedIndex(0);
		comboCampus.setSelectedIndex(0);
		grupoModalidade.clearSelection();
		grupoPeriodo.clearSelection();
		
		
		txtRgmNotas.setText("");
		txtNomeNotas.setText("");
		txtCurso.setText("");
		txtNotaField.setText("");
		txtFaltas.setText("");
		boxDisciplina.setSelectedIndex(0);
		boxSemestre.setSelectedIndex(0);
	}


	private void setCamposEditaveis(boolean editavel) {
		// Bloqueia/Libera aba 1
		txtRGM.setEditable(editavel);
		txtNome.setEditable(editavel);
		txtDataNasc.setEditable(editavel);
		txtCPF.setEditable(editavel);
		txtEmail.setEditable(editavel);
		txtEndereco.setEditable(editavel);
		txtMunicipio.setEditable(editavel);
		txtTelefone.setEditable(editavel);
		txtNumero.setEditable(editavel);
		txtCEP.setEditable(editavel);
		txtComplemento.setEditable(editavel);
		UF.setEnabled(editavel);
		
		
		comboCursos.setEnabled(editavel);
		comboCampus.setEnabled(editavel);
		
		mntmNewMenuItem.setEnabled(editavel); 
		
		Enumeration<AbstractButton> botoesPeriodo = grupoPeriodo.getElements();
		while (botoesPeriodo.hasMoreElements()) {
			botoesPeriodo.nextElement().setEnabled(editavel);
		}
		
		Enumeration<AbstractButton> botoesModalidade = grupoModalidade.getElements();
		while (botoesModalidade.hasMoreElements()) {
			botoesModalidade.nextElement().setEnabled(editavel);
		}
		
		// ==================== NOVO: BLOQUEIA/LIBERA ABA DE NOTAS ====================
		txtRgmNotas.setEditable(editavel);
		txtNotaField.setEditable(editavel);
		txtFaltas.setEditable(editavel);
		boxDisciplina.setEnabled(editavel);
		boxSemestre.setEnabled(editavel);
	}
	// AJUSTADO: O método agora gerencia apenas a atualização das Strings na JTextArea fixa
	private void gerenciarBoletim(Aluno aluno) {
		if (aluno != null) {
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

			sb.append("  Curso / Modalidade:  ").append(aluno.curso).append(" (" + aluno.tipoCurso + ")\n"); 
			sb.append("  Campus:              ").append(aluno.campus).append("\n\n");

			sb.append("  Disciplina:          ").append(aluno.disciplina.isEmpty() ? "Não informada" : aluno.disciplina).append("\n");
			sb.append("  Semestre:            ").append(aluno.semestre.isEmpty() ? "-" : aluno.semestre).append("º Semestre\n");
			sb.append("  Nota Atribuída:      ").append(aluno.nota.isEmpty() ? "Sem Nota" : aluno.nota).append("\n");
			sb.append("  Faltas Acumuladas:   ").append(aluno.faltas.isEmpty() ? "0" : aluno.faltas).append("\n");
			
			txtAreaBoletim.setText(sb.toString());
		} else {
			txtAreaBoletim.setText("\n\n   Nenhum aluno carregado para consulta. \n   Consulte um registro nas listas do menu superior.");
		}
	}
}