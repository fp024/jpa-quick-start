package org.fp024.jpaquick.biz.client;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Employee;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.fail;

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
            // 직원 엔티티 등록
            Employee employee = new Employee();
            employee.setName("둘리");

            tx.begin();
            em.persist(employee);
            tx.commit();

            for (int i = 0; i < 30; i++) {
                Thread.sleep(1000);
                logger.info("다른 사용자가 데이터 수정중.... {}", i);
            }

            // 엔티티 REFRESH
            em.refresh(employee);
            logger.info("갱신된 직원정보: {}", employee);

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
