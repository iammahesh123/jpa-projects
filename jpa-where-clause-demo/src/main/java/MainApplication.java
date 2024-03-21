import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import model.Employee;
import persistence.JPAUtil;

import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        // Add 5 entities
        persistEmployees(entityManager);

        // Criteria query to retrieve employees from the "IT" department
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);

        Predicate condition = cb.equal(root.get("department"), "IT");
        cq.where(condition);

        List<Employee> resultList = entityManager.createQuery(cq).getResultList();

        for (Employee employee : resultList) {
            System.out.println(employee.getName() + " - " + employee.getDepartment());
        }

        entityManager.close();
        JPAUtil.closeEntityManagerFactory();
    }

    private static void persistEmployees(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        for (int i = 1; i <= 5; i++) {
            Employee employee = new Employee();
            employee.setName("Employee " + i);
            employee.setDepartment(i % 2 == 0 ? "IT" : "HR"); // Alternating departments
            entityManager.persist(employee);
        }
        entityManager.getTransaction().commit();
    }
}
