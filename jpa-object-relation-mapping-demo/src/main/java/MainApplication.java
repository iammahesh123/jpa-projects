import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Product;

public class MainApplication {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = emf.createEntityManager();

        // Create
        em.getTransaction().begin();
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(1000.0);
        em.persist(product);
        em.getTransaction().commit();

        // Read
        Product retrievedProduct = em.find(Product.class, 1L);
        System.out.println(retrievedProduct.getName()); // Output: Laptop

        // Update
        em.getTransaction().begin();
        retrievedProduct.setPrice(1200.0);
        em.getTransaction().commit();

        // Delete
        em.getTransaction().begin();
        em.remove(retrievedProduct);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}

