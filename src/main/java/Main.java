import dao.ElementoCatalogoDao;
import dao.PrestitoDao;
import dao.UtenteDao;
import entities.Libro;
import entities.Prestito;
import entities.Rivista;
import entities.Utente;
import enums.Periodicita;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionecatalogo");
        EntityManager em = emf.createEntityManager();

        ElementoCatalogoDao elementoCatalogoDao = new ElementoCatalogoDao(em);
        UtenteDao utenteDao = new UtenteDao(em);
        PrestitoDao prestitoDao = new PrestitoDao(em);

        //inserimento libri e riviste

        Libro libro1 = new Libro();

        libro1.setAutore("Carlos Ruiz Zafón");
        libro1.setGenere("Romanzo");
        libro1.setTitolo("L'ombra del vento");
        libro1.setAnnoPubblicazione(2001);
        libro1.setNumPagine(400);

        Libro libro2 = new Libro();
        libro2.setAutore("Dan Brown");
        libro2.setGenere("Thriller");
        libro2.setTitolo("Il codice Da Vinci");
        libro2.setAnnoPubblicazione(2003);
        libro2.setNumPagine(350);


        /*elementoCatalogoDao.save(libro1);
         elementoCatalogoDao.save(libro2);*/

        Rivista rivista1 = new Rivista();
        rivista1.setPeriodicita(Periodicita.SETTIMANALE);
        rivista1.setTitolo("Wired");
        rivista1.setAnnoPubblicazione(2024);
        rivista1.setNumPagine(50);

        /* elementoCatalogoDao.save(rivista1);*/

        /*  System.out.println(elementoCatalogoDao.getById("a1bcf9cb-afad-43d5-ba98-528d81f745f8"));*/

//ho fatto che si può passare una stringa, convertita in UUID dalla funzione nel DAO
        Rivista rivistaEsistente = (Rivista) elementoCatalogoDao.getById("cf2fe695-41cd-43e4-bee6-0931a7c17c38");

        /* elementoCatalogoDao.delete(rivistaEsistente.getIsbn());*/

        /*System.out.println(elementoCatalogoDao.getElementoByAnnoPubblicazione(2001));*/

        //cerco per autore
/*
        System.out.println(elementoCatalogoDao.getElementoByAutore("Carlos Ruiz Zafón"));*/

        //cerco per parte del titolo

        /* System.out.println(elementoCatalogoDao.getElementoByTitolo("codice"));*/


        // parte relativa a utente e prestito

        Utente utente1 = new Utente();
        utente1.setNome("Luigi");
        utente1.setCognome("Delle Bicocche");
        utente1.setDataDiNascita(LocalDate.of(1970, 1, 1));
        utente1.setNumeroDiTessera(12345);

        /*utenteDao.save(utente1);*/

        Utente utente2 = new Utente();
        utente2.setNome("Giorgio");
        utente2.setCognome("Vanni");
        utente2.setDataDiNascita(LocalDate.of(1975, 1, 1));
        utente2.setNumeroDiTessera(12346);

        /* utenteDao.save(utente2);*/

        Prestito prestito1 = new Prestito();
        prestito1.setUtente(utente2);
        prestito1.setElementoPrestato(libro1);
        prestito1.setDataInizioPrestito(LocalDate.of(2024, 4, 20));


        /*prestitoDao.save(prestito1);
         */

        //elementi attualmente in prestito di un utente data tessera

        /*  System.out.println(prestitoDao.getElementiInPrestitoByNumTessera(12346));*/


        //cerco elementi in prestito attualmente, non ancora restituiti
        prestitoDao.getPrestitiScaduti().forEach(System.out::println
        );


    }
}
