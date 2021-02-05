package org.fp024.jpaquick.persistence.jdbc;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeServiceClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceClient.class);

	public static void main(String[] args) {
		EmployeeVO vo = new EmployeeVO();
		vo.setName("홍길동");
		vo.setStartDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
		vo.setTitle("과장");
		vo.setDeptName("총무부");
		vo.setSalary(2700.99);

		EmployeeDAO employDAO = new EmployeeDAO();
		employDAO.insertEmployee(vo);

		List<EmployeeVO> employList = employDAO.getEmployeeList();

		employList.forEach(user -> LOGGER.info("---> {}", user));

	}
}
