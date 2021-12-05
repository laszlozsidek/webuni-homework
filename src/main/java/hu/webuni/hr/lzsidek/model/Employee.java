package hu.webuni.hr.lzsidek.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDateTimeOfWork;

    @ManyToOne
    private Position position;
    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "employee")
    private List<HolidayRequest> holidayRequests;

    @ManyToOne
    private Employee manager;

    private String username;
    private String password;

    public Employee() {
    }

    public Employee(Long id, String name, Position position, LocalDateTime startDateTimeOfWork, Company company) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.startDateTimeOfWork = startDateTimeOfWork;
        this.company = company;
    }

    public Employee(Long id, String name, Position position, LocalDateTime startDateTimeOfWork, Company company, String username, String password, Employee manager) {
        this.id = id;
        this.name = name;
        this.startDateTimeOfWork = startDateTimeOfWork;
        this.position = position;
        this.company = company;
        this.username = username;
        this.password = password;
        this.manager = manager;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
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

    public List<HolidayRequest> getHolidayRequests() {
        return holidayRequests;
    }

    public void setHolidayRequests(List<HolidayRequest> holidayRequests) {
        this.holidayRequests = holidayRequests;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addHolidayRequest(HolidayRequest holidayRequest) {
        if(this.holidayRequests == null)
            this.holidayRequests = new ArrayList<>();

        this.holidayRequests.add(holidayRequest);
        holidayRequest.setEmployee(this);
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