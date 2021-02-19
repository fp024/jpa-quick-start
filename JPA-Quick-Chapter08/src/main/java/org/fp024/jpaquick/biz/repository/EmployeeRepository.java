package org.fp024.jpaquick.biz.repository;

import org.fp024.jpaquick.biz.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByName(String name);

    List<Employee> findByNameContaining(String name);

    List<Employee> findByNameContainingOrMailIdContaining(String name, String mailId);

    List<Employee> findByMailIdContainingOrderByNameDesc(String mailId);
}
