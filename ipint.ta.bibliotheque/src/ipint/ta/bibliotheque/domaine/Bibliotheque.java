package ipint.ta.bibliotheque.domaine;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Bibliotheque {

	@Id
	private String nom;
	@OneToMany(mappedBy="bibliotheque")
	private List<Lecteur> lecteurs = new ArrayList<Lecteur>();
	@OneToMany(mappedBy="bibliotheque")
	private List<Livre> livres = new ArrayList<Livre>();
	
	public String getNom() {
		 
		return nom;
	}
	 
	public void setNom(String nom) {
		 
		this.nom = nom;
	}
	 
	public List<Lecteur> getLecteurs() {
		
		return lecteurs;
	}

	public List<Livre> getLivres() {
		return livres;
	}
}
