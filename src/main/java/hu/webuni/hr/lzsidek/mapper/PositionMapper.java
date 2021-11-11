package hu.webuni.hr.lzsidek.mapper;

import hu.webuni.hr.lzsidek.dto.PositionDto;
import hu.webuni.hr.lzsidek.model.Position;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    List<PositionDto> positionsToDTOs(List<Position> positions);

    PositionDto positionToDTO(Position position);

    Position DTOToPosition(PositionDto positionDTO);

    @Mapping(target = "employees", ignore = true)
    @Named("summary")
    PositionDto positionToDTOWithoutEmployees(Position position);

    @IterableMapping(qualifiedByName = "summary")
    List<PositionDto> positionsToDTOsWithoutEmployees(List<Position> positions);
}