package hu.webuni.hr.lzsidek.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue
    private Long id;
    private int registryNumber;
    private String name;
    private String address;
    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    public Company() {
    }

    public Company(Long id, int registryNumber, String name, String address, List<Employee> employees) {
        this.id = id;
        this.registryNumber = registryNumber;
        this.name = name;
        this.address = address;
        this.employees = employees;
    }

    

    public int getRegistryNumber() {
        return registryNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRegistryNumber(int registryNumber) {
        this.registryNumber = registryNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        if(this.employees == null)
            this.employees = new ArrayList<>();

        this.employees.add(employee);
        employee.setCompany(this);
    }

    @Override
    public String toString() {
        return "Company: " +
                "id=" + id +
                ", registryNumber=" + registryNumber +
                ", name='" + name + '\'' +
                ", address='" + address;
    }
}
