package hu.webuni.hr.lzsidek.repository;

import hu.webuni.hr.lzsidek.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByPosition(String position);

    List<Employee> findByNameStartingWithIgnoreCase(String startingWith);

    List<Employee> findByStartDateTimeOfWorkBetween(LocalDateTime firstDate, LocalDateTime lastDate);

    List<Employee> findBySalaryGreaterThan(Integer minSalary);
}