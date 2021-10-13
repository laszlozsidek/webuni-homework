package hu.webuni.hr.lzsidek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrApplication /*implements CommandLineRunner*/ {

//	@Autowired
//	SalaryService salaryService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Employee emp1 = new Employee(1L, "A.Stark", "CEO", LocalDateTime.of(2019,4,6,0,0));
//		Employee emp2 = new Employee(2L, "N.Romanoff", "CIO", LocalDateTime.of(2016,10,6,0,0));
//		Employee emp3 = new Employee(3L, "S.Rogers", "COO", LocalDateTime.of(2016,2,2,0,0));
//		Employee emp4 = new Employee(4L, "B.Banner", "CIT", LocalDateTime.of(2010,2,2,0,0));
//
//		salaryService.setSalaryOfNewEmployee(emp1);
//		salaryService.setSalaryOfNewEmployee(emp2);
//		salaryService.setSalaryOfNewEmployee(emp3);
//		salaryService.setSalaryOfNewEmployee(emp4);
//
//		System.out.println(emp1);
//		System.out.println(emp2);
//		System.out.println(emp3);
//		System.out.println(emp4);
//	}
}
