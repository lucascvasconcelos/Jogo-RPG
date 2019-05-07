package aplicacao_swing;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programa��o Orientada a Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fachada.Fachada;

public class TelaPrincipal {

	private JFrame frmPrincipal;
	private JMenuItem mntmCadastrar;
	private JMenuItem mntmListar;
	private JMenu mnConsulta;
	private JMenu mnTelefone;
	private JMenu mnPessoa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();


		frmPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				Fachada.inicializar();
				try {
					Fachada.login(null, null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "sistema inicializado !");
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
				JOptionPane.showMessageDialog(null, "sistema finalizado !");
			}
		});
		frmPrincipal.setTitle("Jogo");
		frmPrincipal.setBounds(100, 100, 450, 300);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);

		mnPessoa = new JMenu("Conta");
		menuBar.add(mnPessoa);

		mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroConta j = new TelaCadastroConta();
				j.setVisible(true);
				
			}

		});
		mnPessoa.add(mntmCadastrar);

		mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				TelaListagem j = new TelaListagem();
//				j.setVisible(true);
			}
		});
		mnPessoa.add(mntmListar);
		
		mntmListar = new JMenuItem("Login");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				TelaListagem j = new TelaListagem();
//				j.setVisible(true);
			}
		});
		mnPessoa.add(mntmListar);



		mnTelefone = new JMenu("Personagem");
		menuBar.add(mnTelefone);
		JMenuItem mntmCriar = new JMenuItem("Adicionar");
		mntmCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCriarPersonagem j = new TelaCriarPersonagem();
				j.setVisible(true);
			}
		});
		mnTelefone.add(mntmCriar);

		JMenuItem mntmListar_1 = new JMenuItem("Listar");
		mntmListar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListagemPersonagem j = new TelaListagemPersonagem();
				j.setVisible(true);
			}
		});
		mnTelefone.add(mntmListar_1);
		
		JMenuItem mntmEscolher = new JMenuItem("Escolher Personagem");
		mntmEscolher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEscolhaPersonagem j = new TelaEscolhaPersonagem();
				j.setVisible(true);
			}
		});
		mnTelefone.add(mntmEscolher);

//		JMenuItem mntmEquipar = new JMenuItem("Listar");
//		mntmListar_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
////				TelaListagem j = new TelaListagem();
////				j.setVisible(true);
//			}
//		});
//		mnTelefone.add(mntmEquipar);
		
//		mnConsulta = new JMenu("Consultas");
//		mnConsulta.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				TelaConsulta j = new TelaConsulta();
//				j.setVisible(true);
//
//			
//			}
//		});
//		menuBar.add(mnConsulta);
	}
	
	public JFrame callback(boolean opcao) {
		return opcao ? frmPrincipal : null;		
	}
}
