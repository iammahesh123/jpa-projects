import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Book;
import model.Library;

public class MainApplication {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Create a library
        Library library = new Library("Public Library");

        // Create books
        Book book1 = new Book("Introduction to Java", library);
        Book book2 = new Book("Data Structures and Algorithms", library);

        // Associate books with the library
        library.getBooks().add(book1);
        library.getBooks().add(book2);

        // Start transaction
        entityManager.getTransaction().begin();

        // Persist library and books
        entityManager.persist(library);
        entityManager.persist(book1);
        entityManager.persist(book2);

        // Commit transaction
        entityManager.getTransaction().commit();

        // Retrieve library and print books
        Library retrievedLibrary = entityManager.find(Library.class, library.getId());
        System.out.println("Library: " + retrievedLibrary.getName());
        for (Book book : retrievedLibrary.getBooks()) {
            System.out.println("Book: " + book.getTitle());
        }

        // Close EntityManager and EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
