package hu.webuni.hr.lzsidek;

import hu.webuni.hr.lzsidek.model.Company;
import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.model.Position;
import hu.webuni.hr.lzsidek.repository.CompanyRepository;
import hu.webuni.hr.lzsidek.repository.EmployeeRepository;
import hu.webuni.hr.lzsidek.repository.PositionRepository;
import hu.webuni.hr.lzsidek.service.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	InitDbService initDbService;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	PositionRepository positionRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Database " + (initDbService.clearDB() ? "" : "un") + "successfully cleared.");
		System.out.println("Database " + (initDbService.insertTestData() ? "" : "un") + "successfully filled.");

//		List<Company> companyList1 = companyRepository.findDistinctByEmployeesSalaryGreaterThan(1000000);
//		for (Company company : companyList1) {
//			System.out.println(company);
//		}
//
// 		List<Company> companyList2 = companyRepository.findByEmployeesSizeGreaterThan(2);
//		for (Company company : companyList2) {
//			System.out.println(company);
//		}
//
//		Long companyId = companyRepository.findAll().stream().filter(e -> e.getName().equals("Amazon")).findFirst().get().getId();
//		List<Double> salaryList = companyRepository.findByAverageSalaryGroupByPositionDesc(companyId);
//		for (Double salary : salaryList) {
//			System.out.println(salary);
//		}
//
//		Position position = positionRepository.findAll().stream().filter(e -> e.getName().equals("CEO")).findFirst().get();
//		System.out.println(">>> 0:");
//		Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
//		Pageable secondPageWithTwoElements = PageRequest.of(1, 2);
//		Pageable thirdPageWithTwoElements = PageRequest.of(3, 2);
//		System.out.println(">>> 1:");
//		Page<Employee> page1 = employeeRepository.findAll(firstPageWithTwoElements);
//		List<Employee> employeeList = employeeRepository.findByPosition(position, firstPageWithTwoElements);
//		for (Employee employee : employeeList) {
//			System.out.println(employee);
//		}
//		System.out.println("Page1: " + page1);
//		System.out.println(">>> 2:");
//		List<Employee> employeeList2 = employeeRepository.findByPosition(position, secondPageWithTwoElements);
//		Page<Employee> page2 = employeeRepository.findAll(secondPageWithTwoElements);
//		for (Employee employee : employeeList2) {
//			System.out.println(employee);
//		}
//		System.out.println("Page2: " + page2);
//		System.out.println(">>> 3:");
//		List<Employee> employeeList3 = employeeRepository.findByPosition(position, thirdPageWithTwoElements);
//		Page<Employee> page3 = employeeRepository.findAll(thirdPageWithTwoElements);
//		for (Employee employee : employeeList3) {
//			System.out.println(employee);
//		}
//		System.out.println("Page3: " + page3);

		positionRepository.setMinSalaryByPosition("CEO", 1040000);
	}
}