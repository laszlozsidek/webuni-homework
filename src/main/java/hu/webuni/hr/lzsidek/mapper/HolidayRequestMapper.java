package hu.webuni.hr.lzsidek.mapper;

import hu.webuni.hr.lzsidek.dto.HolidayRequestDto;
import hu.webuni.hr.lzsidek.model.HolidayRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.validation.Valid;
import java.util.List;


@Mapper(componentModel = "spring")
public interface HolidayRequestMapper {
	List<HolidayRequestDto> holidayRequestsToDTOs(List<HolidayRequest> holidayRequests);
	
	@Mapping(source = "employee.id", target = "employeeId")
	@Mapping(source = "approver.id", target = "approverId")
	HolidayRequestDto holidayRequestToDTO(HolidayRequest holidayRequest);

	@Mapping(target = "employee", ignore = true)
	@Mapping(target = "approver", ignore = true)
	HolidayRequest DTOToHolidayRequest(@Valid HolidayRequestDto holidayRequestDTO);

	List<HolidayRequest> DTOsToHolidayRequests(List<HolidayRequestDto> holidayRequestDTOs);
}