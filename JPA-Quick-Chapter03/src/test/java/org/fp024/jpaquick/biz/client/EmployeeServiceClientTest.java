package org.fp024.jpaquick.biz.client;

import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.*;


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
        em.setFlushMode(FlushModeType.COMMIT);

        // 엔티티 트랜젝션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            Employee employee = new Employee();
            employee.setName("털보가이");

            tx.begin();
            em.persist(employee);
            tx.commit();

            // 모든 엔티티를 분리 상태로 전환시킨다.
            em.clear();

            // 직원 엔티티 이름 수정
            tx.begin();
            employee.setName("탈보가이");
            Employee employeeEmp = em.merge(employee);
            tx.commit();

            // 관리상태 여부 확인
            logger.info("employee 관리: {}", em.contains(employee));
            logger.info("employeeEmp 관리: {}", em.contains(employeeEmp));

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
