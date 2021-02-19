package org.fp024.jpaquick.biz.repository;

import org.fp024.jpaquick.biz.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByName(String name);

    Page<Employee> findByNameContaining(String name, Pageable paging);

    List<Employee> findByNameContainingOrMailIdContaining(String name, String mailId);

    List<Employee> findByMailIdContainingOrderByNameDesc(String mailId);

    @Query("SELECT emp.id, emp.name, emp.salary " +
            "FROM Employee emp " +
            "WHERE emp.name LIKE %:name% " +
            "ORDER BY emp.id DESC")
    List<Object[]> findByJPQL(@Param("name") String name, Pageable paging);


    @Query(nativeQuery = true,
            value = "SELECT id, name, salary " +
                    "FROM s_emp " +
                    "WHERE name LIKE CONCAT('%',?1,'%') " +
                    "ORDER BY id DESC "
    )
    List<Object[]> findByNativeQuery(String name);
}
