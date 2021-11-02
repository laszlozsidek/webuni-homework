package hu.webuni.hr.lzsidek.repository;

import hu.webuni.hr.lzsidek.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}