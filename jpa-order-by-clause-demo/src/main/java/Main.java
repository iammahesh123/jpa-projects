import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Create an EntityManagerFactory using persistence unit name
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit-name");

        // Create an EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Begin a transaction
        entityManager.getTransaction().begin();


        // Create and persist Employee objects
        Employee employee1 = new Employee("mahesh", "Kadambala", 80000,"IT Department");
        Employee employee2 = new Employee("Eswar", "Beta", 75000, "Sales Department");
        Employee employee3 = new Employee("Jagan", "Mala", 60000,"Testing Department");
        Employee employee4 = new Employee("Raju", "Seepana", 79000, "IT Department");


        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);
        entityManager.persist(employee4);

        // Execute JPQL query to select employees ordered by salary in descending order
        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e ORDER BY e.salary DESC", Employee.class);
        List<Employee> results = query.getResultList();

        // Print the sorted list of employees
        for (Employee employee : results) {
           System.out.println(employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getSalary());
        }

        // Commit the transaction
        entityManager.getTransaction().commit();

        // Close the EntityManager and EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}

