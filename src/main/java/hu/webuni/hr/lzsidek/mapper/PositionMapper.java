package hu.webuni.hr.lzsidek.mapper;

import hu.webuni.hr.lzsidek.dto.PositionDto;
import hu.webuni.hr.lzsidek.dto.EmployeeDto;
import hu.webuni.hr.lzsidek.model.Position;
import hu.webuni.hr.lzsidek.model.Employee;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    List<PositionDto> positionsToDTOs(List<Position> positions);

    PositionDto positionToDTO(Position position);

    Position DTOToPosition(PositionDto positionDTO);

    EmployeeDto employeeToDTO(Employee employee);

    @InheritInverseConfiguration
    Employee DTOToEmployee(EmployeeDto employeeDTO);

    @Mapping(target = "employees", ignore = true)
    @Named("summary")
    PositionDto positionToDTOWithoutEmployees(Position position);

    @IterableMapping(qualifiedByName = "summary")
    List<PositionDto> positionsToDTOsWithoutEmployees(List<Position> positions);
}