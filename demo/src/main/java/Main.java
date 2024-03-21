import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Address;
import model.User;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = emf.createEntityManager();

        // Create a new user with address
        em.getTransaction().begin();
        Address address = new Address();
        address.setStreet("111 Srikakulam");
        address.setCity("City");
        em.persist(address);

        User user = new User();
        user.setName("Mahesh");
        user.setAddress(address);
        em.persist(user);
        em.getTransaction().commit();

        // Retrieve user with address
        User retrievedUser = em.find(User.class, user.getId());
        System.out.println("Retrieved User: " + retrievedUser.getName());
        System.out.println("User's Address: " + retrievedUser.getAddress().getStreet() + ", " + retrievedUser.getAddress().getCity());

        // Close EntityManager
        em.close();
        emf.close();
    }
}
