package com.fp024.jpaquick.biz.repository;


import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Department;
import org.fp024.jpaquick.biz.domain.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EmployeeDAO {
    // JDBC 변수 선언
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    private static final String LIST_EMP =
            "SELECT e.id, e.`name`, d.dept_id, d.`name` AS dept_name " +
            "  FROM s_emp e " +
            " INNER JOIN  s_dept d " +
            "    ON e.dept_id = d.dept_id " +
            " ORDER BY e.id ASC;";

    // 회원 목록
    public List<Employee> getEmployeeList() {
        logger.info("===> JDBC 기반으로 직원 목록 조회 기능 처리");
        List<Employee> employeeList = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(LIST_EMP);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getLong("id"));
                employee.setName(rs.getString("name"));

                Department department = new Department();
                department.setDeptId(rs.getLong("dept_id"));
                department.setName(rs.getString("dept_name"));

                employee.setDept(department);
                employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, stmt, conn);
        }

        return employeeList;
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // com.mysql.jdbc.Driver 는 폐기상태라고 한다.
            String url = "jdbc:mysql://192.168.100.10/jpa_test";
            conn = DriverManager.getConnection(url, "jpa_test", "jpa_test");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    private void close(PreparedStatement stmt, Connection conn) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt = null;
        }

        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }

    private void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs = null;
        }

        close(stmt, conn);
    }
}
