import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // Initialize EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        // Create EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Persist sample data
        persistSampleData(entityManager);

        // Display sample data
        displaySampleData(entityManager);

        // Close EntityManager
        entityManager.close();

        // Close EntityManagerFactory
        entityManagerFactory.close();
    }

    private static void persistSampleData(EntityManager entityManager) {
        // Begin transaction
        entityManager.getTransaction().begin();

        // Create sample products
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setPrice(10.99);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setPrice(20.99);

        // Persist products
        entityManager.persist(product1);
        entityManager.persist(product2);

        // Commit transaction
        entityManager.getTransaction().commit();
    }

    private static void displaySampleData(EntityManager entityManager) {
        // Query all products
        List<Product> products = entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();

        // Print product details
        System.out.println("Products:");
        for (Product product : products) {
            System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice());
        }
    }
}
