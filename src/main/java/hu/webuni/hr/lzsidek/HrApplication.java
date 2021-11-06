package hu.webuni.hr.lzsidek;

import hu.webuni.hr.lzsidek.service.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	InitDbService initDbService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Database " + (initDbService.clearDB() ? "" : "un") + "successfully cleared.");
		System.out.println("Database " + (initDbService.insertTestData() ? "" : "un") + "successfully filled.");

	}
}
