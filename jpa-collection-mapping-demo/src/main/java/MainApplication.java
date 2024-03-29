    import jakarta.persistence.EntityManager;
    import jakarta.persistence.Query;
    import model.Course;
    import model.Department;
    import model.Employee;
    import model.Student;
    import util.JPAUtil;
    
    import java.util.List;
    
    public class MainApplication {
        public static void main(String[] args) {
            EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    
            // Add data to Department and Employee tables
            entityManager.getTransaction().begin();
            Department department1 = new Department();
            department1.setName("Engineering");
            entityManager.persist(department1);
    
            Department department2 = new Department();
            department2.setName("Marketing");
            entityManager.persist(department2);
    
            Employee employee1 = new Employee();
            employee1.setName("Syam");
            employee1.setDepartment(department1);
            entityManager.persist(employee1);
    
            Employee employee2 = new Employee();
            employee2.setName("Mikel Smith");
            employee2.setDepartment(department1);
            entityManager.persist(employee2);
    
            Employee employee3 = new Employee();
            employee3.setName("Eswar");
            employee3.setDepartment(department2);
            entityManager.persist(employee3);
    
            entityManager.getTransaction().commit();
    
            // Add data to Student and Course tables
            entityManager.getTransaction().begin();
            Student student1 = new Student();
            student1.setName("Mahesh");
            entityManager.persist(student1);
    
            Student student2 = new Student();
            student2.setName("Sirish");
            entityManager.persist(student2);
    
            Course course1 = new Course();
            course1.setName("Java Programming");
            course1.getStudents().add(student1);
            course1.getStudents().add(student2);
            entityManager.persist(course1);
    
            Course course2 = new Course();
            course2.setName("Database Management");
            course2.getStudents().add(student1);
            entityManager.persist(course2);
    
            entityManager.getTransaction().commit();
    
            // Print all data from Department and Employee tables
            printAllDepartments(entityManager);
            printAllEmployees(entityManager);
    
            // Print all data from Student and Course tables
            printAllStudents(entityManager);
            printAllCourses(entityManager);
    
            entityManager.close();
            JPAUtil.shutdown();
        }
    
        private static void printAllDepartments(EntityManager entityManager) {
            Query query = entityManager.createQuery("SELECT d FROM Department d");
            List<Department> departments = query.getResultList();
            System.out.println("Departments:");
            for (Department department : departments) {
                System.out.println(department.getId() + "\t" + department.getName());
            }
            System.out.println();
        }
    
        private static void printAllEmployees(EntityManager entityManager) {
            Query query = entityManager.createQuery("SELECT e FROM Employee e");
            List<Employee> employees = query.getResultList();
            System.out.println("Employees:");
            for (Employee employee : employees) {
                System.out.println(employee.getId() + "\t" + employee.getName() + "\t" + employee.getDepartment().getName());
            }
            System.out.println();
        }
    
        private static void printAllStudents(EntityManager entityManager) {
            Query query = entityManager.createQuery("SELECT s FROM Student s");
            List<Student> students = query.getResultList();
            System.out.println("Students:");
            for (Student student : students) {
                System.out.println(student.getId() + "\t" + student.getName());
            }
            System.out.println();
        }
    
        private static void printAllCourses(EntityManager entityManager) {
            Query query = entityManager.createQuery("SELECT c FROM Course c");
            List<Course> courses = query.getResultList();
            System.out.println("Courses:");
            for (Course course : courses) {
                System.out.println(course.getId() + "\t" + course.getName());
                System.out.println("Students enrolled:");
                for (Student student : course.getStudents()) {
                    System.out.println("\t- " + student.getName());
                }
            }
            System.out.println();
       }
        }
    
