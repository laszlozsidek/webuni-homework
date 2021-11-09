package hu.webuni.hr.lzsidek.web;

import hu.webuni.hr.lzsidek.dto.EmployeeDto;
import hu.webuni.hr.lzsidek.enums.MinimalEducation;
import hu.webuni.hr.lzsidek.model.Position;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerIT {

    private static final String BASE_URI = "/api/employees";

    @Autowired
    WebTestClient webTestClient;

    @Test
    void testThatEmployeeIsCreatedWithValidData() throws Exception {
        List<EmployeeDto> employeesBeforeCreate = getEmployees();

        EmployeeDto employeeDto = new EmployeeDto(2, "name", new Position(1L, "CEO", MinimalEducation.COLLEGE, 200000), 3, LocalDateTime.of(2012, 1, 1, 0, 0, 0));

        createEmployee(employeeDto);

        List<EmployeeDto> employeesAfterCreate = getEmployees();

        assertThat(employeesAfterCreate.get(employeesBeforeCreate.size())).usingRecursiveComparison().isEqualTo(employeeDto);
    }

    @Test
    void testThatEmployeeIsCreatedWithNotValidData() throws Exception {
        List<EmployeeDto> employeesBeforeCreate = getEmployees();

        EmployeeDto employeeDto = new EmployeeDto(2, "", new Position(1L, "CEO", MinimalEducation.COLLEGE, 200000), 3, LocalDateTime.of(2012, 1, 1, 0, 0, 0));

        webTestClient.post().uri(BASE_URI).bodyValue(employeeDto).exchange().expectStatus().isBadRequest();

        List<EmployeeDto> employeesAfterCreate = getEmployees();

        assertThat(employeesAfterCreate.size()).isEqualTo(employeesBeforeCreate.size());
        assertThat(employeesAfterCreate).usingFieldByFieldElementComparator().containsExactlyElementsOf(employeesBeforeCreate);
    }

    @Test
    void testThatEmployeeIsUpdatedWithValidData() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto(2, "name", new Position(1L, "CEO", MinimalEducation.COLLEGE, 200000), 3, LocalDateTime.of(2012, 1, 1, 0, 0, 0));

        createEmployee(employeeDto);

        List<EmployeeDto> employeesBeforeUpdate = getEmployees();

        employeeDto.setName("updatedName");

        webTestClient.put().uri(BASE_URI + "/" + employeeDto.getId()).bodyValue(employeeDto).exchange().expectStatus().isOk();

        List<EmployeeDto> employeesAfterUpdate = getEmployees();

        assertThat(employeesAfterUpdate.get(employeesBeforeUpdate.size() - 1)).usingRecursiveComparison().isEqualTo(employeeDto);
    }

    @Test
    void testThatEmployeeIsUpdatedWithNotValidData() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto(2, "name", new Position(1L, "CEO", MinimalEducation.COLLEGE, 200000), 3, LocalDateTime.of(2012, 1, 1, 0, 0, 0));

        createEmployee(employeeDto);

        List<EmployeeDto> employeesBeforeUpdate = getEmployees();

        employeeDto.setSalary(-500);

        webTestClient.put().uri(BASE_URI + "/" + employeeDto.getId()).bodyValue(employeeDto).exchange().expectStatus().isBadRequest();

        List<EmployeeDto> employeesAfterUpdate = getEmployees();

        assertThat(employeesAfterUpdate.size()).isEqualTo(employeesBeforeUpdate.size());
        assertThat(employeesAfterUpdate).usingFieldByFieldElementComparator().containsExactlyElementsOf(employeesBeforeUpdate);
    }

    private void createEmployee(EmployeeDto employeeDto) {
        webTestClient.post().uri(BASE_URI).bodyValue(employeeDto).exchange().expectStatus().isOk();
    }

    private List<EmployeeDto> getEmployees() {
        List<EmployeeDto> responseBody = webTestClient.get().uri(BASE_URI).exchange().expectStatus().isOk().expectBodyList(EmployeeDto.class).returnResult().getResponseBody();

        assert responseBody != null;
        responseBody.sort(Comparator.comparingLong(EmployeeDto::getId));

        return responseBody;
    }
}