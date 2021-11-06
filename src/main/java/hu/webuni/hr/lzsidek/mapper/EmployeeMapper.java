package hu.webuni.hr.lzsidek.mapper;

import hu.webuni.hr.lzsidek.dto.EmployeeDto;
import hu.webuni.hr.lzsidek.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    List<EmployeeDto> employeesToDTOs(List<Employee> employees);

    List<Employee> DTOsToEmployees(List<EmployeeDto> employees);

    EmployeeDto employeeToDTO(Employee employee);

    Employee DTOToEmployee(EmployeeDto employeeDto);
}