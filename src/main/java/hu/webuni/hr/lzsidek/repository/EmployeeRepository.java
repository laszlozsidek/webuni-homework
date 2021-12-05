package hu.webuni.hr.lzsidek.repository;

import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.model.Position;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, PagingAndSortingRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

//    List<Employee> findByPosition(String position);
    List<Employee> findByPosition(Position position);

    List<Employee> findByNameStartingWithIgnoreCase(String startingWith);

    List<Employee> findByStartDateTimeOfWorkBetween(LocalDateTime firstDate, LocalDateTime lastDate);

    List<Employee> findBySalaryGreaterThan(Integer minSalary);

    List<Employee> findByPosition(Position position, Pageable pageable);

    List<Employee> findAll(Specification<Employee> spec, Sort id);

    @Query("SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.company")
    List<Employee> findAllWithCompanies();

    Optional<Employee> findByUsername(String username);
}