package hu.webuni.hr.lzsidek.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Employee {
    private Long id;
    private String name;
    private String position;
    private int salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDateTimeOfWork;

    public Employee() {
    }

//    public Employee(Long id, String name, String position, String startDateTimeOfWork) {
//        this.id = id;
//        this.name = name;
//        this.position = position;
//        String[] split = startDateTimeOfWork.split("-");
//        this.startDateTimeOfWork = LocalDateTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), 0, 0);
//    }

    public Employee(Long id, String name, String position, LocalDateTime startDateTimeOfWork) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.startDateTimeOfWork = startDateTimeOfWork;
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
}
