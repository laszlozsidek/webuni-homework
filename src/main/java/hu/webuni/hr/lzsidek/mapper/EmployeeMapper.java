package hu.webuni.hr.lzsidek.mapper;

import hu.webuni.hr.lzsidek.dto.EmployeeDto;
import hu.webuni.hr.lzsidek.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    List<EmployeeDto> employeesToDTOs(List<Employee> employees);

    List<Employee> DTOsToEmployees(List<EmployeeDto> employees);

    @Mapping(target = "position", source = "position.name")
    EmployeeDto employeeToDTO(Employee employee);

    @Mapping(target = "position.name", source = "position")
    Employee DTOToEmployee(EmployeeDto employeeDto);
}