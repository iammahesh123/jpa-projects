import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Course;
import model.Student;
import util.JPAUtil;

import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Create students
            Student student1 = new Student();
            student1.setName("Syam Sunder");

            Student student2 = new Student();
            student2.setName("Eswar");

            // Create courses
            Course course1 = new Course();
            course1.setName("Java");
            course1.setStudent(student1);

            Course course2 = new Course();
            course2.setName("HTML");
            course2.setStudent(student1);

            Course course3 = new Course();
            course3.setName("CSS");
            course3.setStudent(student2);

            em.persist(student1);
            em.persist(student2);
            em.persist(course1);
            em.persist(course2);
            em.persist(course3);

            tx.commit();

            // Retrieve students with their courses
            List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
            for (Student student : students) {
                System.out.println("Student: " + student.getName());
                System.out.println("Courses Enrolled:");
                for (Course course : student.getCourses()) {
                    System.out.println("- " + course.getName());
                }
            }
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            JPAUtil.close();
        }
    }
}

