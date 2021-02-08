package org.fp024.jpaquick.biz.client;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Employee;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
        EntityTransaction tx = em.getTransaction();

        try {
            // 직원등록
            tx.begin();
            for (int i = 1; i <= 10; i++) {
                Employee employee = new Employee();
                employee.setName("직원-" + i);
                em.persist(employee);
            }
            tx.commit();

            // 직원 목록 조회
            String jpql = "SELECT e FROM Employee e ORDER BY e.id DESC";
            List<Employee> employeeList = em.createQuery(jpql, Employee.class).getResultList();

            employeeList.forEach(employee -> logger.info("---> {}", employee));

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            fail();
        } finally {
            em.close();
            emf.close();
        }
    }
}
