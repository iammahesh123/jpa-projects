import jakarta.persistence.EntityManager;
import model.Department;
import model.Employee;

public class MainApplication {
    public static void main(String[] args) {
        // Create entities
        Department department = new Department();
        department.setName("Engineering");

        Employee emp1 = new Employee();
        emp1.setName("Raju");

        Employee emp2 = new Employee();
        emp2.setName("Eswar");

        // Associate employees with the department
        emp1.setDepartment(department);
        emp2.setDepartment(department);

        department.getEmployees().add(emp1);
        department.getEmployees().add(emp2);

        // Persist entities
        EntityManager entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(department);
        entityManager.persist(emp1);
        entityManager.persist(emp2);

        entityManager.getTransaction().commit();

        // Close EntityManager
        entityManager.close();

        // Retrieve and print the persisted data
        entityManager = JpaUtil.getEntityManager();
        Department retrievedDepartment = entityManager.find(Department.class, department.getId());
        System.out.println("Department: " + retrievedDepartment.getName());
        System.out.println("Employees:");
        for (Employee employee : retrievedDepartment.getEmployees()) {
            System.out.println("- " + employee.getName());
        }

        // Close EntityManager
        entityManager.close();

        // Close EntityManagerFactory
        JpaUtil.close();
    }
}
