import jakarta.persistence.EntityManager;
import model.Animal;
import model.Dog;
import model.Cat;
import persistence.JPAUtil;


public class MainApplication {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        // Persisting entities
        entityManager.getTransaction().begin();
        Animal dog = new Dog("Buddy", "Labrador");
        entityManager.persist(dog);

        Animal cat = new Cat("Whiskers", "White");
        entityManager.persist(cat);
        entityManager.getTransaction().commit();

        // Querying data
        entityManager.getTransaction().begin();
        System.out.println("All animals:");
        entityManager.createQuery("SELECT a FROM Animal a", Animal.class)
                .getResultList()
                .forEach(animal -> {
                    if (animal instanceof Dog) {
                        System.out.println("Dog: " + animal.getName() + ", Breed: " + ((Dog) animal).getBreed());
                    } else if (animal instanceof Cat) {
                        System.out.println("Cat: " + animal.getName() + ", Color: " + ((Cat) animal).getColor());
                    }
                });
        entityManager.getTransaction().commit();

        entityManager.close();
        JPAUtil.close();
    }
}
