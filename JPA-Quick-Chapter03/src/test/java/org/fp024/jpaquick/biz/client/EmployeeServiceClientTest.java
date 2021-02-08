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
            employee.setName("둘리");

            // 직원등록
            tx.begin();
            em.persist(employee);
            tx.commit();

            // 직원 검색
            Employee employee1 = em.find(Employee.class, 1L);
            Employee employee2 = em.find(Employee.class, 1L);

            if (employee1 == employee2) {
                logger.info("검색한 두 객체는 동일한 객체다.");
            }

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
