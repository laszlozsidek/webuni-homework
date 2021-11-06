package hu.webuni.hr.lzsidek;

import hu.webuni.hr.lzsidek.repository.CompanyRepository;
import hu.webuni.hr.lzsidek.service.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	InitDbService initDbService;

	@Autowired
	CompanyRepository companyRepository;

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
	}
}