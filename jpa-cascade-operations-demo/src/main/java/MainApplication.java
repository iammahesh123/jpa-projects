import jakarta.persistence.*;
import model.ChildEntity;
import model.ParentEntity;

import java.util.List;


public class MainApplication {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // Create Parent Entity with Children
            ParentEntity parent = new ParentEntity();
            ChildEntity child1 = new ChildEntity();
            ChildEntity child2 = new ChildEntity();
            parent.getChildren().add(child1);
            parent.getChildren().add(child2);
            child1.setParent(parent);
            child2.setParent(parent);

            // Persist Parent (Cascades to Children)
            entityManager.persist(parent);

            transaction.commit();

            // Print Parent and Child tables data
            printParentAndChildData(entityManager);

        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    private static void printParentAndChildData(EntityManager entityManager) {
        System.out.println("Parent Table:");
        Query parentQuery = entityManager.createQuery("SELECT p FROM ParentEntity p");
        List<ParentEntity> parents = parentQuery.getResultList();
        for (ParentEntity parent : parents) {
            System.out.println(parent);
        }

        System.out.println("\nChild Table:");
        Query childQuery = entityManager.createQuery("SELECT c FROM ChildEntity c");
        List<ChildEntity> children = childQuery.getResultList();
        for (ChildEntity child : children) {
            System.out.println(child);
        }
    }
}
