package ipint.ta.bibliotheque.domaine;

import javax.persistence.Entity;

@Entity
public class Roman extends Livre {

	@Override
	public String toString() {
		return "Roman [" + super.toString() + "]";
	}
}
