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
            tx.begin();
            Employee employee = new Employee();
            employee.setName("털보가이");
            employee.setFavoriteGame("어세신 오리진");
            em.persist(employee);


            // 직원 검색
            Employee findEmp = em.find(Employee.class, 1L);

            // 직원 이름변경

            findEmp.setName("털보가이2");
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
