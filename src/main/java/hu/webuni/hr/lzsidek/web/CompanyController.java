package hu.webuni.hr.lzsidek.web;

import hu.webuni.hr.lzsidek.dto.CompanyDto;
import hu.webuni.hr.lzsidek.dto.EmployeeDto;
import hu.webuni.hr.lzsidek.mapper.CompanyMapper;
import hu.webuni.hr.lzsidek.mapper.EmployeeMapper;
import hu.webuni.hr.lzsidek.model.Company;
import hu.webuni.hr.lzsidek.repository.CompanyRepository;
import hu.webuni.hr.lzsidek.service.CompanyService;
import hu.webuni.hr.lzsidek.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<CompanyDto> getAll(@RequestParam(required = false) Boolean full) {
        List<Company> companies;
        boolean notFull = full == null || !full;
        if (notFull) {
            companies = companyService.findAll();
            return companyMapper.companiesToDTOs(companies);
        } else {
            companies = companyRepository.findAllWithEmployees();
            return companyMapper.companiesToDTOsWithoutEmployees(companies);
        }
//        return full != null && full
//                ? companyMapper.companiesToDTOs(companies)
//                : companyMapper.companiesToDTOsWithoutEmployees(companies);
    }

    @GetMapping("/{id}")
    public CompanyDto getById(@PathVariable long id) {
        checkCompanyId(id);
        return companyMapper.companyToDTO(companyService.findById(id).get());
    }

    @PostMapping
    public CompanyDto createCompany(@RequestBody CompanyDto companyDTO) {
        return companyMapper.companyToDTO(companyService.save(companyMapper.DTOToCompany(companyDTO)));
    }

    @PutMapping("/{id}")
    public CompanyDto updateCompany(@PathVariable long id, @RequestBody CompanyDto companyDTO) {
        checkCompanyId(id);
        Company company = companyMapper.DTOToCompany(companyDTO);
        company.setId(id);
        return companyMapper.companyToDTO(companyService.save(company));
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable long id) {
        checkCompanyId(id);
        companyService.delete(id);
    }

    @PostMapping("/{id}")
    public CompanyDto addEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDTO) {
        checkCompanyId(id);
        return companyMapper.companyToDTO(companyService.addEmployee(id, employeeMapper.DTOToEmployee(employeeDTO)));
    }

    @DeleteMapping("/{company_id}/deleteEmployee/{employee_id}")
    public CompanyDto deleteEmployeeFromCompany(@PathVariable long company_id, @PathVariable long employee_id) {
        checkCompanyId(company_id, employee_id);
        return companyMapper.companyToDTO(companyService.deleteEmployee(company_id, employee_id));
    }

    @PutMapping("/{company_id}/updateEmployees")
    public CompanyDto updateEmployees(@PathVariable long company_id, @RequestBody List<EmployeeDto> newEmployeeList) {
        checkCompanyId(company_id);
        return companyMapper.companyToDTO(companyService.updateEmployeeList(company_id, employeeMapper.DTOsToEmployees(newEmployeeList)));
    }

    private void checkCompanyId(Long companyId, Long employeeId) {
        checkCompanyId(companyId);
        if (employeeService.findById(employeeId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private void checkCompanyId(Long companyId) {
        if (companyService.findById(companyId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}