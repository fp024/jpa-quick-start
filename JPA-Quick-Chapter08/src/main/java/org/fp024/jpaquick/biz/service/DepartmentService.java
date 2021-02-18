package org.fp024.jpaquick.biz.service;

import org.fp024.jpaquick.biz.domain.Department;
import org.fp024.jpaquick.biz.persistence.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    // ? 이게 지금 동작하나?
    @Transactional
    public void insertDepartment(Department department) {
        departmentRepository.insertDepartment(department);
    }

    public Department getDepartment(Department department) {
        return departmentRepository.getDepartment(department);
    }

}
