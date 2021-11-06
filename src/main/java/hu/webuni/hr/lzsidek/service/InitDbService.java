package hu.webuni.hr.lzsidek.service;

import hu.webuni.hr.lzsidek.model.Company;
import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.repository.CompanyRepository;
import hu.webuni.hr.lzsidek.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InitDbService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public boolean clearDB() {
        employeeRepository.deleteAllInBatch();
        companyRepository.deleteAllInBatch();
        return employeeRepository.findAll().isEmpty() && companyRepository.findAll().isEmpty();
    }

    public boolean insertTestData() {
        Company c1 = new Company(1L, 3242454, "Amazon", "Seattle, Washington, USA", null);
        Company c2 = new Company(2L, 8768563, "Google", "Mountain View, California, USA", null);
        Company c3 = new Company(3L, 7643326, "Facebook", "Menlo Park, Palo Alto, California, USA", null);
        c1 = companyRepository.save(c1);
        c2 = companyRepository.save(c2);
        c3 = companyRepository.save(c3);
        Employee emp1 = new Employee(1L, "A.Stark", "CEO", LocalDateTime.of(2019,4,6,0,0), c1);
        Employee emp2 = new Employee(2L, "N.Romanoff", "CIO", LocalDateTime.of(2016,10,6,0,0), c1);
        Employee emp3 = new Employee(3L, "S.Rogers", "COO", LocalDateTime.of(2016,2,2,0,0), c2);
        Employee emp4 = new Employee(4L, "B.Banner", "CIT", LocalDateTime.of(2010,2,2,0,0), c2);
        Employee emp5 = new Employee(5L, "C.Barton", "CHR", LocalDateTime.of(2020,2,2,0,0), c3);
        Employee emp6 = new Employee(6L, "N.Fury", "CQR", LocalDateTime.of(2017,2,2,0,0), c3);
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
        employeeRepository.save(emp3);
        employeeRepository.save(emp4);
        employeeRepository.save(emp5);
        employeeRepository.save(emp6);
        return !(employeeRepository.findAll().isEmpty() || companyRepository.findAll().isEmpty());
    }
}
