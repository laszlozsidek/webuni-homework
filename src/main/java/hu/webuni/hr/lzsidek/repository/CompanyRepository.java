package hu.webuni.hr.lzsidek.repository;

import hu.webuni.hr.lzsidek.model.Company;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findDistinctByEmployeesSalaryGreaterThan(Integer minSalary);

    @Query("SELECT c FROM Company c WHERE size(c.employees) > :limit")
    List<Company> findByEmployeesSizeGreaterThan(@Param("limit") Integer limit);

    @Query("SELECT avg(e.salary) as salary FROM Company c JOIN Employee e ON c = e.company WHERE c.id = :companyId GROUP BY e.position ORDER By salary desc")
    List<Double> findByAverageSalaryGroupByPositionDesc(@Param("companyId") Long companyId);

//    @Query("SELECT DISTINCT c FROM Company c LEFT JOIN FETCH c.employees")
    @EntityGraph(attributePaths = {"employees", "employees.position"})
    @Query("SELECT c FROM Company c")
    List<Company> findAllWithEmployees();
}
