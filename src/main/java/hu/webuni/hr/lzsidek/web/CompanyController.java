package hu.webuni.hr.lzsidek.web;

import hu.webuni.hr.lzsidek.dto.CompanyDto;
import hu.webuni.hr.lzsidek.dto.FullCompanyDto;
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
    private Map<Long, FullCompanyDto> companies = new HashMap<>();

    {
        companies1.add(new EmployeeDto(1L, "A.Stark", "CEO", 1233, LocalDateTime.of(2019,4,6,0,0)));
        companies1.add(new EmployeeDto(2L, "N.Romanoff", "CIO", 12323, LocalDateTime.of(2016,10,6,0,0)));
        companies2.add(new EmployeeDto(3L, "S.Rogers", "COO", 32332, LocalDateTime.of(2016,2,2,0,0)));
        companies2.add(new EmployeeDto(4L, "B.Banner", "CIT", 12132, LocalDateTime.of(2010,2,2,0,0)));
        companies3.add(new EmployeeDto(5L, "C.Barton", "QA", 12132, LocalDateTime.of(2020,2,2,0,0)));
        companies3.add(new EmployeeDto(6L, "N.Fury", "QM", 12132, LocalDateTime.of(2017,2,2,0,0)));

        companies.put(1L, new FullCompanyDto(1L, "R834724324-545", "Amazon", "Seattle, Washington, USA", companies1));
        companies.put(2L, new FullCompanyDto(2L, "R962422545-615", "Google", "Mountain View, California, USA", companies2));
        companies.put(3L, new FullCompanyDto(3L, "R276411145-158", "Facebook", "Palo Alto, Kalifornia, Menlo Park, USA", companies3));
    }

    @GetMapping
    public List<CompanyDto> getAll(@RequestParam(required = false) Boolean full) {
        if (full != null && full) {
            return new ArrayList<>(companies.values());
        } else {
            List<CompanyDto> newList = new ArrayList<>();
            companies.values().forEach(c -> newList.add(new CompanyDto(c.getId(), c.getRegistryNumber(), c.getName(), c.getAddress())));
            return newList;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullCompanyDto> getById(@PathVariable long id) {
        if (companies.get(id) != null) {
            return new ResponseEntity<>(companies.get(id), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public FullCompanyDto createCompany(@RequestBody FullCompanyDto fullCompanyDto) {
        companies.put(fullCompanyDto.getId(), fullCompanyDto);
        return fullCompanyDto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<FullCompanyDto> updateCompany(@PathVariable long id, @RequestBody FullCompanyDto fullCompanyDto) {
        if (!companies.containsKey(id)) {
            return ResponseEntity.notFound().build();
        } else {
            fullCompanyDto.setId(id);
            companies.put(id, fullCompanyDto);
            return ResponseEntity.ok(fullCompanyDto);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable long id) {
        companies.remove(id);
    }
}