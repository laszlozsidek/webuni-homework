package hu.webuni.hr.lzsidek.service;

import hu.webuni.hr.lzsidek.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    int getPayRaisePercent(Employee employee);

    Employee save(Employee employee);

    Employee update(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findById(long id);

    void delete(long id);

    List<Employee> findByExample(Employee example);
}