package model;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
public class Employee {
    @Id
    private Long id;
    private String name;

    @ElementCollection
    @CollectionTable(name="employee_department",
            joinColumns=@JoinColumn(name="employee_id"))
    @MapKeyColumn(name="department_key")
    @Column(name="department_value")
    private Map<String, String> departments = new HashMap<>();

    // Constructors, getters, and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getDepartments() {
        return departments;
    }

    public void setDepartments(Map<String, String> departments) {
        this.departments = departments;
    }
}