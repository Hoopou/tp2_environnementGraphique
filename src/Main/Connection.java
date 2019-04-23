package Main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Connection {

	private JFrame frmConnection;
	private JPasswordField passwordField;
	private JTextField textFieldUtilisateur;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection window = new Connection();
					window.frmConnection.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Connection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConnection = new JFrame();
		frmConnection.setResizable(false);
		frmConnection.setTitle("Gestion des Albums");
		frmConnection.setBounds(100, 100, 407, 190);
		frmConnection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConnection.getContentPane().setLayout(null);
		
		JLabel lblConnectionLapplication = new JLabel("Connection \u00E0 l'application");
		lblConnectionLapplication.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblConnectionLapplication.setBounds(10, 11, 213, 31);
		frmConnection.getContentPane().add(lblConnectionLapplication);
		
		JLabel lblNomDutilisateur = new JLabel("Nom d'utilisateur: ");
		lblNomDutilisateur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomDutilisateur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNomDutilisateur.setBounds(10, 54, 138, 22);
		frmConnection.getContentPane().add(lblNomDutilisateur);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe: ");
		lblMotDePasse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMotDePasse.setBounds(10, 81, 138, 22);
		frmConnection.getContentPane().add(lblMotDePasse);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(158, 82, 176, 20);
		frmConnection.getContentPane().add(passwordField);
		
		textFieldUtilisateur = new JTextField();
		textFieldUtilisateur.setBounds(158, 54, 176, 20);
		frmConnection.getContentPane().add(textFieldUtilisateur);
		textFieldUtilisateur.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main window = new Main();
				window.setVisible(true);
			}
		});
		btnValider.setBounds(59, 119, 138, 31);
		frmConnection.getContentPane().add(btnValider);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(207, 119, 138, 31);
		frmConnection.getContentPane().add(btnQuitter);
	}
}
