package hu.webuni.hr.lzsidek.repository;

import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.model.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, PagingAndSortingRepository<Employee, Long> {

//    List<Employee> findByPosition(String position);
    List<Employee> findByPosition(Position position);

    List<Employee> findByNameStartingWithIgnoreCase(String startingWith);

    List<Employee> findByStartDateTimeOfWorkBetween(LocalDateTime firstDate, LocalDateTime lastDate);

    List<Employee> findBySalaryGreaterThan(Integer minSalary);

    List<Employee> findByPosition(Position position, Pageable pageable);
}