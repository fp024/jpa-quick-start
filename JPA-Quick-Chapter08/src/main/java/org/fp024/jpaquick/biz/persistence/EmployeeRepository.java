package org.fp024.jpaquick.biz.persistence;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
public class EmployeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void insertEmployee(Employee employee) {
        logger.info("===> JPA로 insertEmployee() 기능 처리");
        entityManager.persist(employee);
    }

    public void updateEmployee(Employee employee) {
        logger.info("===> JPA로 updateEmployee() 기능 처리");
        entityManager.merge(employee);
    }

    public void deleteEmployee(Employee employee) {
        logger.info("===> JPA로 deleteEmployee() 기능 처리 ");
        entityManager.remove(employee);
    }

    public Employee getEmployee(Employee employee) {
        logger.info("===> JPA로 getEmployee() 기능 처리 ");
        return entityManager.find(Employee.class, employee.getId());
    }

    public List<Employee> getEmployeeList(Employee employee) {
        logger.info("===> JPA로 getEmployeeList() 기능 처리 ");
        return entityManager.createQuery("SELECT emp FROM Employee emp ORDER BY emp.id DESC ", Employee.class).getResultList();
    }



}
