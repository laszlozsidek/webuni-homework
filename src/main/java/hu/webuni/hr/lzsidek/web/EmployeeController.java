package hu.webuni.hr.lzsidek.web;

import hu.webuni.hr.lzsidek.dto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private Map<Long, EmployeeDto> employees = new HashMap<>();

    @GetMapping
    public List<EmployeeDto> getAll(@RequestParam(required = false) Integer minSalary) {
        return
            minSalary == null
                ? new ArrayList<>(employees.values())
                : employees.values().stream().filter(e -> e.getDtoSalary() > minSalary).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable long id) {
        if (employees.get(id) != null) {
            return new ResponseEntity<>(employees.get(id), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        employees.put(employeeDto.getDtoId(), employeeDto);
        return employeeDto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
        if (!employees.containsKey(id)) {
            return ResponseEntity.notFound().build();
        } else {
            employeeDto.setDtoId(id);
            employees.put(id, employeeDto);
            return ResponseEntity.ok(employeeDto);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employees.remove(id);
    }
}
