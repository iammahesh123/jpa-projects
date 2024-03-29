

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import model.Department;
import model.Employee;


import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = emf.createEntityManager();

        // Adding departments
        em.getTransaction().begin();
        Department department1 = new Department();
        department1.setName("Engineering");
        em.persist(department1);

        Department department2 = new Department();
        department2.setName("Marketing");
        em.persist(department2);
        em.getTransaction().commit();

        // Adding employees
        em.getTransaction().begin();
        for (int i = 0; i < 7; i++) {
            Employee employee = new Employee();
            employee.setName("Employee " + i);
            employee.setDepartment(department1);
            em.persist(employee);
        }

        for (int i = 0; i < 3; i++) {
            Employee employee = new Employee();
            employee.setName("Employee " + (i + 7));
            employee.setDepartment(department2);
            em.persist(employee);
        }
        em.getTransaction().commit();

        // Executing query with Having clause
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Employee> root = query.from(Employee.class);

        Expression<Long> countExpression = cb.count(root);
        query.multiselect(root.get("department"), countExpression);
        query.groupBy(root.get("department"));

        Predicate havingPredicate = cb.gt(countExpression, 5); // More than 5 employees
        query.having(havingPredicate);

        TypedQuery<Object[]> typedQuery = em.createQuery(query);
        List<Object[]> resultList = typedQuery.getResultList();

        // Printing departments with more than 5 employees
        for (Object[] result : resultList) {
            Department department = (Department) result[0];
            Long employeeCount = (Long) result[1];
            System.out.println("Department: " + department.getName() + ", Employee Count: " + employeeCount);
        }

        em.close();
        emf.close();
    }
}
