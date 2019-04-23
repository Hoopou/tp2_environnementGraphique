package Objects;

public class Utilisateurs {
	
	private Integer id;
	private Object nom;
	private Object mdp;
	private Boolean admin;

	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public Object getNom() {
	return nom;
	}

	public void setNom(Object nom) {
	this.nom = nom;
	}

	public Object getMdp() {
	return mdp;
	}

	public void setMdp(Object mdp) {
	this.mdp = mdp;
	}

	public Boolean getAdmin() {
	return admin;
	}

	public void setAdmin(Boolean admin) {
	this.admin = admin;
	}

}
