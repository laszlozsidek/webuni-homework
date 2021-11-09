package hu.webuni.hr.lzsidek.repository;

import hu.webuni.hr.lzsidek.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.salary = :minSalary WHERE e.id in (SELECT e1.id FROM Employee e1 JOIN Position p ON e1.position = p WHERE p.name = :position AND e1.salary < :minSalary)")
    void setMinSalaryByPosition(@Param("position") String position, @Param("minSalary") int minSalary);

}