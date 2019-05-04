package Main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;

import Objects.Utilisateurs;
import controller.ControlleurBD;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

public class Connection {

	private JFrame frmConnection;
	private JPanel panelInscription;
	private JPanel panelConnection;
	private JPasswordField panelConnection_ecran_MDP;
	private JCheckBox panelInscription_chexkBox_Admin;
	private JTextField panelConnection_ecran_utilisateur;
	private JPasswordField panelInscription_ecran_MDP;
	private JTextField panelInscription_ecran_utilisateur;
	private JPasswordField panelInscription_ecran_ConfirmerMDP;

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
		ControlleurBD.GetUtilisateur("Admin", "password");
		frmConnection = new JFrame();
		frmConnection.setResizable(false);
		frmConnection.setTitle("Gestion des Albums");
		frmConnection.setBounds(100, 100, 407, 190);
		frmConnection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConnection.getContentPane().setLayout(null);
		
		panelConnection = new JPanel();
		panelConnection.setBounds(0, 0, 401, 161);
		frmConnection.getContentPane().add(panelConnection);
		panelConnection.setLayout(null);
		
		JLabel panelConnection_txt1 = new JLabel("Connection \u00E0 l'application");
		panelConnection_txt1.setBounds(10, 11, 213, 31);
		panelConnection.add(panelConnection_txt1);
		panelConnection_txt1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel panelConnection_nomUtilisateur = new JLabel("Nom d'utilisateur: ");
		panelConnection_nomUtilisateur.setBounds(10, 54, 138, 22);
		panelConnection.add(panelConnection_nomUtilisateur);
		panelConnection_nomUtilisateur.setHorizontalAlignment(SwingConstants.RIGHT);
		panelConnection_nomUtilisateur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel panelConnection_MDP = new JLabel("Mot de passe: ");
		panelConnection_MDP.setBounds(10, 81, 138, 22);
		panelConnection.add(panelConnection_MDP);
		panelConnection_MDP.setHorizontalAlignment(SwingConstants.RIGHT);
		panelConnection_MDP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		panelConnection_ecran_MDP = new JPasswordField();
		panelConnection_ecran_MDP.setBounds(158, 82, 176, 20);
		panelConnection.add(panelConnection_ecran_MDP);
		
		panelConnection_ecran_utilisateur = new JTextField();
		panelConnection_ecran_utilisateur.setBounds(158, 54, 176, 20);
		panelConnection.add(panelConnection_ecran_utilisateur);
		panelConnection_ecran_utilisateur.setColumns(10);
		
		JButton panelConnection_btnValider = new JButton("Valider");
		panelConnection_btnValider.setBounds(144, 123, 112, 31);
		panelConnection.add(panelConnection_btnValider);
		
		JButton panelConnection_btnQuitter = new JButton("Quitter");
		panelConnection_btnQuitter.setBounds(279, 123, 112, 31);
		panelConnection.add(panelConnection_btnQuitter);
		
		JButton panelConnection_btnInscription = new JButton("Inscription");
		panelConnection_btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetPanelConnection();
				panelInscription.setVisible(true);
				panelConnection.setVisible(false);
			}
		});
		panelConnection_btnInscription.setBounds(10, 123, 112, 31);
		panelConnection.add(panelConnection_btnInscription);
		panelConnection_btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panelConnection_btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Utilisateurs user = ControlleurBD.GetUtilisateur(panelConnection_ecran_utilisateur.getText(), panelConnection_ecran_MDP.getText());
				if(user != null) {
					Main window = new Main(user);
					window.setVisible(true);					
				}else {
					JOptionPane.showMessageDialog(null, "L'utilisateur ou le mot de passe est incorrect!", "Connection", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelConnection.setVisible(true);
		
		panelInscription = new JPanel();
		panelInscription.setBounds(0, 0, 401, 161);
		frmConnection.getContentPane().add(panelInscription);
		panelInscription.setLayout(null);
		
		JLabel panelInscription_txt1 = new JLabel("Inscription");
		panelInscription_txt1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panelInscription_txt1.setBounds(10, 0, 213, 31);
		panelInscription.add(panelInscription_txt1);
		
		JLabel panelInscription_Utilisateur = new JLabel("Nom d'utilisateur: ");
		panelInscription_Utilisateur.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInscription_Utilisateur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelInscription_Utilisateur.setBounds(10, 37, 138, 22);
		panelInscription.add(panelInscription_Utilisateur);
		
		JLabel panelInscription_MDP = new JLabel("Mot de passe: ");
		panelInscription_MDP.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInscription_MDP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelInscription_MDP.setBounds(10, 59, 138, 22);
		panelInscription.add(panelInscription_MDP);
		
		panelInscription_ecran_MDP = new JPasswordField();
		panelInscription_ecran_MDP.setBounds(158, 60, 176, 20);
		panelInscription.add(panelInscription_ecran_MDP);
		
		panelInscription_ecran_utilisateur = new JTextField();
		panelInscription_ecran_utilisateur.setColumns(10);
		panelInscription_ecran_utilisateur.setBounds(158, 37, 176, 20);
		panelInscription.add(panelInscription_ecran_utilisateur);
		
		JButton panelInscription_btnInscription = new JButton("Inscription");
		panelInscription_btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inscription();
			}
		});
		panelInscription_btnInscription.setBounds(144, 123, 112, 31);
		panelInscription.add(panelInscription_btnInscription);
		
		JButton panelInscription_btnAnnuler = new JButton("Annuler");
		panelInscription_btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetPanelInscription();
				panelConnection.setVisible(true);
				panelInscription.setVisible(false);
			}
		});
		panelInscription_btnAnnuler.setBounds(279, 123, 112, 31);
		panelInscription.add(panelInscription_btnAnnuler);
		
		panelInscription_ecran_ConfirmerMDP = new JPasswordField();
		panelInscription_ecran_ConfirmerMDP.setBounds(158, 81, 176, 20);
		panelInscription.add(panelInscription_ecran_ConfirmerMDP);
		
		JLabel panelInscription_confirmerMDP = new JLabel("Confirmer le mot de passe: ");
		panelInscription_confirmerMDP.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInscription_confirmerMDP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelInscription_confirmerMDP.setBounds(10, 80, 138, 22);
		panelInscription.add(panelInscription_confirmerMDP);
		
		JLabel panelInscription_estAdmin = new JLabel("Est Administrateur:");
		panelInscription_estAdmin.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInscription_estAdmin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelInscription_estAdmin.setBounds(10, 102, 138, 22);
		panelInscription.add(panelInscription_estAdmin);
		
		panelInscription_chexkBox_Admin = new JCheckBox("");
		panelInscription_chexkBox_Admin.setBounds(159, 102, 97, 23);
		panelInscription.add(panelInscription_chexkBox_Admin);
		
		
		panelInscription.setVisible(false);
	}
	
	private void resetPanelInscription() {
		// TODO Auto-generated method stub
		panelInscription_ecran_ConfirmerMDP.setText("");
		panelInscription_ecran_MDP.setText("");
		panelInscription_ecran_utilisateur.setText("");
		panelInscription_chexkBox_Admin.setSelected(false);
	}
	private void resetPanelConnection() {
		// TODO Auto-generated method stub
		panelConnection_ecran_utilisateur.setText("");
		panelConnection_ecran_MDP.setText("");
	}
	
	private void inscription() {
		if(panelInscription_ecran_utilisateur.getText().length() > 3 && panelInscription_ecran_utilisateur.getText().length() < 30) {
			if(panelInscription_ecran_MDP.getText().equals(panelInscription_ecran_ConfirmerMDP.getText())) {
				if(panelInscription_ecran_MDP.getText().length() > 3 && panelInscription_ecran_MDP.getText().length() < 30) {
					demandeInscriptionBD();
				}else {
					JOptionPane.showMessageDialog(null, "Pour raison de sécurité, le MOT DE PASSE doit avoir plus de 3 caractères!", "Erreur", JOptionPane.WARNING_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Les MOTS DE PASSES ne sont pas identiques", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Pour raison de sécurité, le NOM D'UTILISATEUR doit avoir plus de 3 caractères!", "Erreur", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void demandeInscriptionBD() {
		int responseBD = ControlleurBD.AjouterUtilisateur(panelInscription_ecran_utilisateur.getText(), panelInscription_ecran_MDP.getText(), panelInscription_chexkBox_Admin.isSelected()?1:0);
		
		if(responseBD != ControlleurBD.OK ) {
			if(responseBD == ControlleurBD.UTILISATEUR_EXISTANT) {
				JOptionPane.showMessageDialog(null, "Cet utilisateur est existant, veuillez en choisir un autre!", "Utilisateur", JOptionPane.WARNING_MESSAGE);
			}else if(responseBD == ControlleurBD.VALEUR_INVALIDE) {
				JOptionPane.showMessageDialog(null, "Une des valeurs indiqué n'est pas conforme!", "Warning", JOptionPane.ERROR_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Il y a eu une erreur de requète a la BD!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			resetPanelInscription();
			JOptionPane.showMessageDialog(null, "Utilisateur ajouté", "Ajouter" , JOptionPane.INFORMATION_MESSAGE);
			panelConnection.setVisible(true);
			panelInscription.setVisible(false);
		}
		
		
	}
}
