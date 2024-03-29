import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Course;
import model.Student;
import service.CourseService;
import service.StudentService;

import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        // Create EntityManagerFactory using persistence unit name defined in persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");

        // Instantiate services
        CourseService courseService = new CourseService(entityManagerFactory.createEntityManager());
        StudentService studentService = new StudentService(entityManagerFactory.createEntityManager());

        // Add some courses
        Course mathCourse = new Course();
        mathCourse.setName("Java");
        courseService.saveCourse(mathCourse);

        Course scienceCourse = new Course();
        scienceCourse.setName("Python");
        courseService.saveCourse(scienceCourse);

        // Add some students
        Student johnStudent = new Student();
        johnStudent.setName("Mahesh Kadambala");
        johnStudent.getCourses().add(mathCourse);
        johnStudent.getCourses().add(scienceCourse);
        studentService.saveStudent(johnStudent);

        Student aliceStudent = new Student();
        aliceStudent.setName("Eswar Beta");
        aliceStudent.getCourses().add(mathCourse);
        studentService.saveStudent(aliceStudent);

        // Retrieve and print courses
        List<Course> courses = courseService.getAllCourses();
        System.out.println("Courses:");
        for (Course course : courses) {
            System.out.println(course.getId() + ": " + course.getName());
        }

        // Retrieve and print students
        List<Student> students = studentService.getAllStudents();
        System.out.println("\nStudents:");
        for (Student student : students) {
            System.out.println(student.getId() + ": " + student.getName());
            System.out.println("Courses:");
            for (Course course : student.getCourses()) {
                System.out.println(" - " + course.getName());
            }
        }

        // Close the EntityManagerFactory when done
        entityManagerFactory.close();
    }
}
