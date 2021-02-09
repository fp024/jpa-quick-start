package com.fp024.jpaquick.biz.repository;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
class EmployeeDAOTest {
    private EmployeeDAO employeeDAO;

    @BeforeEach
    void before() {
        employeeDAO = new EmployeeDAO();
    }

    @Test
    void getEmployeeList() {
        List<Employee> result = employeeDAO.getEmployeeList();

        result.forEach(employee ->
                logger.info("{} 직원의 부서명: {}", employee.getName(), employee.getDept().getName()));

    }

}