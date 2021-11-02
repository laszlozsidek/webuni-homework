package hu.webuni.hr.lzsidek.web;

import hu.webuni.hr.lzsidek.dto.EmployeeDto;
import hu.webuni.hr.lzsidek.mapper.EmployeeMapper;
import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.service.EmployeeMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmployeeMapperService employeeMapperService;

    @GetMapping
    public List<EmployeeDto> getAll(@RequestParam(required = false) Integer minSalary) {
        return employeeMapper.employeesToDTOs(employeeMapperService.findAll(minSalary));
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable long id) {
        checkEmployeeId(id);
        return employeeMapper.employeeToDTO(
                employeeMapperService
                        .find(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        Employee employee = employeeMapperService.save(employeeMapper.DTOToEmployee(employeeDto));
        return employeeMapper.employeeToDTO(employee);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
        checkEmployeeId(id);
        employeeDto.setId(id);
        Employee employee = employeeMapperService.save(employeeMapper.DTOToEmployee(employeeDto));
        return employeeMapper.employeeToDTO(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        checkEmployeeId(id);
        employeeMapperService.remove(id);
    }

    private void checkEmployeeId(long id) {
        if (employeeMapperService.find(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
