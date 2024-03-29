import jakarta.persistence.*;
import model.Address;
import model.Person;

public class MainApplication {
    public static void main(String[] args) {
        // Create EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = emf.createEntityManager();

        // Begin transaction
        em.getTransaction().begin();

        // Create a person
        Person person = new Person();
        person.setName("Mahesh");
        em.persist(person);

        // Create an address
        Address address = new Address();
        address.setPerson(person);
        address.setCity("Hyderabad");
        em.persist(address);

        // Commit transaction
        em.getTransaction().commit();

        // Retrieve person and address
        Person retrievedPerson = em.find(Person.class, person.getId());

        // Check if person and associated address exist
        if (retrievedPerson != null && retrievedPerson.getAddress() != null) {
            Address retrievedAddress = retrievedPerson.getAddress();
            System.out.println("Person: " + retrievedPerson.getName());
            System.out.println("Address: " + retrievedAddress.getCity());
        } else {
            System.out.println("Person or associated Address not found.");
        }

        // Close EntityManager
        em.close();
        emf.close();
    }
}


