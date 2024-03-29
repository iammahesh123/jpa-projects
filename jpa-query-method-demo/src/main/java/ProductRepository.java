import java.util.List;

public interface ProductRepository {
    // Query Method to find products by name
    List<Product> findByName(String name);

    // Query Method to find products by price less than a given value
    List<Product> findByPriceLessThan(double price);

    // Query Method to count products by name
    long countByName(String name);
}
