package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import model.Course;

import java.util.List;
@Transactional
public class CourseService {
    private EntityManager entityManager;

    public CourseService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Course> getAllCourses() {
        return entityManager.createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }


    public Course saveCourse(Course course) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(course);
        transaction.commit();
        return course;
    }

    public Course getCourseById(Long id) {
        return entityManager.find(Course.class, id);
    }


}
