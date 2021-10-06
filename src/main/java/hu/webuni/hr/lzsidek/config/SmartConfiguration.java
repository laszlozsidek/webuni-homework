package hu.webuni.hr.lzsidek.config;

import hu.webuni.hr.lzsidek.service.EmployeeService;
import hu.webuni.hr.lzsidek.service.SmartEmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("smart")
public class SmartConfiguration {

    @Bean
    public EmployeeService employeeService() {
        return new SmartEmployeeService();
    }
}