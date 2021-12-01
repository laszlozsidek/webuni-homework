package hu.webuni.hr.lzsidek.mapper;

import hu.webuni.hr.lzsidek.dto.CompanyDto;
import hu.webuni.hr.lzsidek.dto.EmployeeDto;
import hu.webuni.hr.lzsidek.model.Company;
import hu.webuni.hr.lzsidek.model.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    List<EmployeeDto> employeesToDTOs(List<Employee> employees);

    @Mapping(target = "position", source = "position.name")
//    @Mapping(target = "company", ignore = true)
    EmployeeDto employeeToDTO(Employee employee);

    @Mapping(target = "employees", ignore = true)
    CompanyDto companyToDto(Company c);

    List<Employee> DTOsToEmployees(List<EmployeeDto> employees);

//    @Mapping(target = "position.name", source = "position")
    @InheritInverseConfiguration
    Employee DTOToEmployee(EmployeeDto employeeDto);
}