import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Employee;

import java.util.HashMap;
import java.util.Map;

public class MainApplication {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Create and persist employee
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Mahesh");

        Map<String, String> departments = new HashMap<>();
        departments.put("IT", "Information Technology");
        departments.put("HR", "Human Resources");

        employee.setDepartments(departments);

        em.persist(employee);

        em.getTransaction().commit();

        // Retrieve employee from database
        Employee retrievedEmployee = em.find(Employee.class, 1L);

        // Output retrieved employee details
        if (retrievedEmployee != null) {
            System.out.println("Retrieved Employee:");
            System.out.println("ID: " + retrievedEmployee.getId());
            System.out.println("Name: " + retrievedEmployee.getName());
            System.out.println("Departments:");
            for (Map.Entry<String, String> entry : retrievedEmployee.getDepartments().entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("Employee not found.");
        }

        em.close();
        emf.close();
    }
}
