package org.fp024.jpaquick.biz.service;

import org.fp024.jpaquick.biz.domain.Department;
import org.fp024.jpaquick.biz.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void insertDepartment(Department department) {
        departmentRepository.save(department);
    }

    public Department getDepartment(Department department) {
        return departmentRepository.findById(department.getDeptId()).orElseThrow(() -> {
                    throw new EntityNotFoundException();
                }
        );
    }
}
