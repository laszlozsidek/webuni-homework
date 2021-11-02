package hu.webuni.hr.lzsidek.service;

import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public abstract class EmployeeMapperService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAll(Integer minSalary) {
        return minSalary == null
                ? employeeRepository.findAll()
                : employeeRepository.findAll()
                        .stream()
                        .filter(e -> e.getSalary() > minSalary)
                        .collect(Collectors.toList());
    }

    public Optional<Employee> find(long id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void remove(long id) {
        employeeRepository.deleteById(id);
    }
}