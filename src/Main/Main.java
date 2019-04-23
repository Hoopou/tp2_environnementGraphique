package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class Main {

	private JFrame frmGestionDesArtistes;
	private JTextField textFieldRecherche;
	private JTable tableArtistes;
	private JTextField textFieldNumero;
	private JTextField textFieldNom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmGestionDesArtistes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionDesArtistes = new JFrame();
		frmGestionDesArtistes.setTitle("Gestion des artistes");
		frmGestionDesArtistes.setBounds(100, 100, 832, 510);
		frmGestionDesArtistes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionDesArtistes.getContentPane().setLayout(null);
		
		textFieldRecherche = new JTextField();
		textFieldRecherche.setBounds(10, 28, 391, 30);
		frmGestionDesArtistes.getContentPane().add(textFieldRecherche);
		textFieldRecherche.setColumns(10);
		
		JLabel lblRechercherUnArtiste = new JLabel("Rechercher un artiste");
		lblRechercherUnArtiste.setBounds(10, 11, 168, 14);
		frmGestionDesArtistes.getContentPane().add(lblRechercherUnArtiste);
		
		JButton btnRecherche = new JButton("Recherche");
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRecherche.setBounds(411, 28, 117, 30);
		frmGestionDesArtistes.getContentPane().add(btnRecherche);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(689, 32, 117, 30);
		frmGestionDesArtistes.getContentPane().add(btnQuitter);
		
		JLabel lblArtistes = new JLabel("Artistes");
		lblArtistes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblArtistes.setBounds(10, 87, 93, 21);
		frmGestionDesArtistes.getContentPane().add(lblArtistes);
		
		JLabel lblImageArtiste = new JLabel("image");
		lblImageArtiste.setBackground(Color.WHITE);
		lblImageArtiste.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageArtiste.setBounds(10, 131, 168, 116);
		frmGestionDesArtistes.getContentPane().add(lblImageArtiste);
		
		JButton btnRemplacer = new JButton("Remplacer");
		btnRemplacer.setBounds(35, 253, 117, 30);
		frmGestionDesArtistes.getContentPane().add(btnRemplacer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(216, 131, 420, 166);
		frmGestionDesArtistes.getContentPane().add(scrollPane);
		
		tableArtistes = new JTable();
		tableArtistes.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tableArtistes);
		
		JButton btnNouveau = new JButton("Nouveau");
		btnNouveau.setBounds(689, 131, 117, 30);
		frmGestionDesArtistes.getContentPane().add(btnNouveau);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(689, 174, 117, 30);
		frmGestionDesArtistes.getContentPane().add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBounds(689, 215, 117, 30);
		frmGestionDesArtistes.getContentPane().add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(689, 267, 117, 30);
		frmGestionDesArtistes.getContentPane().add(btnSupprimer);
		
		JLabel labelInformations = new JLabel("Informations");
		labelInformations.setFont(new Font("Tahoma", Font.PLAIN, 22));
		labelInformations.setBounds(10, 319, 186, 21);
		frmGestionDesArtistes.getContentPane().add(labelInformations);
		
		JLabel lblNumro = new JLabel("Num\u00E9ro:");
		lblNumro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumro.setBounds(10, 351, 79, 21);
		frmGestionDesArtistes.getContentPane().add(lblNumro);
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNom.setBounds(10, 380, 79, 21);
		frmGestionDesArtistes.getContentPane().add(lblNom);
		
		JLabel lblMembre = new JLabel("Membre:");
		lblMembre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMembre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMembre.setBounds(10, 409, 79, 21);
		frmGestionDesArtistes.getContentPane().add(lblMembre);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setEditable(false);
		textFieldNumero.setBounds(92, 351, 198, 20);
		frmGestionDesArtistes.getContentPane().add(textFieldNumero);
		textFieldNumero.setColumns(10);
		
		textFieldNom = new JTextField();
		textFieldNom.setEditable(false);
		textFieldNom.setColumns(10);
		textFieldNom.setBounds(92, 383, 198, 20);
		frmGestionDesArtistes.getContentPane().add(textFieldNom);
		
		JCheckBox checkBoxMembre = new JCheckBox("");
		checkBoxMembre.setEnabled(false);
		checkBoxMembre.setBounds(95, 410, 195, 23);
		frmGestionDesArtistes.getContentPane().add(checkBoxMembre);
		
		JScrollPane scrollPane_ListeAlbums = new JScrollPane();
		scrollPane_ListeAlbums.setBounds(388, 351, 198, 109);
		frmGestionDesArtistes.getContentPane().add(scrollPane_ListeAlbums);
		
		JList listAlbums = new JList();
		scrollPane_ListeAlbums.setViewportView(listAlbums);
		listAlbums.setModel(new AbstractListModel() {
			String[] values = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JLabel labelImageAlbumCouverture = new JLabel("image");
		labelImageAlbumCouverture.setHorizontalAlignment(SwingConstants.CENTER);
		labelImageAlbumCouverture.setBackground(Color.WHITE);
		labelImageAlbumCouverture.setBounds(638, 344, 168, 116);
		frmGestionDesArtistes.getContentPane().add(labelImageAlbumCouverture);
	}
}
