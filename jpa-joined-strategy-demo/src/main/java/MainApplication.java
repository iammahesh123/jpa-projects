import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Employee;
import model.Person;
import model.Student;

import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Persisting entities
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Person person = new Person();
        person.setName("Mahesh Kadambala");

        Employee employee = new Employee();
        employee.setName("Eswar Beta");
        employee.setSalary(50000);

        Student student = new Student();
        student.setName("Jagan");
        student.setMajor("Computer Science");

        entityManager.persist(person);
        entityManager.persist(employee);
        entityManager.persist(student);

        transaction.commit();

        // Retrieving entities
        List<Person> people = entityManager.createQuery("SELECT p FROM Person p", Person.class).getResultList();

        // Printing retrieved entities
        System.out.println("People:");
        for (Person p : people) {
            System.out.println("ID: " + p.getId() + ", Name: " + p.getName());
            if (p instanceof Employee) {
                System.out.println("Employee Salary: " + ((Employee) p).getSalary());
            } else if (p instanceof Student) {
                System.out.println("Student Major: " + ((Student) p).getMajor());
            }
        }

        // Close EntityManager and EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
