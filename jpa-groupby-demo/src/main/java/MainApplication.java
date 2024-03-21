import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import model.Department;
import model.Employees;
import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        // Create an EntityManagerFactory using persistence unit name
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JpaGroupByExample");

        // Create an EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Begin a transaction
        entityManager.getTransaction().begin();

        // Example data initialization
        Department itDepartment = new Department();
        itDepartment.setName("IT Department");
        entityManager.persist(itDepartment);

        Department salesDepartment = new Department();
        salesDepartment.setName("Sales Department");
        entityManager.persist(salesDepartment);

        Employees employee1 = new Employees();
        employee1.setFirstName("Mahesh");
        employee1.setLastName("Kadambala");
        employee1.setSalary(80000);
        employee1.setDepartment(itDepartment);
        entityManager.persist(employee1);

        Employees employee2 = new Employees();
        employee2.setFirstName("Eswar");
        employee2.setLastName("Betha");
        employee2.setSalary(75000);
        employee2.setDepartment(salesDepartment);
        entityManager.persist(employee2);

        // Commit the transaction
        entityManager.getTransaction().commit();

        // Use Criteria API to group employees by department and calculate total salary
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Employees> root = query.from(Employees.class);
        Join<Employees, Department> departmentJoin = root.join("department");

        query.multiselect(
                departmentJoin,
                cb.sum(root.get("salary"))
        );
        query.groupBy(departmentJoin);

        // Execute the query and retrieve results
        List<Object[]> results = entityManager.createQuery(query).getResultList();

        // Print grouped results
        for (Object[] result : results) {
            Department department = (Department) result[0];
            Double totalSalary = (Double) result[1];
            System.out.println("Department: " + department.getName() + ", Total Salary: " + totalSalary);
        }

        // Close the EntityManager and EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
