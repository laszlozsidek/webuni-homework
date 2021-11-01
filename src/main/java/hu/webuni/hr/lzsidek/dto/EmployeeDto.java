package hu.webuni.hr.lzsidek.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class EmployeeDto {
    private long dtoId;
    @NotEmpty
    private String dtoName;
    @NotEmpty
    private String dtoPosition;
    @Positive
    private int dtoSalary;
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dtoStartDateTimeOfWork;

    public EmployeeDto() {
    }

    public EmployeeDto(long dtoId, String dtoName, String dtoPosition, int dtoSalary, LocalDateTime dtoStartDateTimeOfWork) {
        this.dtoId = dtoId;
        this.dtoName = dtoName;
        this.dtoPosition = dtoPosition;
        this.dtoSalary = dtoSalary;
        this.dtoStartDateTimeOfWork = dtoStartDateTimeOfWork;
    }

    public long getDtoId() {
        return dtoId;
    }

    public void setDtoId(long dtoId) {
        this.dtoId = dtoId;
    }

    public String getDtoName() {
        return dtoName;
    }

    public void setDtoName(String dtoName) {
        this.dtoName = dtoName;
    }

    public String getDtoPosition() {
        return dtoPosition;
    }

    public void setDtoPosition(String dtoPosition) {
        this.dtoPosition = dtoPosition;
    }

    public int getDtoSalary() {
        return dtoSalary;
    }

    public void setDtoSalary(int dtoSalary) {
        this.dtoSalary = dtoSalary;
    }

    public LocalDateTime getDtoStartDateTimeOfWork() {
        return dtoStartDateTimeOfWork;
    }

    public void setDtoStartDateTimeOfWork(LocalDateTime dtoStartDateTimeOfWork) {
        this.dtoStartDateTimeOfWork = dtoStartDateTimeOfWork;
    }
}