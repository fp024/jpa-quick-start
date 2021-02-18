package org.fp024.jpaquick.biz.service;

import org.fp024.jpaquick.biz.domain.Employee;
import org.fp024.jpaquick.biz.persistence.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void insertEmployee(Employee employee) {
        employeeRepository.insertEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.updateEmployee(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.deleteEmployee(employee);
    }

    public Employee getEmployee(Employee employee) {
        return employeeRepository.getEmployee(employee);
    }

    public List<Employee> getEmployeeList(Employee employee) {
        return employeeRepository.getEmployeeList(employee);
    }
}
