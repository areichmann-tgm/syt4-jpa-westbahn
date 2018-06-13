package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;


import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class Main {

    //private static final Logger log = Logger.getLogger(Main.class);

    private static EntityManagerFactory sessionFactory;
    @PersistenceContext
    private static EntityManager entitymanager;

    static SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");
    static SimpleDateFormat timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");

    private  static  List<Bahnhof> bahnhoefe;

    private Main() {
        super();
    }

    public static void main(String[] args) {
        //Erstellt Datenbank
        EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("westbahn");
        entitymanager= sessionFactory.createEntityManager();
         BasicConfigurator.configure();

         entitymanager.getTransaction().begin();
         Bahnhof b1 = new Bahnhof("WienHbf", 0, 0, 0, true);
         entitymanager.getTransaction().commit();



        try {
            //log.info("Starting \"Mapping Perstistent Classes and Associations\" (task1)");
            sessionFactory = Persistence.createEntityManagerFactory("westbahn");
            entitymanager = sessionFactory.createEntityManager();
            fillDB(entitymanager);
            task01();
            //log.info("Starting \"Working with JPA-QL and the Hibernate Criteria API\" (task2)");
            //log.setLevel(Level.OFF);
            task02();
            task02a();
            task02b();
            task02c();
            //log.setLevel(Level.ALL);
            task03(entitymanager);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entitymanager.getTransaction().isActive())
                //entitymanager.getTransaction().rollback();
            //entitymanager.close();
            sessionFactory.close();
        }
    }

    private static void createData(Session session) {
    }

    public static void fillDB(EntityManager em) throws ParseException {

        //Make Bahnhof
        em.getTransaction().begin();
        bahnhoefe = new ArrayList<Bahnhof>();
        bahnhoefe.add(new Bahnhof("WienHbf", 0, 0, 0, true));
        bahnhoefe.add(new Bahnhof("SalzburgHbf", 20, 60, 120, true));
        bahnhoefe.add(new Bahnhof("Amstetten", 40, 124, 169, false));
        bahnhoefe.add(new Bahnhof("Linz-Ost", 140, 320, 250, false));
        bahnhoefe.add(new Bahnhof("Huetteldorf", 3, 5, 19, false));
        bahnhoefe.add(new Bahnhof("Wels-Zentrum", 102, 400, 250, true));
        for (Bahnhof b : bahnhoefe)
            em.persist(b);
        em.flush();
        em.getTransaction().commit();


        //Make Strecken
        em.getTransaction().begin();
        List<Strecke> strecken = new ArrayList<Strecke>();
        strecken.add(new Strecke(bahnhoefe.get(0),bahnhoefe.get(1)));
        strecken.add(new Strecke(bahnhoefe.get(1),bahnhoefe.get(2)));
        strecken.add(new Strecke(bahnhoefe.get(2),bahnhoefe.get(3)));
        strecken.add(new Strecke(bahnhoefe.get(3),bahnhoefe.get(4)));
        strecken.add(new Strecke(bahnhoefe.get(4),bahnhoefe.get(5)));
        for (Strecke s : strecken)
            em.persist(s);
        em.flush();
        em.getTransaction().commit();

        //Make Tickets and Payments
        em.getTransaction().begin();
        Zahlung maestro = new Maestro();
        Zahlung kreditkarte = new Kreditkarte();
        Zahlung praemien = new Praemienmeilen();

        List<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(new Einzelticket(strecken.get(0), maestro, TicketOption.FAHRRAD));
        tickets.add(new Einzelticket(strecken.get(1), praemien, TicketOption.FAHRRAD));
        tickets.add(new Zeitkarte(strecken.get(2), kreditkarte, new Date(), ZeitkartenTyp.MONATSKARTE));
        tickets.add(new Einzelticket(strecken.get(3), maestro,TicketOption.FAHRRAD));
        tickets.add(new Einzelticket(strecken.get(4), maestro, TicketOption.FAHRRAD));

        for (Ticket t : tickets)
            em.persist(t);
        em.flush();
        em.getTransaction().commit();

        //Make Benutzer
        em.getTransaction().begin();
        List<Benutzer> listeBenutzer = new ArrayList<Benutzer>();
        listeBenutzer.add(new Benutzer("Adrian", "Reichmann", "areichmann@student.tgm.ac.at",";O", "06766969696969", 0L, tickets.get(0)));
        listeBenutzer.add(new Benutzer("Marco", "Gradnitzer", "mgradnitzer@student.tgm.ac.at",";O", "122", 0L, tickets.get(2)));

        for (Benutzer b : listeBenutzer)
            em.persist(b);
        em.flush();
        em.getTransaction().commit();

        //Make Züge
        em.getTransaction().begin();
        List<Zug> zuege = new ArrayList<Zug>();
       zuege.add(new Zug(new Date(),32, 100, 68, bahnhoefe.get(0), bahnhoefe.get(1)));
        for (Zug z : zuege)
            em.persist(z);
        em.flush();
        em.getTransaction().commit();

        //Make Reservierungen
        em.getTransaction().begin();
        List<Reservierung> res = new ArrayList<Reservierung>();
        res.add(new Reservierung(new Date(), 25, 30, StatusInfo.ONTIME, zuege.get(0), strecken.get(0), listeBenutzer.get(0),maestro));
        for (Reservierung r : res)
            em.persist(r);
        em.flush();
        em.getTransaction().commit();


    }


    public static void task01() throws ParseException, InterruptedException {
    }

    public static <T> void task02() throws ParseException {
        //Beispiel Query
        /*
        Query q = entitymanager.createNamedQuery("Bahnhof.getAll");

        List<?> l = q.getResultList();

        for (Object b : l) {
            Bahnhof bhf = null;
            if (b instanceof Bahnhof) {
                bhf = (Bahnhof) b;
                System.out.println("Bahnhof: " + bhf.getName());
            }
        }*/
    }

    public static void task02a() throws ParseException {
        Query q = entitymanager.createNamedQuery("Reservierung.getReservationEmail");
            q.setParameter("eMail", "areichmann@student.tgm.ac.at");
        List<Reservierung> result = q.getResultList();

        for (Reservierung r : result){
            System.out.println(r.getBenutzer().getNachName());
        }

    }

    public static void task02b() throws ParseException {
        Query q = entitymanager.createNamedQuery("getAllUsersWithMonthTicket");
        List<Benutzer> result = q.getResultList();

        for (Benutzer r : result){

            System.out.println(r.getNachName()+" "+r.getVorName() + " ist Besitzer einer Monatskarte");
        }
    }

    public static void task02c() throws ParseException {
        Query q = entitymanager.createNamedQuery("listTicketsForRoute");
        q.setParameter("Start",bahnhoefe.get(0));
        q.setParameter("Ende",bahnhoefe.get(2));
        List<Benutzer> result = q.getResultList();

        for (Benutzer r : result){

            System.out.println(r.getNachName()+" "+r.getVorName() + " ist Besitzer einer Monatskarte");
        }
    }
    }

    public static void task03(EntityManager em) {
    }

    public static void validate(Object obj) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(obj);
        for (ConstraintViolation<Object> violation : violations) {
            //log.error(violation.getMessage());
            System.out.println(violation.getMessage());
        }
    }
}
