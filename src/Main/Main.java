package Main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.event.TableModelListener;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.sun.xml.internal.stream.buffer.stax.StreamReaderBufferCreator;

import Objects.Albums;
import Objects.Artistes;
import Objects.Utilisateurs;
import controller.ControlleurBD;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;

public class Main extends JFrame{
	
	private Utilisateurs CurrentUser;
	
	private JTextField textFieldRecherche;
	private JTable tableArtistes;
	private JTextField ecran_numeroArtiste;
	private JTextField ecran_nomArtiste;
	private JCheckBox ecran_check_membre;
	private JButton btnModifier;
	private JButton btnAjouter;
	private JLabel lblImageArtiste;
	private JButton btnSupprimer;
	private JButton btnRemplacer;
	private JList listAlbums;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
//				Utilisateurs userAdmin = new Utilisateurs();
//				userAdmin.setAdmin(true);
//				userAdmin.setId(1);
//				userAdmin.setMdp("password");
//				userAdmin.setNom("Admin");
				try {
					Connection window = new Connection();
					window.main(args);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main(Utilisateurs user) {
		CurrentUser = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Gestion des artistes");
		setBounds(100, 100, 832, 510);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		textFieldRecherche = new JTextField();
		textFieldRecherche.setBounds(10, 28, 391, 30);
		getContentPane().add(textFieldRecherche);
		textFieldRecherche.setColumns(10);
		
		JLabel lblRechercherUnArtiste = new JLabel("Rechercher un artiste");
		lblRechercherUnArtiste.setBounds(10, 11, 168, 14);
		getContentPane().add(lblRechercherUnArtiste);
		
		JButton btnRecherche = new JButton("Recherche");
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldRecherche.getText().replaceAll(" " , "").equals("")) {
					clearTable();
					insertArtistesIntoTable();
					resetTextIntoModification();
					ActiverDesactiverModificationArtiste(false);
				}else{
					clearTable();
					
					newTableModel();
					ArrayList<Artistes> arrayArtisteComplete = ControlleurBD.GetArtistes();
					ArrayList<Artistes> arrayArtiste = new ArrayList<Artistes>();
					
					for(Artistes currentArtiste:arrayArtisteComplete) {
						if(currentArtiste.getNom().toLowerCase().contains(textFieldRecherche.getText().toLowerCase())) {
							arrayArtiste.add(currentArtiste);
						}
					}
					
					DefaultTableModel model = (DefaultTableModel) tableArtistes.getModel();
					
					for(int i = 0 ; i<arrayArtiste.size() ; i++) {
						model.addRow(new Object[]{arrayArtiste.get(i).getId(), arrayArtiste.get(i).getNom(), arrayArtiste.get(i).getMembre()});			
					}
					
					resetTextIntoModification();
					ActiverDesactiverModificationArtiste(false);
				}
				clearAlbums();
			}
		});
		btnRecherche.setBounds(411, 28, 117, 30);
		getContentPane().add(btnRecherche);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(689, 32, 117, 30);
		getContentPane().add(btnQuitter);
		
		JLabel lblArtistes = new JLabel("Artistes");
		lblArtistes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblArtistes.setBounds(10, 87, 93, 21);
		getContentPane().add(lblArtistes);
		
		lblImageArtiste = new JLabel("image");
		lblImageArtiste.setDisabledIcon(null);
		lblImageArtiste.setBackground(Color.WHITE);
		lblImageArtiste.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageArtiste.setBounds(10, 131, 168, 116);
		getContentPane().add(lblImageArtiste);
		
		btnRemplacer = new JButton("Remplacer");
		btnRemplacer.setEnabled(false);
		btnRemplacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				int resp = fc.showOpenDialog(null);
				if(resp == JFileChooser.OPEN_DIALOG && fc.getSelectedFile().toString().endsWith(".png")) {
					try {
						BufferedImage image = ImageIO.read(new FileInputStream(new File(fc.getSelectedFile().toString())));
						Artistes artiste = getArtisteWithId((int)tableArtistes.getModel().getValueAt(tableArtistes.getSelectedRow(), 0));
						artiste.setPhoto(image);
						ControlleurBD.UpdateImageArtiste(artiste);
					}catch(Exception e) {
						System.out.println("Erreur de lecture de l'image");
					}
					
					clearTable();
					insertArtistesIntoTable();
					resetTextIntoModification();
					ActiverDesactiverModificationArtiste(false);
					
					System.out.println("ouvrir le fichier" + fc.getSelectedFile());
				}
			}
		});
		btnRemplacer.setBounds(35, 253, 117, 30);
		getContentPane().add(btnRemplacer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(216, 131, 420, 166);
		getContentPane().add(scrollPane);
		
		
		tableArtistes = new JTable();
		tableArtistes.setModel(newTableModel());
		tableArtistes.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				SelectionnerArtiste();
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		scrollPane.setViewportView(tableArtistes);
		
		JButton btnNouveau = new JButton("Nouveau");
		btnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				ActiverDesactiverModificationArtiste(true);
				tableArtistes.clearSelection();
				resetTextIntoModification();
				btnAjouter.setEnabled(true);
			}
		});
		btnNouveau.setBounds(689, 131, 117, 30);
		btnNouveau.setEnabled(true);
		getContentPane().add(btnNouveau);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!ecran_numeroArtiste.getText().replace(" " , "").equals("") || !ecran_nomArtiste.getText().replace(" " , "").equals("")) {
					artisteIdVerification();
					clearTable();
					insertArtistesIntoTable();
					resetTextIntoModification();
					ActiverDesactiverModificationArtiste(false);
					btnAjouter.setEnabled(false);
					btnModifier.setEnabled(false);
				}else {
					JOptionPane.showMessageDialog(null, "Vous devez entrer des informations valides d'abord!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAjouter.setBounds(689, 174, 117, 30);
		btnAjouter.setEnabled(false);
		getContentPane().add(btnAjouter);
		
		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Artistes oldArtiste = getArtisteWithId((int)tableArtistes.getModel().getValueAt(tableArtistes.getSelectedRow(), 0));
					Artistes newArtiste = getCurrentModifiedArtiste();
					newArtiste.setCreateur(oldArtiste.getCreateur());
					newArtiste.setPhoto(oldArtiste.getPhoto());
					
					ArrayList<Albums> arrayAlbums = ControlleurBD.GetAlbums();
					for(int i = 0 ; i < arrayAlbums.size() ; i++) {
						if(arrayAlbums.get(i).getIdArtiste() == oldArtiste.getId()) {
							ControlleurBD.DeleteAlbum(arrayAlbums.get(i).getId());
						}
					}					
					
					ControlleurBD.DeleteArtiste(oldArtiste.getId());
					ControlleurBD.AjouterArtiste(newArtiste);
					
					
					
				}catch(Exception j) {
					JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors de la modification d'un artiste!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				clearTable();
				insertArtistesIntoTable();
				resetTextIntoModification();
				ActiverDesactiverModificationArtiste(false);
			}
		});
		btnModifier.setBounds(689, 215, 117, 30);
		btnModifier.setEnabled(false);
		getContentPane().add(btnModifier);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int response = ControlleurBD.DeleteArtiste((int)tableArtistes.getModel().getValueAt(tableArtistes.getSelectedRow(), 0));
					if(response != ControlleurBD.OK ) {
						if(response == ControlleurBD.VALEUR_INVALIDE) {
							JOptionPane.showMessageDialog(null, "Il y a eu une erreur de suppression d'artiste!", "Warning", JOptionPane.ERROR_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "Il y a eu une erreur de suppression d'artiste!", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						//la supression a la bd à été fait
						clearTable();
						insertArtistesIntoTable();
						resetTextIntoModification();
						ActiverDesactiverModificationArtiste(false);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				clearTable();
				insertArtistesIntoTable();
				resetTextIntoModification();
				ActiverDesactiverModificationArtiste(false);
			}
		});
		btnSupprimer.setBounds(689, 267, 117, 30);
		getContentPane().add(btnSupprimer);
		
		JLabel labelInformations = new JLabel("Informations");
		labelInformations.setFont(new Font("Tahoma", Font.PLAIN, 22));
		labelInformations.setBounds(10, 319, 186, 21);
		getContentPane().add(labelInformations);
		
		JLabel lblNumro = new JLabel("Num\u00E9ro:");
		lblNumro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumro.setBounds(10, 351, 79, 21);
		getContentPane().add(lblNumro);
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNom.setBounds(10, 380, 79, 21);
		getContentPane().add(lblNom);
		
		JLabel lblMembre = new JLabel("Membre:");
		lblMembre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMembre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMembre.setBounds(10, 409, 79, 21);
		getContentPane().add(lblMembre);
		
		ecran_numeroArtiste = new JTextField();
		ecran_numeroArtiste.setEditable(false);
		ecran_numeroArtiste.setBounds(92, 351, 198, 20);
		getContentPane().add(ecran_numeroArtiste);
		ecran_numeroArtiste.setColumns(10);
		
		ecran_nomArtiste = new JTextField();
		ecran_nomArtiste.setEditable(false);
		ecran_nomArtiste.setColumns(10);
		ecran_nomArtiste.setBounds(92, 383, 198, 20);
		getContentPane().add(ecran_nomArtiste);
		
		ecran_check_membre = new JCheckBox("");
		ecran_check_membre.setEnabled(false);
		ecran_check_membre.setBounds(95, 410, 195, 23);
		getContentPane().add(ecran_check_membre);
		
		JScrollPane scrollPane_ListeAlbums = new JScrollPane();
		scrollPane_ListeAlbums.setBounds(388, 351, 198, 109);
		getContentPane().add(scrollPane_ListeAlbums);
		
		listAlbums = new JList();
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
		getContentPane().add(labelImageAlbumCouverture);
		
		insertArtistesIntoTable();
		clearAlbums();
	}

	private void insertArtistesIntoTable() {
		// TODO Auto-generated method stub
		newTableModel();
		textFieldRecherche.setText("");
		ArrayList<Artistes> arrayArtiste = ControlleurBD.GetArtistes();
		
		DefaultTableModel model = (DefaultTableModel) tableArtistes.getModel();
		
		for(int i = 0 ; i<arrayArtiste.size() ; i++) {
			model.addRow(new Object[]{arrayArtiste.get(i).getId(), arrayArtiste.get(i).getNom(), arrayArtiste.get(i).getMembre()});			
		}
		
	}
	
	private void ActiverDesactiverModificationArtiste(boolean active) {
		ecran_numeroArtiste.setEditable(active);
		ecran_nomArtiste.setEditable(active);
		ecran_check_membre.setEnabled(active);
		
	}
	
	private void setSelectedRowValuesIntoModification() {
		ecran_numeroArtiste.setText(tableArtistes.getModel().getValueAt(tableArtistes.getSelectedRow(),0).toString());
		ecran_nomArtiste.setText(tableArtistes.getModel().getValueAt(tableArtistes.getSelectedRow(),1).toString());
		ecran_check_membre.setSelected((boolean)tableArtistes.getModel().getValueAt(tableArtistes.getSelectedRow(),2));
		
	}
	
	private void resetTextIntoModification() {
		ecran_numeroArtiste.setText("");
		ecran_nomArtiste.setText("");
		ecran_check_membre.setSelected(false);
	}
	
	private void artisteIdVerification() {
		boolean idInvalide = false;
		try {
			Artistes artiste = getCurrentModifiedArtiste();		
			artiste.setCreateur(CurrentUser.getId());
			idInvalide = getArtisteWithId(artiste.getId()) == null ? false:true;
			if(!idInvalide) {
				int response = ControlleurBD.AjouterArtiste(artiste);
				
				if(response != ControlleurBD.OK ) {
					if(response == ControlleurBD.UTILISATEUR_EXISTANT) {
						JOptionPane.showMessageDialog(null, "Cet artiste est existant, veuillez en choisir un autre!", "Artiste", JOptionPane.WARNING_MESSAGE);
					}else if(response == ControlleurBD.VALEUR_INVALIDE) {
						JOptionPane.showMessageDialog(null, "Une des valeurs indiqué n'est pas conforme!", "Warning", JOptionPane.ERROR_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Il y a eu une erreur de requète a la BD!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					//l'ajout a la bd à été fait
				}
			}else {
				JOptionPane.showMessageDialog(null, "L'id de l'artiste n'est pas valide!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "L'id de l'artiste n'est pas valide!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public Artistes getCurrentModifiedArtiste() throws Exception {
		Artistes artiste = new Artistes();
		try {
			artiste.setId(Integer.parseInt(ecran_numeroArtiste.getText()));			
			artiste.setNom(ecran_nomArtiste.getText());
			artiste.setMembre(ecran_check_membre.isSelected());
		}catch(Exception e) {
			throw new Exception("l'id n'est pas du texte");
		}
		
		return artiste;
	}
	
	private DefaultTableModel newTableModel() {
//		tableArtistes.setModel(new DefaultTableModel());
		try {
			btnModifier.setEnabled(false);
			btnSupprimer.setEnabled(false);
			btnRemplacer.setEnabled(false);		
		}catch(Exception e) {
			
		}
		
		return new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Nom", "Membre"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, Boolean.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		};
	}
	
	private void clearTable() {
		tableArtistes.setModel(newTableModel());
	}
	
	private Artistes getArtisteWithId(int id) {
		
		ArrayList<Artistes> arrayArtistes = ControlleurBD.GetArtistes();
		for(Artistes currentArtiste : arrayArtistes) {
			if(currentArtiste.getId() == id) {
				return currentArtiste;
			}
		}
		
		return null;
	}
	
	private AbstractListModel newAlbumsModel(String[] tabbedValues) {
		return new AbstractListModel() {
			String[] values = tabbedValues;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		};
	}
	
	private void clearAlbums() {
		this.listAlbums.setModel(newAlbumsModel(new String[] {}));
		this.lblImageArtiste.setIcon(null);
	}
	
	private String[] getAlbumsTableFromArray(ArrayList<Albums> array) {
		String[] table = new String[array.size()];
		for(int i = 0 ; i < array.size(); i++) {
			table[i] = array.get(i).getTitre();
		}
		System.out.println(table[0]);
		return table;
	}
	private void SelectionnerArtiste() {
		setSelectedRowValuesIntoModification();
		ActiverDesactiverModificationArtiste(true);
		btnModifier.setEnabled(true);
		btnSupprimer.setEnabled(true);
		btnRemplacer.setEnabled(true);
		Artistes selectedArtiste = null;
		try {
			selectedArtiste = getArtisteWithId((int)tableArtistes.getModel().getValueAt(tableArtistes.getSelectedRow(), 0));			
		}catch(Exception e) {
			System.out.println("Unable to parse the image from selected artiste");
		}
		if(selectedArtiste.getPhoto() != null) {
			lblImageArtiste.setIcon(new ImageIcon(selectedArtiste.getPhoto().getScaledInstance(lblImageArtiste.getWidth(), lblImageArtiste.getHeight(), Image.SCALE_SMOOTH)));			
		}else {
			lblImageArtiste.setIcon(null); //enleve l'image deja presente
		}
		ArrayList<Albums> array = ControlleurBD.GetAlbums();
		newAlbumsModel(getAlbumsTableFromArray(array));
		listAlbums.setListData(getAlbumsTableFromArray(array));
	}
}
