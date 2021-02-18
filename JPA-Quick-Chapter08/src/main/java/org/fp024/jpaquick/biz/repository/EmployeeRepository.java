package org.fp024.jpaquick.biz.repository;

import org.fp024.jpaquick.biz.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
