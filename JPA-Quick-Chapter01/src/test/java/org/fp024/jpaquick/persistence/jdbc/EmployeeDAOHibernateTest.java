package org.fp024.jpaquick.persistence.jdbc;

import static org.junit.Assert.assertFalse;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(JUnit4.class)
public class EmployeeDAOHibernateTest {
	private EmployeeDAOHibernate targetDAO;

	@Before
	public void before() {
		targetDAO = new EmployeeDAOHibernate();
	}

	@Test
	public void testInsertEmployee() {
		EmployeeVO vo = new EmployeeVO();
		vo.setId(100L);
		vo.setName("하이버네이트");
		vo.setDeptName("하이버네이트 개발부");
		vo.setTitle("부사장");
		vo.setStartDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
		vo.setSalary(20000.99);
		vo.setEmail("hibernate@hibernate.org");

		targetDAO.insertEmployee(vo);
	}

	@Test
	public void testGetEmployeeList() {
		List<EmployeeVO> resultList = targetDAO.getEmployeeList();
		assertFalse(resultList.isEmpty());
		resultList.forEach(e -> logger.info("===> {}", e));
	}

	@After
	public void after() {
		targetDAO.close();
	}

}
