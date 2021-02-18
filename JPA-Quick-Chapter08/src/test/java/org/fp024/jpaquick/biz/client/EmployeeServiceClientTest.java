package org.fp024.jpaquick.biz.client;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Department;
import org.fp024.jpaquick.biz.domain.Employee;
import org.fp024.jpaquick.biz.service.DepartmentService;
import org.fp024.jpaquick.biz.service.EmployeeService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.IntStream;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/spring/business-layer.xml")
public class EmployeeServiceClientTest {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Order(1)
    @Test
    void doInsert() {
        Department department1 = Department.builder()
                .name("개발부").build();
        departmentService.insertDepartment(department1);

        Department department2 = Department.builder()
                .name("영업부").build();
        departmentService.insertDepartment(department2);

        IntStream.rangeClosed(1, 5).forEach(
                i -> {
                    Employee employee = Employee.builder()
                            .name("개발직원" + i)
                            .salary(i * 12700.00)
                            .mailId("Dev-" + i)
                            .dept(department1).build();
                    department1.getEmployeeList().add(employee);
                    employeeService.insertEmployee(employee);
                }
        );

        IntStream.rangeClosed(1, 7).forEach(
                i -> {
                    Employee employee = Employee.builder()
                            .name("영업직원" + i)
                            .salary(i * 24300.00)
                            .mailId("Sales-" + i)
                            .dept(department2).build();
                    department2.getEmployeeList().add(employee);
                    employeeService.insertEmployee(employee);
                }
        );
    }

    @Order(2)
    @Test
    void doSelect() {
        Department department = departmentService.getDepartment(Department.builder().deptId(1L).build());
        logger.info(department.toString());
        department.getEmployeeList().forEach(e -> logger.info("\t{}", e.getName()));
    }

}
