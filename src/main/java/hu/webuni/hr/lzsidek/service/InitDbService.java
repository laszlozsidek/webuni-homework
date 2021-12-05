package hu.webuni.hr.lzsidek.service;

import hu.webuni.hr.lzsidek.enums.MinimalEducation;
import hu.webuni.hr.lzsidek.model.Company;
import hu.webuni.hr.lzsidek.enums.CompanyType;
import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.model.Position;
import hu.webuni.hr.lzsidek.repository.CompanyRepository;
import hu.webuni.hr.lzsidek.repository.EmployeeRepository;
import hu.webuni.hr.lzsidek.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InitDbService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    SalaryService salaryService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean clearDB() {
        employeeRepository.deleteAllInBatch();
        companyRepository.deleteAllInBatch();
        positionRepository.deleteAllInBatch();
        return employeeRepository.findAll().isEmpty() && companyRepository.findAll().isEmpty() && positionRepository.findAll().isEmpty();
    }

    public boolean insertTestData() {
        Position p1 = new Position(1L, "CEO", MinimalEducation.COLLEGE, 200000);
        Position p2 = new Position(2L, "CIO", MinimalEducation.UNIVERSITY, 150000);
        Position p3 = new Position(3L, "CIT", MinimalEducation.COLLEGE, 100000);
        Position p4 = new Position(4L, "CHR", null, 100000);

        p1 = positionRepository.save(p1);
        p2 = positionRepository.save(p2);
        p3 = positionRepository.save(p3);
        p4 = positionRepository.save(p4);

        Company c1 = new Company(1L, 3242454, "Amazon", "Seattle, Washington, USA", null, CompanyType.BT);
        Company c2 = new Company(2L, 8768563, "Google", "Mountain View, California, USA", null, CompanyType.KFT);
        Company c3 = new Company(3L, 7643326, "Facebook", "Menlo Park, Palo Alto, California, USA", null, CompanyType.ZRT);

        c1 = companyRepository.save(c1);
        c2 = companyRepository.save(c2);
        c3 = companyRepository.save(c3);

        Employee emp1 = new Employee(1L, "A.Stark", p1, LocalDateTime.of(2019,4,6,0,0), c1
            , "astark", passwordEncoder.encode("pass"));
        Employee emp2 = new Employee(2L, "N.Romanoff", p2, LocalDateTime.of(2016,10,6,0,0), c1
            , "nromanoff", passwordEncoder.encode("pass"));
        Employee emp3 = new Employee(3L, "S.Rogers", p1, LocalDateTime.of(2016,2,2,0,0), c1);
        Employee emp4 = new Employee(4L, "B.Banner", p3, LocalDateTime.of(2010,2,2,0,0), c2);
        Employee emp5 = new Employee(5L, "C.Barton", p4, LocalDateTime.of(2020,2,2,0,0), c3);
        Employee emp6 = new Employee(6L, "N.Fury", p1, LocalDateTime.of(2017,2,2,0,0), c3);

        salaryService.setSalaryOfNewEmployee(emp1);
        salaryService.setSalaryOfNewEmployee(emp2);
        salaryService.setSalaryOfNewEmployee(emp3);
        salaryService.setSalaryOfNewEmployee(emp4);
        salaryService.setSalaryOfNewEmployee(emp5);
        salaryService.setSalaryOfNewEmployee(emp6);

        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
        employeeRepository.save(emp3);
        employeeRepository.save(emp4);
        employeeRepository.save(emp5);
        employeeRepository.save(emp6);

        return !(employeeRepository.findAll().isEmpty() || companyRepository.findAll().isEmpty() || positionRepository.findAll().isEmpty());
    }
}
