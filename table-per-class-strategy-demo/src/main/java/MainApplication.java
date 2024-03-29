
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Bike;
import model.Car;


public class MainApplication {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = emf.createEntityManager();

        // Creating and persisting Car
        em.getTransaction().begin();
        Car car = new Car();
        car.setManufacturer("Toyota");
        car.setNumberOfDoors(4);
        em.persist(car);
        em.getTransaction().commit();

        // Creating and persisting Bike
        em.getTransaction().begin();
        Bike bike = new Bike();
        bike.setManufacturer("Giant");
        bike.setHasBasket(true);
        em.persist(bike);
        em.getTransaction().commit();

        // Retrieving and printing Car details
        Car persistedCar = em.find(Car.class, car.getId());
        System.out.println("Persisted Car Details:");
        System.out.println("ID: " + persistedCar.getId());
        System.out.println("Manufacturer: " + persistedCar.getManufacturer());
        System.out.println("Number of Doors: " + persistedCar.getNumberOfDoors());

        // Retrieving and printing Bike details
        Bike persistedBike = em.find(Bike.class, bike.getId());
        System.out.println("\nPersisted Bike Details:");
        System.out.println("ID: " + persistedBike.getId());
        System.out.println("Manufacturer: " + persistedBike.getManufacturer());
        System.out.println("Has Basket: " + persistedBike.isHasBasket());

        em.close();
        emf.close();
    }
}

