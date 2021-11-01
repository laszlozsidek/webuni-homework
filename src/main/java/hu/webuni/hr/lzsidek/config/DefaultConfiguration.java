package hu.webuni.hr.lzsidek.config;

import hu.webuni.hr.lzsidek.service.DefaultEmployeeService;
import hu.webuni.hr.lzsidek.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!smart")
public class DefaultConfiguration {

    @Bean
    public EmployeeService employeeService() {
        return new DefaultEmployeeService();
    }
}