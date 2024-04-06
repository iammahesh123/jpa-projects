package model;

import jakarta.persistence.Entity;

@Entity
public class Bike extends Vehicle {
    private boolean hasBasket;

    // Getters and Setters


    public boolean isHasBasket() {
        return hasBasket;
    }

    public void setHasBasket(boolean hasBasket) {
        this.hasBasket = hasBasket;
    }
}
