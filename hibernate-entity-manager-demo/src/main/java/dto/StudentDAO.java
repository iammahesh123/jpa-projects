package dto;


import jakarta.persistence.*;

import java.util.List;


public class StudentDAO {
    private EntityManagerFactory entityManagerFactory;

    public StudentDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernateDemo");
    }

    public void saveStudent(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(student);
        transaction.commit();
        entityManager.close();
    }

    public List<Student> findStudentByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE s.name = :name", Student.class);
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }


    public void updateStudent(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(student);
        transaction.commit();
        entityManager.close();
    }

    public void deleteStudent(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(entityManager.contains(student) ? student : entityManager.merge(student));
        transaction.commit();
        entityManager.close();
    }
}


