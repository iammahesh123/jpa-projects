import dto.Student;
import dto.StudentDAO;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();

        // Create and save a new student
        Student student = new Student();
        student.setName("Mahesh");
        student.setAge(25);
        studentDAO.saveStudent(student);

        // Create and save a new student
        Student student2 = new Student();
        student2.setName("Eswar");
        student2.setAge(22);
        studentDAO.saveStudent(student2);

        List<Student> students = studentDAO.findStudentByName("Eswar");
        if (!students.isEmpty()) {
            System.out.println("Found Students:");
            for (Student s : students) {
                System.out.println(s.getName());
            }
        } else {
            System.out.println("No students found with the given name.");
        }
    }

    }

