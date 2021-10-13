package hu.webuni.hr.lzsidek.web;

import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    SalaryService salaryService;
    private List<Employee> employees = new ArrayList<>();

    {
        employees.add(new Employee(1L, "A.Stark", "CEO", LocalDateTime.of(2019,4,6,0,0)));
        employees.add(new Employee(2L, "N.Romanoff", "CIO", LocalDateTime.of(2016,10,6,0,0)));
        employees.add(new Employee(3L, "S.Rogers", "COO", LocalDateTime.of(2016,2,2,0,0)));
        employees.add(new Employee(4L, "B.Banner", "CIT", LocalDateTime.of(2010,2,2,0,0)));
    }

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        for (Employee employee : employees) {
            salaryService.setSalaryOfNewEmployee(employee);
        }
        model.put("employees", employees);
        model.put("newEmployee", new Employee());
        return "index";
    }

    @PostMapping("/")
    public String addEmployee(Employee employee) {
        employees.add(employee);
        return "redirect:";
    }
}
