package hu.webuni.hr.lzsidek.service;

import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.model.Employee_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class EmployeeSpecifications {

    public static Specification<Employee> hasId(long id){
        return (root, cq, cb) -> cb.equal(root.get(Employee_.id), id);
    }

    public static Specification<Employee> hasEmployeeName(String name){
        return (root, cq, cb) -> cb.like(root.get(Employee_.name), name + "%");
    }

    public static Specification<Employee> hasPosition(String position){
        return (root, cq, cb) -> cb.equal(root.get(Employee_.position), position);
    }

    public static Specification<Employee> hasSalary(int salary){
        return (root, cq, cb) -> cb.between(root.get(Employee_.salary), (int)(salary * 0.95), (int)(salary * 1.05));
    }

    public static Specification<Employee> hasStartDay(LocalDateTime day){
        LocalDateTime startOfDay = LocalDateTime.of(
                day.toLocalDate(), LocalTime.of(0, 0));
        return (root, cq, cb) -> cb.equal(root.get(Employee_.startDateTimeOfWork), startOfDay);
    }

    public static Specification<Employee> hasCompanyName(String company){
        return (root, cq, cb) -> cb.equal(root.get(Employee_.company), company + "%");
    }
}