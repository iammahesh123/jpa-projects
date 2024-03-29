import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Create and persist sample products
        Product product1 = new Product();
        product1.setName("Laptop");
        product1.setPrice(1000.00);
        em.persist(product1);

        Product product2 = new Product();
        product2.setName("Mobile Phone");
        product2.setPrice(500.00);
        em.persist(product2);

        // Commit transaction
        em.getTransaction().commit();

        // Retrieve products using Query Methods
        List<Product> laptopProducts = findProductsByName(em, "Laptop");
        System.out.println("Products with name 'Laptop': " + laptopProducts);

        List<Product> affordableProducts = findProductsByPriceLessThan(em, 700.00);
        System.out.println("Affordable products (price less than $700): " + affordableProducts);

        long laptopCount = countProductsByName(em, "Laptop");
        System.out.println("Count of products with name 'Laptop': " + laptopCount);

        // Close EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }

    // Query Methods Implementation
    public static List<Product> findProductsByName(EntityManager em, String name) {
        return em.createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public static List<Product> findProductsByPriceLessThan(EntityManager em, double price) {
        return em.createQuery("SELECT p FROM Product p WHERE p.price < :price", Product.class)
                .setParameter("price", price)
                .getResultList();
    }

    public static long countProductsByName(EntityManager em, String name) {
        return em.createQuery("SELECT COUNT(p) FROM Product p WHERE p.name = :name", Long.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
