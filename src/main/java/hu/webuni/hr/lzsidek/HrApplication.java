package hu.webuni.hr.lzsidek;

import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.repository.CompanyRepository;
import hu.webuni.hr.lzsidek.repository.EmployeeRepository;
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
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Database " + (initDbService.clearDB() ? "" : "un") + "successfully cleared.");
		System.out.println("Database " + (initDbService.insertTestData() ? "" : "un") + "successfully filled.");

//		List<Company> companyList = companyRepository.findDistinctByEmployeesSalaryGreaterThan(1000000);
//		for (Company company : companyList) {
//			System.out.println(company);
//		}

// 		List<Company> companyList = companyRepository.findByEmployeesSizeGreaterThan(2);
//		for (Company company : companyList) {
//			System.out.println(company);
//		}

//		Long companyId = companyRepository.findAll().stream().filter(e -> e.getName().equals("Amazon")).findFirst().get().getId();
//		List<Integer> salaryList = companyRepository.findByAverageSalaryGroupByPositionDesc(companyId);
//		for (Integer salary : salaryList) {
//			System.out.println(salary);
//		}

		System.out.println(">>> 0:");
		Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
		Pageable secondPageWithTwoElements = PageRequest.of(1, 2);
		Pageable thirdPageWithTwoElements = PageRequest.of(3, 2);
		System.out.println(">>> 1:");
		Page<Employee> page1 = employeeRepository.findAll(firstPageWithTwoElements);
		List<Employee> employeeList = employeeRepository.findByPosition("CEO", firstPageWithTwoElements);
		for (Employee employee : employeeList) {
			System.out.println(employee);
		}
		System.out.println("Page1: " + page1);
		System.out.println(">>> 2:");
		List<Employee> employeeList2 = employeeRepository.findByPosition("CEO", secondPageWithTwoElements);
		Page<Employee> page2 = employeeRepository.findAll(secondPageWithTwoElements);
		for (Employee employee : employeeList2) {
			System.out.println(employee);
		}
		System.out.println("Page2: " + page2);
		System.out.println(">>> 3:");
		List<Employee> employeeList3 = employeeRepository.findByPosition("CEO", thirdPageWithTwoElements);
		Page<Employee> page3 = employeeRepository.findAll(thirdPageWithTwoElements);
		for (Employee employee : employeeList3) {
			System.out.println(employee);
		}
		System.out.println("Page3: " + page3);
	}
}