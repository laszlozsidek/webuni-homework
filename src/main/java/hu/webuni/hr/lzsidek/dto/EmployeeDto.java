package hu.webuni.hr.lzsidek.dto;

import hu.webuni.hr.lzsidek.model.Position;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class EmployeeDto {
    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private Position position;
    @Positive
    private int salary;
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDateTimeOfWork;

    public EmployeeDto() {
    }

    public EmployeeDto(long id, String name, Position position, int salary, LocalDateTime startDateTimeOfWork) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.startDateTimeOfWork = startDateTimeOfWork;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}