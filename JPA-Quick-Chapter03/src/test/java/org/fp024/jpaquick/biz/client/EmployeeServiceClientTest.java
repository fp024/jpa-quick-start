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

        try {
            // 직원 엔티티 검색
            Employee employee = em.getReference(Employee.class, 1L);
            logger.info("검색된 직원 이름 : {}" , employee.getName());

        } catch (Exception e) {

            logger.error(e.getMessage(), e);
            fail();

        } finally {
            em.close();
            emf.close();
        }
    }
}
