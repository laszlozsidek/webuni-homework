package hu.webuni.hr.lzsidek.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String position;
    private int salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDateTimeOfWork;

    @ManyToOne
    private Company company;

    public Employee() {
    }

    public Employee(Long id, String name, String position, LocalDateTime startDateTimeOfWork) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.startDateTimeOfWork = startDateTimeOfWork;
    }

    public Employee(Long id, String name, String position, LocalDateTime startDateTimeOfWork, Company company) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.startDateTimeOfWork = startDateTimeOfWork;
        this.company = company;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDateTime getStartDateTimeOfWork() {
        return startDateTimeOfWork;
    }

    public void setStartDateTimeOfWork(LocalDateTime startDateTimeOfWork) {
        this.startDateTimeOfWork = startDateTimeOfWork;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", startDateTimeOfWork=" + startDateTimeOfWork +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}