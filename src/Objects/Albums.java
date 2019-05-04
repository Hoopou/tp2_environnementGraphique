package Objects;

import java.awt.image.BufferedImage;

public class Albums {
	private Integer id;
	private String titre;
	private String genre;
	private String anneeSortie;
	private BufferedImage couverture;
	private Integer idArtiste;

	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public String getTitre() {
	return titre;
	}

	public void setTitre(String titre) {
	this.titre = titre;
	}

	public String getGenre() {
	return genre;
	}

	public void setGenre(String genre) {
	this.genre = genre;
	}

	public Object getAnneeSortie() {
	return anneeSortie;
	}

	public void setAnneeSortie(String date) {
	this.anneeSortie = date;
	}

	public BufferedImage getCouverture() {
	return couverture;
	}

	public void setCouverture(BufferedImage couverture) {
		try {
			this.couverture = couverture;			
		}catch(Exception e) {
			this.couverture = null;
		}
	}

	public Integer getIdArtiste() {
	return idArtiste;
	}

	public void setIdArtiste(Integer idArtiste) {
	this.idArtiste = idArtiste;
	}
	
}
