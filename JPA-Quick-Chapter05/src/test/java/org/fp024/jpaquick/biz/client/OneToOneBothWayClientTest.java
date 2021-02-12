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
        Employee employee = Employee.builder().name("털보가이").build();
        em.persist(employee);


        // 사원증 등록
        EmployeeCard employeeCard = EmployeeCard.builder()
                .expireDate(LocalDate.of(2025, 12, 31))
                .role("MASTER")
                .employee(employee) // 직원에 대한 참조 설정
                .build();
        em.persist(employeeCard);

        em.getTransaction().commit();
        em.close();
    }


    @Order(2)
    @Test
    void dataSelect() {
        EntityManager em = emf.createEntityManager();
        
        // 검색된 사원증을 통해 직원정보 사용하기
        EmployeeCard employeeCard = em.find(EmployeeCard.class, 1L);
        logger.info("사원증 유효기간 : {}", employeeCard.getExpireDate());
        logger.info("사원증 소유자 : {}", employeeCard.getEmployee().getName());

        // 검색된 직원을 통해 사원증 정보 사용하기
        Employee employee = em.find(Employee.class, 1L);
        logger.info("사원증 소유자: {}", employee.getName());
        logger.info("사원증 유효기간: {}", employee.getCard().getExpireDate());

        em.close();
    }

}