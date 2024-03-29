import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Student;
import model.University;

public class MainApplication {
    public static void main(String[] args) {
    // Create EntityManagerFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_NAME");
    EntityManager em = emf.createEntityManager();

    // Begin transaction
        em.getTransaction().begin();

    // Create a university
    University university = new University();
        university.setName("Geek University");
        em.persist(university);

    // Create students
    Student student1 = new Student();
        student1.setName("Mahesh");
        student1.setUniversity(university);
        em.persist(student1);

    Student student2 = new Student();
        student2.setName("Eswar");
        student2.setUniversity(university);
        em.persist(student2);

    // Commit transaction
        em.getTransaction().commit();

    // Retrieve student and university
    Student retrievedStudent = em.find(Student.class, student1.getId());
    University retrievedUniversity = retrievedStudent.getUniversity();

        System.out.println("Student: " + retrievedStudent.getName());
        System.out.println("University: " + retrievedUniversity.getName());

    // Close EntityManager
        em.close();
        emf.close();
}
}
