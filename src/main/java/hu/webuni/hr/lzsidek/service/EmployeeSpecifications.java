package hu.webuni.hr.lzsidek.service;

import hu.webuni.hr.lzsidek.model.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class EmployeeSpecifications {

    public static Specification<Employee> hasId(long id) {
        return (root, cq, cb) -> cb.equal(root.get(Employee_.id), id);
    }

    public static Specification<Employee> hasEmployeeName(String name) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Employee_.name)), (name + "%").toLowerCase());
    }

    public static Specification<Employee> hasPosition(String position) {
        return (root, cq, cb) -> cb.equal(root.get(Employee_.position).get(Position_.name), position);
    }

    public static Specification<Employee> hasSalary(int salary) {
        return (root, cq, cb) -> cb.between(root.get(Employee_.salary), (int)(salary * 0.95), (int)(salary * 1.05));
    }

    public static Specification<Employee> hasStartDay(LocalDateTime day) {
        LocalDateTime startOfDay = LocalDateTime.of(
                day.toLocalDate(), LocalTime.of(0, 0));
        return (root, cq, cb) -> cb.between(root.get(Employee_.startDateTimeOfWork), startOfDay, startOfDay.plusDays(1L));
    }

    public static Specification<Employee> hasCompanyName(String company){
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Employee_.company).get(Company_.name)), (company + "%").toLowerCase());
    }
}