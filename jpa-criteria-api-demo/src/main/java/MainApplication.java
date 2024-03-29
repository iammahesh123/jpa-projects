import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import model.Employee;

import java.util.List;

public class MainApplication {

    public static void main(String[] args) {
        // Create EntityManagerFactory and EntityManager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // Start a transaction
            entityManager.getTransaction().begin();

            // Insert sample employee data
            persistSampleData(entityManager);

            // Commit the transaction
            entityManager.getTransaction().commit();

            // Construct a CriteriaBuilder
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            // Create a CriteriaQuery for Employee objects
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

            // Set the root of the query
            Root<Employee> root = criteriaQuery.from(Employee.class);

            // Specify the selection
            criteriaQuery.select(root);

            // Create a predicate for salary greater than 50000
            Predicate salaryGreaterThan = criteriaBuilder.greaterThan(root.get("salary"), 50000);

            // Set the WHERE clause
            criteriaQuery.where(salaryGreaterThan);

            // Create a TypedQuery based on the CriteriaQuery
            TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);

            // Execute the query and retrieve the result list
            List<Employee> employees = typedQuery.getResultList();
            System.out.println("Employees names of the salaries geater than 50000");

            // Print the names of employees with salary greater than 50000
            employees.forEach(employee -> System.out.println(employee.getName()));
        } finally {
            // Close EntityManager and EntityManagerFactory
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    // Method to insert sample employee data
    private static void persistSampleData(EntityManager entityManager) {
        Employee employee1 = new Employee("Raju Kumar", 60000);
        Employee employee2 = new Employee("Eswar", 75000);
        Employee employee3 = new Employee("Jagan", 55000);
        Employee employee4 = new Employee("Syam", 85000);
        Employee employee5 = new Employee("Mukes", 35000);

        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);
        entityManager.persist(employee4);
        entityManager.persist(employee5);
    }
}
