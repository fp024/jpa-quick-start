package org.fp024.jpaquick.persistence.jdbc;

import static org.junit.Assert.assertFalse;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(JUnit4.class)
public class EmployeeDAOTest {

	@Test
	public void testEmployDAO() {
		EmployeeVO vo = new EmployeeVO();
		vo.setName("홍길동");
		vo.setStartDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
		vo.setTitle("과장");
		vo.setDeptName("총무부");
		vo.setSalary(2700.99);

		EmployeeDAO employDAO = new EmployeeDAO();
		employDAO.insertEmployee(vo);

		List<EmployeeVO> employList = employDAO.getEmployeeList();

		employList.forEach(user -> logger.info("---> {}", user));

		assertFalse(employList.isEmpty());

	}

}
