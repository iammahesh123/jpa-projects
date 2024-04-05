import jakarta.persistence.EntityManager;
import model.Child;
import model.Parent;
import util.JPAUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class MainApplication {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();

        try {
            // Create Parent and Child entities
            Parent parent = new Parent();
            Child child1 = new Child();
            Child child2 = new Child();

            // Establish parent-child relationship
            child1.setParent(parent);
            child2.setParent(parent);

            parent.setChildren(Arrays.asList(child1, child2));

            // Persist parent along with children
            entityManager.persist(parent);

            entityManager.getTransaction().commit();

            // Print IDs of parent and children
            System.out.println("Parent ID: " + parent.getId());
            for (Child child : parent.getChildren()) {
                System.out.println("Child ID: " + child.getId());
            }

            // Remove parent entity (Cascade Remove will delete associated children)
            entityManager.getTransaction().begin();
            entityManager.remove(parent);

            // Clear parent's reference to children
            parent.setChildren(new ArrayList<>());

            entityManager.getTransaction().commit();

            // Print after removal (should show no children)
            System.out.println("After removal:");
            for (Child child : parent.getChildren()) {
                System.out.println("Child ID: " + child.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            JPAUtil.close();
        }
    }
}
