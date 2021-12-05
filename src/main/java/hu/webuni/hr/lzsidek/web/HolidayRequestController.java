package hu.webuni.hr.lzsidek.web;

import hu.webuni.hr.lzsidek.dto.HolidayRequestDto;
import hu.webuni.hr.lzsidek.dto.HolidayRequestFilterDto;
import hu.webuni.hr.lzsidek.mapper.HolidayRequestMapper;
import hu.webuni.hr.lzsidek.model.HolidayRequest;
import hu.webuni.hr.lzsidek.service.HolidayRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/holidayrequests")
public class HolidayRequestController {

	@Autowired
	HolidayRequestService holidayRequestService;

	@Autowired
	HolidayRequestMapper holidayRequestMapper;

	@GetMapping
	public List<HolidayRequestDto> getAll() {
		return holidayRequestMapper.holidayRequestsToDTOs(holidayRequestService.findAll());
	}

	@GetMapping("/{id}")
	public HolidayRequestDto getById(@PathVariable long id) {
		HolidayRequest holidayRequest = holidayRequestService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return holidayRequestMapper.holidayRequestToDTO(holidayRequest);
	}

	@PostMapping(value = "/search")
	public List<HolidayRequestDto> findByExample(@RequestBody HolidayRequestFilterDto example,
			Pageable pageable) {
		Page<HolidayRequest> page = holidayRequestService.findHolidayRequestsByExample(example, pageable);
		return holidayRequestMapper.holidayRequestsToDTOs(page.getContent());
	}

	@PostMapping
	@PreAuthorize("#newHolidayRequest.employeeId == authentication.principal.employee.employeeId")
	public HolidayRequestDto addHolidayRequest(@RequestBody @Valid HolidayRequestDto newHolidayRequest) {
		HolidayRequest holidayRequest;
		try {
			holidayRequest = holidayRequestService.addHolidayRequest(holidayRequestMapper.DTOToHolidayRequest(newHolidayRequest), newHolidayRequest.getEmployeeId());
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return holidayRequestMapper.holidayRequestToDTO(holidayRequest);
	}

	@PutMapping("/{id}")
	public HolidayRequestDto modifyHolidayRequest(@PathVariable long id, @RequestBody @Valid HolidayRequestDto modifiedHolidayRequest) {
		modifiedHolidayRequest.setEmployeeId(id);
		HolidayRequest holidayRequest;
		try {
			holidayRequest = holidayRequestService.modifyHolidayRequest(id, holidayRequestMapper.DTOToHolidayRequest(modifiedHolidayRequest));
		} catch (InvalidParameterException e) {
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return holidayRequestMapper.holidayRequestToDTO(holidayRequest);
	}

	@DeleteMapping("/{id}")
	public void deleteHolidayRequest(@PathVariable long id) {
		try {
			holidayRequestService.deleteHolidayRequest(id);
		} catch (InvalidParameterException e) {
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/{id}/approval", params = { "status", "approverId" })
	public HolidayRequestDto approveHolidayRequest(@PathVariable long id, @RequestParam long approverId, @RequestParam boolean status) {
		HolidayRequest holidayRequest;
		try {
			holidayRequest = holidayRequestService.approveHolidayRequest(id, approverId, status);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return holidayRequestMapper.holidayRequestToDTO(holidayRequest);
	}
}