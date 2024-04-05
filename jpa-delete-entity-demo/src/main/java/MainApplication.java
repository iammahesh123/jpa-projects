import jakarta.persistence.EntityManager;
import model.Student;
import util.JPAUtil;

public class MainApplication {

    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();

        // Creating a new student
        Student student = new Student();
        student.setId(10);
        student.setName("Syam Sunder");
        student.setAge(20);

        entityManager.persist(student);

        entityManager.getTransaction().commit();

        // Deleting the student
        entityManager.getTransaction().begin();

        Student studentToDelete = entityManager.find(Student.class, 1);
        if (studentToDelete != null) {
            entityManager.remove(studentToDelete);
            entityManager.getTransaction().commit();
            System.out.println("Student successfully deleted!");
        } else {
            System.out.println("Student not found!");
        }

        JPAUtil.close();
    }
}
