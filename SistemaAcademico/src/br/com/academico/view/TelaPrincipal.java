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
import javax.swing.SwingConstants;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
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

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_6;
	private JTextField txtRGM;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtMunicipio;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 591);
		
		JMenuBar menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		JMenu mnNewMenu = new JMenu("Aluno");
		menuBar_1.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Salvar");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Alterar");
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Excluir");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Consultar");
		mnNewMenu.add(mntmNewMenuItem_3);
		
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
		
		JMenu mnNewMenu_1 = new JMenu("Notas e Faltas");
		menuBar_1.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Salvar");
		mntmNewMenuItem_7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Alterar");
		mntmNewMenuItem_6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Excluir");
		mnNewMenu_1.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Consultar");
		mnNewMenu_1.add(mntmNewMenuItem_9);
		
		JMenu mnNewMenu_2 = new JMenu("Ajuda");
		menuBar_1.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Sobre");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Programa desenvolvido por:\n-----------------------------------------------\nMichel Mendes de Moraes\nRafael da Silva Santiago\nMatheus Ferreira de Almeida Sá\nVitor Ferreira de Assis Gomes\nDavid Ben Cavalcante Bernardo\nKaique de Sá Lima da Silva\n\n\n"  );
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 833, 469);
		contentPane.add(tabbedPane);
		
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
		txtEndereco.setBounds(104, 250, 714, 30);
		txtEndereco.setFont(new Font("Arial", Font.PLAIN, 20));
		txtEndereco.setColumns(10);
		DadosPessoais.add(txtEndereco);
		
		JLabel lblNewLabel_6_1 = new JLabel("Município");
		lblNewLabel_6_1.setBounds(10, 333, 84, 21);
		lblNewLabel_6_1.setFont(new Font("Arial", Font.PLAIN, 20));
		DadosPessoais.add(lblNewLabel_6_1);
		
		txtMunicipio = new JTextField();
		txtMunicipio.setBounds(104, 328, 442, 30);
		txtMunicipio.setFont(new Font("Arial", Font.PLAIN, 20));
		txtMunicipio.setColumns(10);
		DadosPessoais.add(txtMunicipio);
		
		JLabel lblNewLabel_7_1 = new JLabel("UF");
		lblNewLabel_7_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_7_1.setBounds(709, 102, 27, 20);
		DadosPessoais.add(lblNewLabel_7_1);
		
		JComboBox UF = new JComboBox<String>();
		UF.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"}));
		UF.setFont(new Font("Arial", Font.PLAIN, 20));
		UF.setBounds(747, 96, 71, 31	);
		DadosPessoais.add(UF);
		UF.addItem("AC");
		UF.addItem("AL");
		UF.addItem("AM");
		UF.addItem("AP");
		UF.addItem("BA");
		UF.addItem("CE");
		UF.addItem("DF");
		UF.addItem("ES");
		UF.addItem("GO");
		UF.addItem("MA");
		
		JFormattedTextField txtDataNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataNasc.setFont(new Font("Arial", Font.PLAIN, 20));
		txtDataNasc.setBounds(203, 97, 127, 30);
		DadosPessoais.add(txtDataNasc);
		
		JFormattedTextField txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCPF.setFont(new Font("Arial", Font.PLAIN, 20));
		txtCPF.setBounds(451, 98, 206, 29);
		DadosPessoais.add(txtCPF);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("Telefone");
		lblNewLabel_7_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_7_1_1.setBounds(556, 335, 77, 18);
		DadosPessoais.add(lblNewLabel_7_1_1);
		
		JFormattedTextField txtTelefone = new JFormattedTextField(new MaskFormatter("(##) ####-####"));
		txtTelefone.setFont(new Font("Arial", Font.PLAIN, 20));
		txtTelefone.setBounds(643, 328, 175, 30);
		DadosPessoais.add(txtTelefone);
		
		JPanel Curso = new JPanel();
		tabbedPane.addTab("Curso", null, Curso, null);
		Curso.setLayout(null);
		
		JLabel lblNewLabel_7_1_2 = new JLabel("Curso");
		lblNewLabel_7_1_2.setBounds(10, 39, 72, 24);
		lblNewLabel_7_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
		Curso.add(lblNewLabel_7_1_2);
		
		JComboBox<String> UF_1 = new JComboBox<String>();
		UF_1.setModel(new DefaultComboBoxModel(new String[] {"Ciência da Computação", "Engenharia Civil / Engenharia de Software", "Direito", "Administração", "Medicina / Psicologia", "Sistemas de Informação"}));
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
		UF_1_2.setModel(new DefaultComboBoxModel(new String[] {"UNICID", "CESUCA", "UP", "UNIPE", "FSG", "FASS", "UDF"}));
		UF_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
		UF_1_2.setBounds(92, 103, 726, 32);
		Curso.add(UF_1_2);
		
		ButtonGroup grupoPeriodo = new ButtonGroup();

		// --- Botão Matutino ---
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Matutino");
		rdbtnNewRadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
		rdbtnNewRadioButton.setBounds(151, 184, 129, 23);
		Curso.add(rdbtnNewRadioButton);
		grupoPeriodo.add(rdbtnNewRadioButton); // Adiciona ao grupo lógico

		// --- Botão Vespertino ---
		JRadioButton rdbtnVespertino = new JRadioButton("Vespertino");
		rdbtnVespertino.setFont(new Font("Arial", Font.PLAIN, 18));
		rdbtnVespertino.setBounds(403, 184, 129, 23);
		Curso.add(rdbtnVespertino);
		grupoPeriodo.add(rdbtnVespertino); // Adiciona ao grupo lógico

		// --- Botão Noturno ---
		JRadioButton rdbtnNewRadioButton_1_1 = new JRadioButton("Noturno");
		rdbtnNewRadioButton_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		rdbtnNewRadioButton_1_1.setBounds(675, 184, 129, 23);
		Curso.add(rdbtnNewRadioButton_1_1);
		grupoPeriodo.add(rdbtnNewRadioButton_1_1); // Adiciona ao grupo lógico
		
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
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_2.setBounds(10, 76, 808, 33);
		NotasFaltas.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Disciplina");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(10, 151, 82, 31);
		NotasFaltas.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Semestre");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(10, 229, 76, 31);
		NotasFaltas.add(lblNewLabel_5);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_6.setBounds(652, 227, 166, 33);
		NotasFaltas.add(textField_6);
		textField_6.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Programação Orientada a Objetos", "Banco de Dados", "Calculo Diferencial"}));
		comboBox.setFont(new Font("Arial", Font.PLAIN, 18));
		comboBox.setBounds(102, 149, 716, 33);
		NotasFaltas.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Arial", Font.PLAIN, 18));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"2020-1", "2020-2"}));
		comboBox_1.setBounds(102, 228, 121, 32);
		NotasFaltas.add(comboBox_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("Nota");
		lblNewLabel_5_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_5_2.setBounds(346, 230, 38, 30);
		NotasFaltas.add(lblNewLabel_5_2);
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"0,5", "1,0", "1,5", "2,0", "2,5", "3,0", "3,5", "4,0", "4,5", "5,0"}));
		comboBox_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		comboBox_1_1.setBounds(394, 227, 65, 33);
		NotasFaltas.add(comboBox_1_1);
		
		JLabel lblNewLabel_5_2_1 = new JLabel("Faltas");
		lblNewLabel_5_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_5_2_1.setBounds(593, 230, 49, 30);
		NotasFaltas.add(lblNewLabel_5_2_1);
		
		JPanel Boletim = new JPanel();
		tabbedPane.addTab("Boletim", null, Boletim, null);

	}
}
