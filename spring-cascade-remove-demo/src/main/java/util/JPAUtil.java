package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("example-persistence-unit");
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        entityManagerFactory.close();
    }
}
