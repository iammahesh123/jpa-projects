import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Child;
import model.Parent;

public class MainApplication {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        // Create parent entity with associated child entities
        Parent parent = new Parent();
        parent.setName("Parent");

        Child child1 = new Child();
        child1.setName("Child 1");
        child1.setParent(parent);

        Child child2 = new Child();
        child2.setName("Child 2");
        child2.setParent(parent);

        parent.getChildren().add(child1);
        parent.getChildren().add(child2);

        // Persist parent entity (Cascade Persist will save child entities)
        em.getTransaction().begin();
        em.persist(parent);
        em.getTransaction().commit();

        // Verify Persistence
        Parent persistedParent = em.find(Parent.class, parent.getId());
        System.out.println("Persisted Parent: " + persistedParent.getName());
        System.out.println("Persisted Children: " + persistedParent.getChildren().size());

        em.close();
        emf.close();
    }
}
