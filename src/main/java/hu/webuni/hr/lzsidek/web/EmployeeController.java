package hu.webuni.hr.lzsidek.web;

import hu.webuni.hr.lzsidek.dto.EmployeeDto;
import hu.webuni.hr.lzsidek.mapper.EmployeeMapper;
import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.service.EmployeeMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
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

    @GetMapping(value = "/search", params = "position")
    public List<EmployeeDto> getAllByPosition(@RequestParam(name = "position") String position) {
        return employeeMapper.employeesToDTOs(employeeMapperService.findAllByPosition(position));
    }

    @GetMapping(value = "/search", params = "startingWith")
    public List<EmployeeDto> findAllByNameStartingWithIgnoreCase(@RequestParam(name = "startingWith") String startingWith) {
        return employeeMapper.employeesToDTOs(employeeMapperService.findAllByNameStartingWithIgnoreCase(startingWith));
    }

    @GetMapping("/search")
    public List<EmployeeDto> findAllByNameStartingWithIgnoreCase(
            @RequestParam("firstDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime firstDate,
            @RequestParam("lastDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastDate) {
        return employeeMapper.employeesToDTOs(employeeMapperService.findAllByStartDateTimeOfWorkBetween(firstDate, lastDate));
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