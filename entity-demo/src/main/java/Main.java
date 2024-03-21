import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        // Obtain EntityManager instance
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Create and persist Product entity
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(1000.0);
        em.persist(product);

        // Commit transaction
        em.getTransaction().commit();

        // Retrieve the persisted Product entity
        Product persistedProduct = em.find(Product.class, product.getId());

        // Print the details of the persisted Product entity
        System.out.println("Inserted Product Details:");
        System.out.println("ID: " + persistedProduct.getId());
        System.out.println("Name: " + persistedProduct.getName());
        System.out.println("Price: " + persistedProduct.getPrice());

        // Close EntityManager
        em.close();
        emf.close();
    }
}
