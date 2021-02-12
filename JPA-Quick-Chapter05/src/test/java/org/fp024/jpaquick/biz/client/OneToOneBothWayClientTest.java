package org.fp024.jpaquick.biz.client;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Employee;
import org.fp024.jpaquick.biz.domain.EmployeeCard;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OneToOneBothWayClientTest {
    private static EntityManagerFactory emf;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("Chapter05");
    }

    @AfterAll
    static void afterAll() {
        if (emf != null) {
            emf.close();
        }
    }

    @Order(1)
    @Test
    void dataInsert() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // 사원증 등록
        // 카드를 등록할때 이때는 사원 정보가 없음.
        EmployeeCard employeeCard = new EmployeeCard();
        employeeCard.setExpireDate(LocalDate.of(2025, 12, 31));
        employeeCard.setRole("MASTER");
        em.persist(employeeCard);

        // 직원 등록
        Employee employee = new Employee();
        employee.setName("털보가이");
        employee.setCard(employeeCard);
        em.persist(employee);

        em.getTransaction().commit();
        em.close();
    }


    @Order(2)
    @Test
    void dataSelect() {
        EntityManager em = emf.createEntityManager();

        Employee employee = em.find(Employee.class, 1L);
        logger.info("직원을 통한 직원 정보 접근: {}", employee);

        em.close();
    }

}