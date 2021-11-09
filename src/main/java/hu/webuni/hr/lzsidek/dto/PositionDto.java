package hu.webuni.hr.lzsidek.dto;

import hu.webuni.hr.lzsidek.enums.MinimalEducation;
import hu.webuni.hr.lzsidek.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class PositionDto {
    private Long id;
    private String name;
    private MinimalEducation minimalEducation;
    private int minSalary;
    private List<Employee> employees = new ArrayList<>();

    public PositionDto() {
    }

    public PositionDto(Long id, String name, MinimalEducation minimalEducation, int minSalary, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.minimalEducation = minimalEducation;
        this.minSalary = minSalary;
        this.employees = employees;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
