package aplicacao_swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DAOTipoPersonagem;
import fachada.Fachada;
import modelo.TipoPersonagem;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class TelaCriarPersonagem extends JFrame {
	private static DAOTipoPersonagem daotipopersonagem = new DAOTipoPersonagem();

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCriarPersonagem frame = new TelaCriarPersonagem();
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
	public TelaCriarPersonagem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(32, 27, 70, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(32, 54, 266, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo do personagem");
		lblNewLabel_1.setBounds(32, 112, 184, 15);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(32, 139, 145, 24);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.setBounds(218, 139, 117, 25);
		contentPane.add(btnNewButton);
		for(TipoPersonagem t : Fachada.listarTipo()) {
			comboBox.addItem(t.getDescricao());
		}
	}
}
