package hu.webuni.hr.lzsidek.dto;

import java.util.List;

public class FullCompanyDto extends CompanyDto {
    private List<EmployeeDto> employees;

    public FullCompanyDto(Long id, String registryNumber, String name, String address, List<EmployeeDto> employees) {
        super(id, registryNumber, name, address);
        this.employees = employees;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }
}