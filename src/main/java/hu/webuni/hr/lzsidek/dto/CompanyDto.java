package hu.webuni.hr.lzsidek.dto;

import java.util.ArrayList;
import java.util.List;

public class CompanyDto {
    private Long id;
    private int registryNumber;
    private String name;
    private String address;
    private List<EmployeeDto> employees = new ArrayList<>();

    public CompanyDto() {
    }

    public CompanyDto(Long id, int registryNumber, String name, String address, List<EmployeeDto> employees) {
        this.id = id;
        this.registryNumber = registryNumber;
        this.name = name;
        this.address = address;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRegistryNumber() {
        return registryNumber;
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

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }
}