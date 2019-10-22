package ipint.ta.bibliotheque.domaine;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Entity
@Inheritance
public class BD extends Livre {

	private String serie;

	public String getSerie() {
		
		return serie;
	}

	public void setSerie(String serie) {
		
		this.serie = serie;
	}

	@Override
	public String toString() {
		
		return "BD [" + super.toString() + "serie=" + serie + "]";
	}
}
