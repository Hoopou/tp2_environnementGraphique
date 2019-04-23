package Objects;

public class Albums {
	private Integer id;
	private Object titre;
	private String genre;
	private Object anneeSortie;
	private Object couverture;
	private Integer idArtiste;

	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public Object getTitre() {
	return titre;
	}

	public void setTitre(Object titre) {
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

	public void setAnneeSortie(Object anneeSortie) {
	this.anneeSortie = anneeSortie;
	}

	public Object getCouverture() {
	return couverture;
	}

	public void setCouverture(Object couverture) {
	this.couverture = couverture;
	}

	public Integer getIdArtiste() {
	return idArtiste;
	}

	public void setIdArtiste(Integer idArtiste) {
	this.idArtiste = idArtiste;
	}
}
