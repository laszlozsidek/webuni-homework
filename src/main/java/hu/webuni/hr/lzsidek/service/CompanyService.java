package hu.webuni.hr.lzsidek.service;

import hu.webuni.hr.lzsidek.model.Company;
import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.repository.CompanyRepository;
import hu.webuni.hr.lzsidek.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public Company addEmployee(long id, Employee employee) {
        Company company = companyRepository.findById(id).get();
        company.addEmployee(employee);
        employeeRepository.save(employee);
        return company;
    }

    @Transactional
    public Company deleteEmployee(long companyId, long employeeId) {
        Company company = companyRepository.findById(companyId).get();
        Employee employee = employeeRepository.getById(employeeId);
        employee.setCompany(null);
        company.getEmployees().remove(employee);
        return company;
    }

    @Transactional
    public Company updateEmployeeList(long companyId, List<Employee> newEmployeeList) {
        Company company = companyRepository.findById(companyId).get();
        company.getEmployees().forEach(e -> e.setCompany(null));
        company.getEmployees().clear();
        newEmployeeList.forEach(e -> {
            company.addEmployee(e);
            employeeRepository.save(e);
        });
        return company;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> findById(long id) {
        return companyRepository.findById(id);
    }

    @Transactional
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    public void delete(long id) {
        companyRepository.deleteById(id);
    }
}
