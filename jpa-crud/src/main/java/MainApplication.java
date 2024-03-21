import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Employee;

import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnitName");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            for (int i = 1; i <= 5; i++) {
                Employee emp = new Employee();
                emp.setName("Employee " + i); // Setting unique names for each employee
                emp.setSalary(50000 + (i * 1000)); // Incrementing salary for each employee
                em.persist(emp);
            }
            transaction.commit();

            // Read Operation: Print all employees
            transaction.begin(); // Begin a new transaction
            List<Employee> allEmployees = em.createQuery("SELECT e FROM Employee e", Employee.class)
                    .getResultList();
            System.out.println("All Employees:");
            for (Employee employee : allEmployees) {
                System.out.println(employee.getName() + " - " + employee.getSalary());
            }
            transaction.commit(); // Commit the transaction after reading

            // Update Operation: Update two employees
            transaction.begin(); // Begin a new transaction
            Employee employeeToUpdate1 = em.find(Employee.class, 1L); // Assuming ID 1 and 2 for updating
            employeeToUpdate1.setSalary(employeeToUpdate1.getSalary() + 5000); // Increasing salary
            Employee employeeToUpdate2 = em.find(Employee.class, 2L);
            employeeToUpdate2.setSalary(employeeToUpdate2.getSalary() + 5000); // Increasing salary
            em.merge(employeeToUpdate1);
            em.merge(employeeToUpdate2);
            transaction.commit(); // Commit the transaction after updating

            // Read Operation: Print updated employees
            transaction.begin(); // Begin a new transaction
            List<Employee> updatedEmployees = em.createQuery("SELECT e FROM Employee e WHERE e.id IN (1, 2)", Employee.class)
                    .getResultList();
            System.out.println("\nUpdated Employees:");
            for (Employee employee : updatedEmployees) {
                System.out.println(employee.getName() + " - " + employee.getSalary());
            }
            transaction.commit(); // Commit the transaction after reading

            // Delete Operation
            transaction.begin(); // Begin a new transaction
            for (Employee employee : updatedEmployees) {
                em.remove(employee);
            }
            transaction.commit(); // Commit the transaction after deletion
        } finally {
            em.close();
            emf.close();
        }
    }
}
