package model;

import jakarta.persistence.Entity;

@Entity
public class Bike extends Vehicle {
    private boolean hasBasket;

    // Constructors, getters, and setters


    public boolean isHasBasket() {
        return hasBasket;
    }

    public void setHasBasket(boolean hasBasket) {
        this.hasBasket = hasBasket;
    }
}
