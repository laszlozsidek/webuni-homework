package hu.webuni.hr.lzsidek.service;

import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public void remove(long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> findAllByPosition(String position) {
        return employeeRepository.findByPosition(position);
    }

    public List<Employee> findAllByNameStartingWithIgnoreCase(String startingWith) {
        return employeeRepository.findByNameStartingWithIgnoreCase(startingWith);
    }

    public List<Employee> findAllByStartDateTimeOfWorkBetween(LocalDateTime firstDate, LocalDateTime lastDate) {
        return employeeRepository.findByStartDateTimeOfWorkBetween(firstDate, lastDate);
    }
}