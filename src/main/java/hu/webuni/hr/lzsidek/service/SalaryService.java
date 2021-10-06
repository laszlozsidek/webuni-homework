package hu.webuni.hr.lzsidek.service;

import hu.webuni.hr.lzsidek.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalaryService {
    @Autowired
    private EmployeeService employeeService;

    int baseSalary = 1000000;

    public void setSalaryOfNewEmployee(Employee employee) {
        employee.setSalary(
                (int)(((double)(employeeService.getPayRaisePercent(employee) + 100) / 100.0) * baseSalary)
        );
    }
}
