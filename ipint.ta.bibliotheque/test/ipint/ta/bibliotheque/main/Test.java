package ipint.ta.bibliotheque.main;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import ipint.ta.bibliotheque.domaine.Auteur;
import ipint.ta.bibliotheque.domaine.BD;
import ipint.ta.bibliotheque.domaine.Bibliotheque;
import ipint.ta.bibliotheque.domaine.Lecteur;
import ipint.ta.bibliotheque.domaine.Livre;
import ipint.ta.bibliotheque.domaine.Roman;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test {

	private static final String PERSISTENCE_UNIT_NAME = "bibliotheque";
    private EntityManagerFactory factory;
    
    @Before
    public void initFactory() {
    	
    	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    
	public void creerLivresAuteurs() {
		
		
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        
        // Creation de la bibliotheque
        Bibliotheque bibliotheque = new Bibliotheque();
        bibliotheque.setNom("Bibliotheque municipale");
        em.persist(bibliotheque);
        
        // Creation BD1
        BD bd1 = new BD();
        bd1.setTitre("Asterix Le Gaulois");
        bd1.setDescription("La toute première histoire d'Astérix, pour faire connaissance avec la troupe des irréductibles Gaulois.");
        bd1.setSerie("Asterix");
        bd1.setBibliotheque(bibliotheque);
    	em.persist(bd1);
    	bibliotheque.getLivres().add(bd1);
    	
    	// Creation Auteur1
    	Auteur a1 = new Auteur();
    	a1.setNom("Goscinny");
    	a1.setPrenom("Rene");
    	a1.getLivres().add(bd1);
    	em.persist(a1);
    	bd1.getAuteurs().add(a1);
    	em.persist(bd1);
    	
    	// Creation Auteur2
    	Auteur a2 = new Auteur();
    	a2.setNom("Uderzo");
    	a2.setPrenom("Albert");
    	a2.getLivres().add(bd1);
    	em.persist(a2);
    	bd1.getAuteurs().add(a2);
    	em.persist(bd1);
    	
    	// Creation BD2
    	BD bd2 = new BD();
    	bd2.setTitre("Asterix et Cleopatre");
    	bd2.setDescription("Cléopâtre fait le pari avec César que son peuple est encore capable de grandes réalisations. Elle ordonne à Numérobis de construire un palais somptueux pour César.à partir de 6 ans.");
    	bd2.setSerie("Asterix");
    	bd2.setBibliotheque(bibliotheque);
    	bd2.getAuteurs().add(a1);
    	bd2.getAuteurs().add(a2);
    	em.persist(bd2);
    	bibliotheque.getLivres().add(bd2);
    	
    	a1.getLivres().add(bd2);
    	em.persist(a1);
    	a2.getLivres().add(bd2);
    	em.persist(a2);
    	
    	//Creation Roman1
    	Roman r1 = new Roman();
    	r1.setTitre("Voyage au centre de la Terre");
    	r1.setDescription("Une périlleuse expédition ponctuée de découvertes extraordinaires et de créatures fantastiques...");
    	r1.setBibliotheque(bibliotheque);
    	em.persist(r1);
    	bibliotheque.getLivres().add(r1);
    	
    	// Creation Auteur3
    	Auteur a3 = new Auteur();
    	a3.setNom("Jules");
    	a3.setPrenom("Verne");
    	a3.getLivres().add(r1);
    	em.persist(a3);
    	r1.getAuteurs().add(a3);
    	em.persist(r1);
    	
    	em.persist(bibliotheque);
        
        em.getTransaction().commit();

        em.close();
	}
	
	// Affichage des livres de la bibliotheque
	@org.junit.Test
	public void testA() {
		
		creerLivresAuteurs();
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("SELECT l FROM Livre l WHERE l.bibliotheque.nom = :param");
		q.setParameter("param", "Bibliotheque municipale");
		
		assertTrue(q.getResultList().size() == 3);
			
		System.out.println("\nTEST A : Affichage des livres de la bibliothèque\n");
		
		for(int i = 0; i < q.getResultList().size(); i++) {
			
			Livre l = (Livre) q.getResultList().get(i);
			System.out.println(l);
		}
		
		em.close();
	}
	
	
	public void creerLecteurs() {
		
		EntityManager em = factory.createEntityManager();
	
		em.getTransaction().begin();
		
		Query q = em.createQuery("SELECT b FROM Bibliotheque b");
		assertTrue(q.getResultList().size() == 1);
		
		Bibliotheque b = (Bibliotheque) q.getResultList().get(0);
		
		Lecteur l1 = new Lecteur();
		l1.setNom("Errami");
		l1.setPrenom("Jaja");
		l1.setBibliotheque(b);
		em.persist(l1);
		b.getLecteurs().add(l1);
		
		Lecteur l2 = new Lecteur();
		l2.setNom("Chtairi");
		l2.setPrenom("Yacine");
		l2.setBibliotheque(b);
		em.persist(l2);
		b.getLecteurs().add(l2);
		
		Lecteur l3 = new Lecteur();
		l3.setNom("Hego");
		l3.setPrenom("Julien");
		l3.setBibliotheque(b);
		em.persist(l3);
		b.getLecteurs().add(l3);
		
		em.persist(b);
		
		q = em.createQuery("SELECT l FROM Livre l WHERE l.bibliotheque.nom = :param");
		q.setParameter("param", "Bibliotheque municipale");
		
		Livre li1 =  (Livre) q.getResultList().get(0);
		Livre li2 =  (Livre) q.getResultList().get(1);
		Livre li3 =  (Livre) q.getResultList().get(2);
		
		l1.getLivres().add(li1);
		l1.getLivres().add(li2);
		em.persist(l1);
		li1.setLecteur(l1);
		li2.setLecteur(l1);
		em.persist(li1);
		em.persist(li2);
		
		l3.getLivres().add(li3);
		em.persist(l3);
		li3.setLecteur(l3);
		em.persist(li3);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	// Affichage des emprunts de chaque lecteur
	@org.junit.Test
	public void testB() {
		
		creerLecteurs();	
		
		EntityManager em = factory.createEntityManager();
		
		Query q = em.createQuery("SELECT l FROM Lecteur l WHERE l.bibliotheque.nom = :param");
		q.setParameter("param", "Bibliotheque municipale");
		assertTrue(q.getResultList().size() == 3);
		
		System.out.println("\nTEST B : Affichage des emprunts de chaque lecteur\n")
		;
		for(int i = 0; i < q.getResultList().size(); i++) {
			
			Lecteur lecteur = (Lecteur) q.getResultList().get(i);
			
			if(lecteur.getLivres().size() > 0) {
				
				System.out.println(lecteur + " a emprunté " + lecteur.getLivres().size() + " livre(s)");
				
				for(Livre l : lecteur.getLivres()) {
					
					System.out.println(l);
				}
				
			} else {
				
				System.out.println(lecteur + " n'a pas encore emprunté de livres");
			}
			
			System.out.println();
			
		}
	}
}
