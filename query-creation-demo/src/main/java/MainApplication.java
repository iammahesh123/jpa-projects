import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
//import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("examplePU");
        EntityManager em = emf.createEntityManager();

        // Create and persist some users
        em.getTransaction().begin();
        User user1 = new User("Raju", "rajukumar@example.com");
        User user2 = new User("Eswar", "eswar@example.com");
        em.persist(user1);
        em.persist(user2);
        em.getTransaction().commit();

        // Query users by username
        em.getTransaction().begin();
        TypedQuery<User> queryByUsername = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", "JohnDoe");
        List<User> usersByUsername = queryByUsername.getResultList();
        if (!usersByUsername.isEmpty()) {
            for (User user : usersByUsername) {
                System.out.println("User found by username: " + user);
            }
        } else {
            System.out.println("No user found with the specified username.");
        }

        // Query users by email domain
        TypedQuery<User> queryByEmail = em.createQuery("SELECT u FROM User u WHERE u.email LIKE :domain", User.class)
                .setParameter("domain", "%example.com");
        List<User> usersByEmail = queryByEmail.getResultList();
        if (!usersByEmail.isEmpty()) {
            for (User user : usersByEmail) {
                System.out.println("User found by email domain: " + user);
            }
        } else {
            System.out.println("No user found with the specified email domain.");
        }
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
