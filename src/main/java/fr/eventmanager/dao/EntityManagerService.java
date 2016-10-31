package fr.eventmanager.dao;

import fr.eventmanager.builder.EventBuilder;
import fr.eventmanager.builder.UserBuilder;
import fr.eventmanager.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Generic entity manager service to perform jpa operations & queries,
 * and handle back-end management of jpa resources.
 */
public class EntityManagerService {
    public static final String PERSISTENCE_UNIT = "EventManager";
    private static EntityManagerService instance = null;

    private final EntityManagerFactory factory;
    private final EntityManager manager;

    private EntityManagerService(String persistenceUnit) {
        this.factory = Persistence.createEntityManagerFactory(persistenceUnit);
        this.manager = factory.createEntityManager();
    }

    public static EntityManagerService getInstance() {
        if (instance == null) {
            instance = new EntityManagerService(PERSISTENCE_UNIT);
            instance.populate();
        }
        return instance;
    }

    public synchronized <T> T performQuery(Function<EntityManager, T> query) {
        return query.apply(this.manager);
    }

    public synchronized void performOperation(Consumer<EntityManager> operation) {
        this.manager.getTransaction().begin();
        operation.accept(this.manager);
        this.manager.getTransaction().commit();
    }

    private synchronized void populate() {
        final User romainDavaze = new UserBuilder("romain.davaze@gmail.com", "password", "Romain", "Davaze")
                .setCompany("Sigma")
                .build();

        final User guillaumeChanson = new UserBuilder("guillaume.chanson@gmail.com", "password", "Guillaume", "Chanson")
                .setCompany("Nantes Métropole")
                .build();

        final User florineCercle = new UserBuilder("florine.cercle@gmail.com", "password", "Florine", "Cerclé")
                .setCompany("Sopra Steria")
                .build();

        final User johnDoe = new UserBuilder("john.doe@gmail.com", "password", "John", "Doe")
                .setCompany("OpenJDK")
                .build();

        final User laurentGuerin = new UserBuilder("laurent.guerin@gmail.com", "password", "Laurent", "Guerin")
                .setCompany("Teacher")
                .build();


        performOperation(em -> {
            em.persist(romainDavaze);
            em.persist(guillaumeChanson);
            em.persist(florineCercle);
            em.persist(laurentGuerin);
            em.persist(johnDoe);
        });

        performOperation(em -> {
            em.persist(new EventBuilder("Fête de la musique", guillaumeChanson).setVisible(true).build());
            em.persist(new EventBuilder("Laser Game", romainDavaze).build());
            em.persist(new EventBuilder("Avant-première", florineCercle).addAttendee(romainDavaze).setVisible(true).build());
            em.persist(new EventBuilder("Vide-grenier", guillaumeChanson).build());
            em.persist(new EventBuilder("Fête des fleurs", florineCercle).build());
            em.persist(new EventBuilder("Shopping à l'Apple Store", romainDavaze).build());
            em.persist(new EventBuilder("Concert Parov Stelar", guillaumeChanson).build());
            em.persist(new EventBuilder("Concours Patisserie", romainDavaze).build());
            em.persist(new EventBuilder("Salons des Vins", romainDavaze).build());
            em.persist(new EventBuilder("Pyjama party", florineCercle).build());
            em.persist(new EventBuilder("Anniversaire de Guillaume", guillaumeChanson).build());

        });
    }
}
