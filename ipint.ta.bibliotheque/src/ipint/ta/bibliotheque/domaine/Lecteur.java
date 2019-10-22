package ipint.ta.bibliotheque.domaine;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Lecteur {

	@Id
	private String nom;
	private String prenom;
	private Bibliotheque bibliotheque;
	@OneToMany(mappedBy="lecteur")
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

	public Bibliotheque getBibliotheque() {
		
		return bibliotheque;
	}

	public void setBibliotheque(Bibliotheque bibliotheque) {
		
		this.bibliotheque = bibliotheque;
	}

	public List<Livre> getLivres() {
		
		return livres;
	}

	@Override
	public String toString() {
		return "Lecteur [nom=" + nom + ", prenom=" + prenom + ", bibliotheque=" + bibliotheque.getNom() + "]";
	}
}
