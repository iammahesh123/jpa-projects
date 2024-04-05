package model;

import jakarta.persistence.*;

@Entity
public class ChildEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ParentEntity parent;

    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParentEntity getParent() {
        return parent;
    }

    public void setParent(ParentEntity parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "ChildEntity{" +
                "id=" + id +
                '}';
    }
}

