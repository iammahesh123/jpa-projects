package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ParentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<ChildEntity> children = new ArrayList<>();

    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ChildEntity> getChildren() {
        return children;
    }

    public void setChildren(List<ChildEntity> children) {
        this.children = children;
    }


    @Override
    public String toString() {
        return "ParentEntity{" +
                "id=" + id +
                '}';
    }


}
