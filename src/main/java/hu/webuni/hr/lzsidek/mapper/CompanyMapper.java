package hu.webuni.hr.lzsidek.mapper;

import hu.webuni.hr.lzsidek.dto.CompanyDto;
import hu.webuni.hr.lzsidek.dto.EmployeeDto;
import hu.webuni.hr.lzsidek.model.Company;
import hu.webuni.hr.lzsidek.model.Employee;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    List<CompanyDto> companiesToDTOs(List<Company> companies);

    CompanyDto companyToDTO(Company company);

    Company DTOToCompany(CompanyDto companyDTO);

    EmployeeDto employeeToDTO(Employee employee);

    Employee DTOToEmployee(EmployeeDto employeeDTO);

    @Mapping(target = "employees", ignore = true)
    @Named("summary")
    CompanyDto companyToDTOWithoutEmployees(Company company);

    @IterableMapping(qualifiedByName = "summary")
    List<CompanyDto> companiesToDTOsWithoutEmployees(List<Company> companies);
}
