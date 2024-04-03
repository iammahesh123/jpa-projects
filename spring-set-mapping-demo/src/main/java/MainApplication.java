import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Child;
import model.Parent;

import java.util.HashSet;
import java.util.Set;

public class MainApplication {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Create parent entity
        Parent parent = new Parent();
        Set<Child> children = new HashSet<>();

        // Create child entities
        Child child1 = new Child();
        child1.setParent(parent);

        Child child2 = new Child();
        child2.setParent(parent);

        // Add children to parent
        children.add(child1);
        children.add(child2);

        parent.setChildren(children);

        // Begin transaction
        entityManager.getTransaction().begin();

        // Persist parent and children
        entityManager.persist(parent);
        entityManager.persist(child1);
        entityManager.persist(child2);

        // Commit transaction
        entityManager.getTransaction().commit();

        // Display relationships
        System.out.println("Parent ID: " + parent.getId());
        for (Child child : parent.getChildren()) {
            System.out.println("Child ID: " + child.getId() + ", Parent ID: " + child.getParent().getId());
        }

        // Close EntityManager and EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
