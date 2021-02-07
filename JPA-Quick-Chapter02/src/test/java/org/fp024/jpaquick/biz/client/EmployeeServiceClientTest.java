package org.fp024.jpaquick.biz.client;

import static org.junit.Assert.fail;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.fp024.jpaquick.biz.domain.Employee;
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
			// 직원 엔티티 생성
			Employee employee = new Employee();
			employee.setId(2L);
			employee.setName("둘리");
			employee.setMailId("gurum");
			employee.setStartDate(LocalDateTime.now());
			employee.setTitle("과장");
			employee.setDeptName("총무부");
			employee.setSalary(2500.00);
			employee.setCommissionPct(12.50);

			// 트랜젝션 시작
			tx.begin();
			
			// 직원 등록처리
			em.persist(employee);
			
			// 트랜젝션 종료 (COMMIT)
			tx.commit();

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
