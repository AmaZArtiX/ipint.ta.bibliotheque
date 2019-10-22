package ipint.ta.bibliotheque.domaine;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public abstract class Livre {

	@Id
	protected String titre;
	protected String description;
	protected Bibliotheque bibliotheque;
	protected Lecteur lecteur;
	@ManyToMany(mappedBy="livres")
	protected List<Auteur> auteurs = new ArrayList<Auteur>();
	
	public String getTitre() {
		
		return titre;
	}
	
	public void setTitre(String titre) {
		
		this.titre = titre;
	}
	
	public String getDescription() {
		
		return description;
	}
	
	public void setDescription(String description) {
		
		this.description = description;
	}

	public Bibliotheque getBibliotheque() {
		
		return bibliotheque;
	}

	public void setBibliotheque(Bibliotheque bibliotheque) {
		
		this.bibliotheque = bibliotheque;
	}

	public Lecteur getLecteur() {
	
		return lecteur;
	}

	public void setLecteur(Lecteur lecteur) {
	
		this.lecteur = lecteur;
	}

	public List<Auteur> getAuteurs() {
		return auteurs;
	}

	@Override
	public String toString() {
		return "titre=" + titre + ", description=" + description + ", ";
	}
}
