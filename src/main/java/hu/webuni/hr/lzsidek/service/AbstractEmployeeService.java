package hu.webuni.hr.lzsidek.service;

import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public abstract class AbstractEmployeeService implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        if(employee.getId() == null || !employeeRepository.existsById(employee.getId()))
            return null;
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByExample(Employee example) {

        long id = example.getId();
        String name = example.getName();
        String position = example.getPosition().getName();
        int salary = example.getSalary();
        LocalDateTime startDateTimeOfWork = example.getStartDateTimeOfWork();
        String company = example.getCompany().getName();

        Specification<Employee> spec = Specification.where(null);

        if (id > 0) {
            spec = spec.and(EmployeeSpecifications.hasId(id));
        }

        if (StringUtils.hasText(name)) {
            spec = spec.and(EmployeeSpecifications.hasEmployeeName(name));
        }

        if (StringUtils.hasText(position)) {
            spec = spec.and(EmployeeSpecifications.hasPosition(position));
        }

        if (salary > 0) {
            spec = spec.and(EmployeeSpecifications.hasSalary(salary));
        }

        if (startDateTimeOfWork != null) {
            spec = spec.and(EmployeeSpecifications.hasStartDay(startDateTimeOfWork));
        }

        if (StringUtils.hasText(company)) {
            spec = spec.and(EmployeeSpecifications.hasCompanyName(company));
        }

        return employeeRepository.findAll(spec, Sort.by("id"));
    }
}