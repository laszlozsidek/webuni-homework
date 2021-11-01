package hu.webuni.hr.lzsidek.web;

import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.service.EmployeeMapperService;
import hu.webuni.hr.lzsidek.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employeeService")
public class EmployeeServiceController extends EmployeeMapperService {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public int getPayRisePercent(@RequestBody Employee employee) {
        return employeeService.getPayRaisePercent(employee);
    }
}