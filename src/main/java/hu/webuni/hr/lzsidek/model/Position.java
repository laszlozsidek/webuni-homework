package hu.webuni.hr.lzsidek.model;

import hu.webuni.hr.lzsidek.enums.MinimalEducation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Position {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private MinimalEducation minimalEducation;
    private int minSalary;
    @OneToMany(mappedBy = "position")
    private List<Employee> employees;

    public Position() {
    }

    public Position(Long id, String name, MinimalEducation minimalEducation, int minSalary) {
        this.id = id;
        this.name = name;
        this.minimalEducation = minimalEducation;
        this.minSalary = minSalary;
    }

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

    public MinimalEducation getMinimalEducation() {
        return minimalEducation;
    }

    public void setMinimalEducation(MinimalEducation minimalEducation) {
        this.minimalEducation = minimalEducation;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }
}
