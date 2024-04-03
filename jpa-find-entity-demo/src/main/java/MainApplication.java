import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Product;

import java.math.BigDecimal;

public class MainApplication {
    public static void main(String[] args) {
        // Creating EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
        EntityManager em = emf.createEntityManager();

        // Adding sample products
        em.getTransaction().begin();
        Product product1 = new Product(1L, "iPhone",new BigDecimal("1250000"));
        Product product2 = new Product(2L, "Samsung Galaxy",new BigDecimal("125800"));
        em.persist(product1);
        em.persist(product2);
        em.getTransaction().commit();

        // Finding entity
        Product foundProduct = em.find(Product.class, 1L);
        if (foundProduct != null) {
            System.out.println("Product found: " + foundProduct.getName());
        } else {
            System.out.println("Product not found.");
        }

        // Closing EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}
