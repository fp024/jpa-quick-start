package org.fp024.jpaquick.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeDAO {

	// JDBC 변수 선언
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// S_EMP 테이블 관련 SQL 구문

	private final String INSERT_EMP = "INSERT INTO s_emp (id, name, start_date, title, dept_name, salary, email) "
			+ "VALUES ((SELECT IFNULL(MAX(id), 0) + 1 FROM s_emp AS temp), ?, ?, ?, ?, ?, ?)";

	/*
		INSERT 할 테이블에 대해 조회하는 코드가 들어가면
		You can't specify target table 테이블명 for update in FROM clause  이런 오류가 생긴다.
		해당 FROM 테이블에 별칭을 붙여주면 해결됨.
	*/
	private final String LIST_EMP = "SELECT id, name, start_date, title, dept_name, salary, email "
  								  + "FROM s_emp ORDER BY name";  

	// 회원등록 기능
	public void insertEmployee(EmployeeVO vo) {
		logger.info("===> JDBC 기반으로 직원 등록 기능 처리");
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(INSERT_EMP);
			stmt.setString(1, vo.getName());
			stmt.setTimestamp(2, new Timestamp(vo.getStartDate().getTime()));
			stmt.setString(3, vo.getTitle());
			stmt.setString(4, vo.getDeptName());
			stmt.setDouble(5, vo.getSalary());
			stmt.setString(6, vo.getEmail());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt, conn);
		}

	}

	// 회원 목록
	public List<EmployeeVO> getEmployeeList() {
		logger.info("===> JDBC 기반으로 직원 목록 조회 기능 처리");
		List<EmployeeVO> employeeList = new ArrayList<>();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(LIST_EMP);
			rs = stmt.executeQuery();
			while (rs.next()) {
				EmployeeVO employee = new EmployeeVO();
				employee.setId(rs.getLong("id"));
				employee.setName(rs.getString("name"));
				employee.setStartDate(rs.getTimestamp("start_date"));
				employee.setTitle(rs.getString("title"));
				employee.setDeptName(rs.getString("dept_name"));
				employee.setSalary(rs.getDouble("salary"));
				employee.setEmail(rs.getString("email"));
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
