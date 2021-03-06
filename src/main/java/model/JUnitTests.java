package model;


 import javax.persistence.*;
 import javax.validation.ConstraintViolation;
 import javax.validation.Validation;
 import javax.validation.Validator;
 import javax.validation.ValidatorFactory;

 import static org.junit.Assert.assertEquals;
 import static org.junit.Assert.assertFalse;
 import static org.junit.Assert.assertTrue;

 import org.apache.log4j.BasicConfigurator;
 import org.junit.BeforeClass;
 import org.junit.Test;
        import org.junit.FixMethodOrder;
        import org.junit.runners.MethodSorters;

 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import java.util.Set;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitTests {
        private  static List<Bahnhof> bahnhoefe;
        private static List<Strecke> strecken;
        private  static List<Zug> zuege;
        private static List<Reservierung> res;
        private static List<Ticket> tickets;

       private static EntityManagerFactory sessionFactory;
       @PersistenceContext
       private static EntityManager entitymanager;

       @BeforeClass
       public static void setUp() {
           EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("westbahn");
           entitymanager= sessionFactory.createEntityManager();
           BasicConfigurator.configure();

           entitymanager.getTransaction().begin();
           bahnhoefe = new ArrayList<Bahnhof>();
           bahnhoefe.add(new Bahnhof("WienHbf", 0, 0, 0, true));
           bahnhoefe.add(new Bahnhof("SalzburgHbf", 20, 60, 120, true));
           bahnhoefe.add(new Bahnhof("Amstetten", 40, 124, 169, false));
           bahnhoefe.add(new Bahnhof("Linz-Ost", 140, 320, 250, false));
           bahnhoefe.add(new Bahnhof("Huetteldorf", 3, 5, 19, false));
           bahnhoefe.add(new Bahnhof("Wels-Zentrum", 102, 400, 250, true));
           for (Bahnhof b : bahnhoefe)
               entitymanager.persist(b);
           entitymanager.flush();
           entitymanager.getTransaction().commit();

           entitymanager.getTransaction().begin();
           strecken = new ArrayList<Strecke>();
           strecken.add(new Strecke(bahnhoefe.get(0),bahnhoefe.get(1)));
           strecken.add(new Strecke(bahnhoefe.get(1),bahnhoefe.get(2)));
           strecken.add(new Strecke(bahnhoefe.get(2),bahnhoefe.get(3)));
           strecken.add(new Strecke(bahnhoefe.get(3),bahnhoefe.get(4)));
           strecken.add(new Strecke(bahnhoefe.get(4),bahnhoefe.get(5)));
           for (Strecke s : strecken)
               entitymanager.persist(s);
           entitymanager.flush();
           entitymanager.getTransaction().commit();

           //Make Tickets and Payments
           entitymanager.getTransaction().begin();
           Zahlung maestro = new Maestro();
           Zahlung kreditkarte = new Kreditkarte();
           Zahlung praemien = new Praemienmeilen();

           tickets = new ArrayList<Ticket>();
           tickets.add(new Einzelticket(strecken.get(0), maestro, TicketOption.FAHRRAD));
           tickets.add(new Einzelticket(strecken.get(0), maestro, TicketOption.FAHRRAD));
           tickets.add(new Einzelticket(strecken.get(1), praemien, TicketOption.FAHRRAD));
           tickets.add(new Zeitkarte(strecken.get(2), kreditkarte, new Date(), ZeitkartenTyp.MONATSKARTE));
           tickets.add(new Einzelticket(strecken.get(3), maestro,TicketOption.FAHRRAD));
           tickets.add(new Einzelticket(strecken.get(4), maestro, TicketOption.FAHRRAD));

           for (Ticket t : tickets)
               entitymanager.persist(t);
           entitymanager.flush();
           entitymanager.getTransaction().commit();

           //Make Benutzer
           entitymanager.getTransaction().begin();
           List<Benutzer> listeBenutzer = new ArrayList<Benutzer>();
           listeBenutzer.add(new Benutzer("Adrian", "Reichmann", "areichmann@student.tgm.ac.at",";O", "06766969696969", 0L, tickets.get(0)));
           listeBenutzer.add(new Benutzer("Marco", "Gradnitzer", "mgradnitzer@student.tgm.ac.at",";O", "122", 0L, tickets.get(3)));
           listeBenutzer.add(new Benutzer("Heute", "Schwedenplatz?", "saufen@student.tgm.ac.at","Lookbar", "144", 0L, tickets.get(1)));
           for (Benutzer b : listeBenutzer)
               entitymanager.persist(b);
           entitymanager.flush();
           entitymanager.getTransaction().commit();

           //Make Züge
           entitymanager.getTransaction().begin();
           zuege = new ArrayList<Zug>();
           zuege.add(new Zug(new Date(),32, 100, 68, bahnhoefe.get(0), bahnhoefe.get(1)));
           for (Zug z : zuege)
               entitymanager.persist(z);
           entitymanager.flush();
           entitymanager.getTransaction().commit();

           //Make Reservierungen
           entitymanager.getTransaction().begin();
           res = new ArrayList<Reservierung>();
           res.add(new Reservierung(new Date(), 25, 30, StatusInfo.ONTIME, zuege.get(0), strecken.get(0), listeBenutzer.get(0),maestro));
           //res.add(new Reservierung(new Date(), 25, 30, StatusInfo.ONTIME, zuege.get(0), strecken.get(0), listeBenutzer.get(2),kreditkarte));
           for (Reservierung r : res)
               entitymanager.persist(r);
           entitymanager.flush();
           entitymanager.getTransaction().commit();

       }

    @Test
    public void checkPersistency() {
        Bahnhof testing = new Bahnhof("Langenzersdorf", 100, 100, 100, true);
        entitymanager.persist(testing);
        Query q = entitymanager.createQuery("select b from Bahnhof b where b.name = 'Langenzersdorf'");
        List<Bahnhof> l = q.getResultList();
        for (Bahnhof b : l)
            assertTrue(testing.equals(b));
    }

    @Test
    public void checkPersistency_2() {
        Benutzer test = new Benutzer("Marco liebt", "Gradi?", "saufen@student.tgm.ac.at","lit", "144", 0L, tickets.get(1));
        entitymanager.persist(test);
        Query q = entitymanager.createQuery("select b from Benutzer b where b.nachName = 'Gradi?'");
        List<Benutzer> l = q.getResultList();
        for (Benutzer b: l)
            assertTrue(b.equals(test));
    }

    @Test
    public void query2b() {
        Query q = entitymanager.createQuery("select b from Benutzer b  JOIN  b.tickets t WHERE t.typ = 1 ");
        List<Benutzer> l = q.getResultList();
        for (Benutzer b: l)
            assertTrue(b.getVorName().equals("Marco"));
    }

    @Test
    public void query2a() {
        Query q = entitymanager.createQuery(" select x from Reservierung x where x.benutzer.eMail = :eMail ");
        q.setParameter("eMail", "areichmann@student.tgm.ac.at");
        List<Reservierung> l = q.getResultList();
        for (Reservierung b: l)
            assertTrue(b.getBenutzer().getNachName().equals("Reichmann"));
    }

    @Test
    public void query2a2() {
        Query q = entitymanager.createQuery(" select x from Reservierung x where x.benutzer.eMail = :eMail ");
        q.setParameter("eMail", "mgradnitzer@student.tgm.ac.at");
        List<Reservierung> l = q.getResultList();
        for (Reservierung b: l)
            assertFalse(b.getBenutzer().getNachName().equals("Reichmann"));
    }

    @Test
    public void query2b2() {
        Query q = entitymanager.createQuery("select b from Benutzer b  JOIN  b.tickets t WHERE t.typ = 1 ");
        List<Benutzer> l = q.getResultList();
        for (Benutzer b: l)
            assertFalse(b.getVorName().equals("Hans"));
    }
    @Test
    public void testValidationError() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Bahnhof z = new Bahnhof("e", 0, 0, 0, true);
        Set<ConstraintViolation<Bahnhof>> violations = validator.validate(z);
        for (ConstraintViolation<Bahnhof> violation : violations) {
            assertTrue(violation.getMessage().equals("Bahnhofname muss mindestens 2 und maximal 150 Zeichen lang sein!"));
        }

    }
    @Test
    public void testValidationError2() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Benutzer z = new Benutzer("Marco liebt", "Gradi?", "FALSE","lit", "144", 0L, tickets.get(1));
        Set<ConstraintViolation<Benutzer>> violations = validator.validate(z);
        for (ConstraintViolation<Benutzer> violation : violations) {
            assertTrue(violation.getMessage().equals("E-Mail Adresse muss korrekt sein"));
        }

    }
    @Test
    public void testValidationError3() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Strecke s = new Strecke(bahnhoefe.get(0),bahnhoefe.get(0));
        Set<ConstraintViolation<Strecke>> violations = validator.validate(s);
        for (ConstraintViolation<Strecke> violation : violations) {
            assertTrue(violation.getMessage().equals("Start und Ende dürfen nicht gleich sein!"));
        }

    }




}
