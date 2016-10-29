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
        final User johnDoe = new UserBuilder()
                .setEmail("john.doe@gmail.com")
                .setPassword("password")
                .setPrenom("John")
                .setNom("Doe")
                .build();

        final User richardRoe = new UserBuilder()
                .setEmail("richard.roe@gmail.com")
                .setPassword("password")
                .setPrenom("Richard")
                .setNom("Roe")
                .build();

        performOperation(em -> {
            em.persist(johnDoe);
            em.persist(richardRoe);
        });

        performOperation(em -> {
            em.persist(new EventBuilder(johnDoe).setLabel("Premier événement").build());
            em.persist(new EventBuilder(johnDoe).setLabel("Deuxième événement").build());
            em.persist(new EventBuilder(richardRoe).setLabel("Troisième événement").build());
            em.persist(new EventBuilder(johnDoe).setLabel("Quatre événement").build());
            em.persist(new EventBuilder(johnDoe).setLabel("Cinq événement").build());
            em.persist(new EventBuilder(richardRoe).setLabel("Sixieme événement").build());
            em.persist(new EventBuilder(johnDoe).setLabel("Sept événement").build());
            em.persist(new EventBuilder(johnDoe).setLabel("Huit événement").build());
            em.persist(new EventBuilder(richardRoe).setLabel("Neuf événement").build());
            em.persist(new EventBuilder(johnDoe).setLabel("Dix événement").build());
            em.persist(new EventBuilder(johnDoe).setLabel("Onze événement").build());
            em.persist(new EventBuilder(richardRoe).setLabel("Douze événement").build());
            em.persist(new EventBuilder(johnDoe).setLabel("Treize événement").build());
            em.persist(new EventBuilder(johnDoe).setLabel("Quatorze événement").build());
            em.persist(new EventBuilder(richardRoe).setLabel("Quinze événement").build());
        });

    }
}
