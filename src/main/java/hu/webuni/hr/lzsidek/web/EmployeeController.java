package hu.webuni.hr.lzsidek.web;

import hu.webuni.hr.lzsidek.dto.EmployeeDto;
import hu.webuni.hr.lzsidek.dto.PositionDto;
import hu.webuni.hr.lzsidek.mapper.EmployeeMapper;
import hu.webuni.hr.lzsidek.mapper.PositionMapper;
import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.model.Position;
import hu.webuni.hr.lzsidek.repository.EmployeeRepository;
import hu.webuni.hr.lzsidek.service.EmployeeService;
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
    PositionMapper positionMapper;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public List<EmployeeDto> getAll(@RequestParam(required = false) Integer minSalary) {
        if (minSalary == null) {
            return employeeMapper.employeesToDTOs(employeeService.findAll());
        } else {
            return employeeMapper.employeesToDTOs(employeeRepository.findBySalaryGreaterThan(minSalary));
        }
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable long id) {
        checkEmployeeId(id);
        return employeeMapper.employeeToDTO(
                employeeService
                        .findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping(value = "/search", params = "position")
    public List<EmployeeDto> getAllByPosition(@RequestParam(name = "position") PositionDto positionDto) {
        return employeeMapper.employeesToDTOs(employeeRepository.findByPosition(positionMapper.DTOToPosition(positionDto)));
    }

    @GetMapping(value = "/search", params = "startingWith")
    public List<EmployeeDto> findAllByNameStartingWithIgnoreCase(@RequestParam(name = "startingWith") String startingWith) {
        return employeeMapper.employeesToDTOs(employeeRepository.findByNameStartingWithIgnoreCase(startingWith));
    }

    @GetMapping("/search")
    public List<EmployeeDto> findAllByStartDateTimeOfWorkBetween(
            @RequestParam("firstDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime firstDate,
            @RequestParam("lastDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastDate) {
        return employeeMapper.employeesToDTOs(employeeRepository.findByStartDateTimeOfWorkBetween(firstDate, lastDate));
    }

    @GetMapping("/payRaise")
    public int getPayRaise(@RequestBody Employee employee) {
        return employeeService.getPayRaisePercent(employee);
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        Employee employee = employeeService.save(employeeMapper.DTOToEmployee(employeeDto));
        return employeeMapper.employeeToDTO(employee);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
        checkEmployeeId(id);
        employeeDto.setId(id);
        Employee employee = employeeService.update(employeeMapper.DTOToEmployee(employeeDto));
        return employeeMapper.employeeToDTO(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        checkEmployeeId(id);
        employeeService.delete(id);
    }

    private void checkEmployeeId(long id) {
        if (employeeService.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}