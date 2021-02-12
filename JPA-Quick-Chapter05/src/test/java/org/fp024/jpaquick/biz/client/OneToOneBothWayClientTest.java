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

        // 직원 등록
        Employee employee = new Employee();
        employee.setName("털보가이");
        em.persist(employee);

        // 사원증 등록
        EmployeeCard employeeCard = new EmployeeCard();
        employeeCard.setExpireDate(LocalDate.of(2025, 12, 31));
        employeeCard.setRole("MASTER");
        // 직원에 대한 참조 설정
        employeeCard.setEmployee(employee);
        em.persist(employeeCard);

        em.getTransaction().commit();
        em.close();
    }


    @Order(2)
    @Test
    void dataSelect() {
        EntityManager em = emf.createEntityManager();

        // EmployeeCard 를 조회할 때, EmployeeCard.employee 를 EAGER 전략에 의해 Employee도 select하여 가져오기 때문에, ...
        EmployeeCard employeeCard = em.find(EmployeeCard.class, 1L);
        logger.info("검색된 사원증 번호: {}", employeeCard.getCardId());
        logger.info("권한: {}", employeeCard.getRole());
        logger.info("사원증 소유자: {}", employeeCard.getEmployee().getName());

        // 여기서는 select가 일어나지 않는다. EmployeeCard를 조회할 때 이미 조회했음.
        Employee employee = em.find(Employee.class, 1L);
        logger.info("검색된 직원 이름: {}", employee.getName());
        logger.info("직원이 소유한 사원증 권한: {}", employee.getCard().getRole());

        em.close();
    }

}