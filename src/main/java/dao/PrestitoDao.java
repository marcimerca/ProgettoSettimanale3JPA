package dao;

import entities.ElementoCatalogo;
import entities.Prestito;
import entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class PrestitoDao {
    private EntityManager em;

    public PrestitoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito prestito) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(prestito);
        et.commit();
    }

    public Prestito getById(int id) {
        return em.find(Prestito.class, id);
    }

    public void delete(int id) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        Prestito prestito = getById(id);

        if (prestito != null) {
            em.remove(prestito);
        } else {
            System.out.println("Prestito non presente");
        }

        et.commit();
    }

    //Query varie
//get elementi in prestito attualmente
    public List<ElementoCatalogo> getElementiByNumeroTessera(int tessera) {
        Query query = em.createQuery("select p.ElementoPrestato from Prestito p where p.utente.numeroDiTessera = :tessera and p.dataRestituzioneEffettiva < p.dataRestituzionePrevista");
        query.setParameter("tessera", tessera);
        return query.getResultList();
    }
}
