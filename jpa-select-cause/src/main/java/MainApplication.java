import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        // Add five user data entries
        entityManager.getTransaction().begin();
        entityManager.persist(new User("raju", "raju@example.com"));
        entityManager.persist(new User("eswar", "eswar@example.com"));
        entityManager.persist(new User("jagan", "jagan@example.com"));
        entityManager.persist(new User("Eva", "eva@example.com"));
        entityManager.persist(new User("Michael", "michael@example.com"));
        entityManager.getTransaction().commit();

        // Apply Criteria SELECT clause to retrieve specific attributes
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<User> root = query.from(User.class);
        query.select(cb.array(root.get("id"), root.get("name")));

        entityManager.getTransaction().begin();
        List<Object[]> results = entityManager.createQuery(query).getResultList();
        entityManager.getTransaction().commit();

        // Display the retrieved attributes
        for (Object[] result : results) {
            Long userId = (Long) result[0];
            String userName = (String) result[1];
            System.out.println("User ID: " + userId + ", Name: " + userName);
        }

        entityManager.close();
        JPAUtil.shutdown();
    }
}
