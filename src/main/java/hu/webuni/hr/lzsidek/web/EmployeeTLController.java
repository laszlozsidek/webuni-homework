package hu.webuni.hr.lzsidek.web;

import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EmployeeTLController {

    @Autowired
    SalaryService salaryService;
    private Map<Long, Employee> employees = new HashMap<>();

    {
        employees.put(1L, new Employee(1L, "A.Stark", "CEO", LocalDateTime.of(2019,4,6,0,0)));
        employees.put(2L, new Employee(2L, "N.Romanoff", "CIO", LocalDateTime.of(2016,10,6,0,0)));
        employees.put(3L, new Employee(3L, "S.Rogers", "COO", LocalDateTime.of(2016,2,2,0,0)));
        employees.put(4L, new Employee(4L, "B.Banner", "CIT", LocalDateTime.of(2010,2,2,0,0)));
    }

    // <employees>

    @GetMapping("/employees")
    public String home(Map<String, Object> model) {
        for (Employee employee : employees.values()) {
            salaryService.setSalaryOfNewEmployee(employee);
        }
        model.put("employees", employees);
        model.put("newEmployee", new Employee());
        return "employees";
    }

    @PostMapping("/employees")
    public String addEmployee(Employee employee) {
        Long max;
        if (!employees.containsKey(employee.getId()) && employee.getId() != null) {
            max = employee.getId();
        } else {
            max = employees.keySet().stream().max(Double::compare).get() + 1;
            employee.setId(max);
        }
        employees.put(max, employee);
        return "redirect:employees";
    }

    @GetMapping("/employees/{id}/delete")
    public String deleteEmployee(@PathVariable long id) {
        employees.remove(id);
        return "redirect:/employees";
    }

    // </employees>

    // <employee>

    @GetMapping("/employees/{id}")
    public String getEmployeeToEdit(@PathVariable long id, Map<String, Object> model) {
        model.put("employee_to_edit", employees.get(id));
        return "employee";
    }

    @PostMapping(value="/employees/{id}/edit", params = "save")
    public String editEmployee(@PathVariable long id, Employee modifiedEmployee) {
        Employee employee = employees.get(id);
        employee.setName(modifiedEmployee.getName());
        employee.setPosition(modifiedEmployee.getPosition());
        employee.setStartDateTimeOfWork(modifiedEmployee.getStartDateTimeOfWork());
        return "redirect:/employees";
    }

    @PostMapping(value="/employees/{id}/edit", params = "cancel")
    public String editEmployee() {
        return "redirect:/employees";
    }

    // </employee>
}