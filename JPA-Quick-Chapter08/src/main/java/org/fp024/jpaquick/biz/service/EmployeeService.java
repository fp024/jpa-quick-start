package org.fp024.jpaquick.biz.service;

import org.fp024.jpaquick.biz.domain.Employee;

import org.fp024.jpaquick.biz.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void insertEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    public Employee getEmployee(Employee employee) {
        return employeeRepository.findById(employee.getId()).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });
    }

    public List<Employee> getEmployeeList(Employee employee) {
        return employeeRepository.findByJPQL(employee.getName(), employee.getMailId());
    }
}
