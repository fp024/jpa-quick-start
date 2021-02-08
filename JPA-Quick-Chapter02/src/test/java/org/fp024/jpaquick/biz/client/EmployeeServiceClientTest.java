package org.fp024.jpaquick.biz.client;

import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.fp024.jpaquick.biz.domain.Employee;
import org.fp024.jpaquick.biz.domain.EmployeeId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(JUnit4.class)
public class EmployeeServiceClientTest {
	@Test
	public void testPersistEmployee() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {
//			tx.begin();
//			// 회원등록 요청
//			EmployeeId empId = new EmployeeId(1L, "guest123");
//			
//			Employee employee = new Employee();
//			employee.setEmpId(empId);
//			employee.setName("직원1");
//			em.persist(employee);
//			tx.commit();

			EmployeeId empId = new EmployeeId(1L, "guest123");
			
			// 등록한 회원 검색
			Employee foundEmployee = em.find(Employee.class, empId);
			logger.info("검색한 회원정보: {}", foundEmployee);

		} catch (Exception e) {
			// 트랜젝션 종료 (ROLLBACK)
			tx.rollback();
			logger.error(e.getMessage(), e);
			fail();
		} finally {
			em.close();
			emf.close();
		}
	}

}
