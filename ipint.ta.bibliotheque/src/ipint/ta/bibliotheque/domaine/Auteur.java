package ipint.ta.bibliotheque.domaine;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Auteur {

	@Id
	private String nom;
	private String prenom;
	@ManyToMany
	private List<Livre> livres = new ArrayList<Livre>();
	
	public String getNom() {
		
		return nom;
	}
	
	public void setNom(String nom) {
		
		this.nom = nom;
	}
	
	public String getPrenom() {
		
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		
		this.prenom = prenom;
	}
	
	public List<Livre> getLivres() {
		
		return livres;
	}	
}
