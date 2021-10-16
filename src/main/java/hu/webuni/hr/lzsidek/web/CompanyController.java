package hu.webuni.hr.lzsidek.web;

import hu.webuni.hr.lzsidek.dto.CompanyDto;
import hu.webuni.hr.lzsidek.dto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private List<EmployeeDto> companies1 = new ArrayList<>();
    private List<EmployeeDto> companies2 = new ArrayList<>();
    private List<EmployeeDto> companies3 = new ArrayList<>();
    private Map<Long, CompanyDto> companies = new HashMap<>();

    {
        companies1.add(new EmployeeDto(1L, "A.Stark", "CEO", 1233, LocalDateTime.of(2019,4,6,0,0)));
        companies1.add(new EmployeeDto(2L, "N.Romanoff", "CIO", 12323, LocalDateTime.of(2016,10,6,0,0)));
        companies2.add(new EmployeeDto(3L, "S.Rogers", "COO", 32332, LocalDateTime.of(2016,2,2,0,0)));
        companies2.add(new EmployeeDto(4L, "B.Banner", "CIT", 12132, LocalDateTime.of(2010,2,2,0,0)));
        companies3.add(new EmployeeDto(5L, "C.Barton", "QA", 12132, LocalDateTime.of(2020,2,2,0,0)));
        companies3.add(new EmployeeDto(6L, "N.Fury", "QM", 12132, LocalDateTime.of(2017,2,2,0,0)));

        companies.put(1L, new CompanyDto(1L, "R834724324-545", "Amazon", "Seattle, Washington, USA", companies1));
        companies.put(2L, new CompanyDto(2L, "R962422545-615", "Google", "Mountain View, California, USA", companies2));
        companies.put(3L, new CompanyDto(3L, "R276411145-158", "Facebook", "Palo Alto, Kalifornia, Menlo Park, USA", companies3));
    }

    @GetMapping
    public List<CompanyDto> getAll(@RequestParam(required = false) Integer minSalary) {
//        return
//                minSalary == null
//                        ? new ArrayList<>(companies.values())
//                        : companies.values().stream().filter(e -> e.getDtoSalary() > minSalary).collect(Collectors.toList());
        return new ArrayList<>(companies.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getById(@PathVariable long id) {
        if (companies.get(id) != null) {
            return new ResponseEntity<>(companies.get(id), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
        companies.put(companyDto.getId(), companyDto);
        return companyDto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
        if (!companies.containsKey(id)) {
            return ResponseEntity.notFound().build();
        } else {
            companyDto.setId(id);
            companies.put(id, companyDto);
            return ResponseEntity.ok(companyDto);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable long id) {
        companies.remove(id);
    }
}