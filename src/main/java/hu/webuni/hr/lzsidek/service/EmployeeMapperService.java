package hu.webuni.hr.lzsidek.service;

import hu.webuni.hr.lzsidek.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public abstract class EmployeeMapperService {
    private Map<Long, Employee> employees = new HashMap<>();

    public List<Employee> findAll(Integer minSalary) {
        return minSalary == null
                ? new ArrayList<>(employees.values())
                : employees.values().stream().filter(e -> e.getSalary() > minSalary).collect(Collectors.toList());
    }

    public Employee find(long id) {
        return employees.get(id) == null ? null : employees.get(id);
    }

    public Employee save(Employee employee) {
        employees.put(employee.getId(), employee);
        return employee;
    }

    public void remove(long id) {
        employees.remove(id);
    }
}