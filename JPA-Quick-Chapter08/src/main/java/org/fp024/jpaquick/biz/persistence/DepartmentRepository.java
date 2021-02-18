package org.fp024.jpaquick.biz.persistence;


import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Department;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Repository
public class DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void insertDepartment(Department department) {
        logger.info("===> JPA로 insertDepartment() 기능 처리");
        entityManager.persist(department);
    }
    
    public Department getDepartment(Department department) {
        logger.info("===> JPA로 getDepartment() 기능 처리");
        return entityManager.find(Department.class, department.getDeptId());
    }

}
