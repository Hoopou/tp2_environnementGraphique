package Objects;

import java.awt.image.BufferedImage;

public class Artistes {
	private Integer id;
	private String nom;
	private Boolean membre;
	private BufferedImage photo = null;
	private int createur;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Boolean getMembre() {
		return membre;
	}

	public void setMembre(Boolean membre) {
		this.membre = membre;
	}

	public BufferedImage getPhoto() {
		return photo;
	}

	public void setPhoto(BufferedImage photo) {
		try {
			this.photo = photo;
		} catch (Exception e) {
			this.photo = null;
		}
	}

	public int getCreateur() {
		return createur;
	}

	public void setCreateur(int createur) {
		this.createur = createur;
	}
}
