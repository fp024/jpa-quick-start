package org.fp024.jpaquick.biz.client;

import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Employee;
import org.junit.jupiter.api.Test;

/**
 * https://www.baeldung.com/junit-5-runwith
 */
@Slf4j
public class EmployeeServiceClientTest {
    @Test
    public void testPersistEmployee() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter03");
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜젝션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            Employee employee = new Employee();
            employee.setName("둘리");

            tx.begin();

            // 직원등록 --> 관리상태로 전환
            em.persist(employee);

            // 엔티티 삭제
            em.remove(employee);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error(e.getMessage(), e);
            fail();
        } finally {
            em.close();
            emf.close();
        }
    }
}
