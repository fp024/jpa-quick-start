package org.fp024.jpaquick.biz.client;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Employee;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.IntStream;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JQLBasicClientTest {
    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("Chapter06");
    }

    @AfterAll
    static void afterAll() {
        emf.close();
    }

    @Order(1)
    @Test
    void dataInsert() {
        em.getTransaction().begin();

        IntStream.rangeClosed(1, 10).forEach(
                i -> {
                    Employee employee = Employee.builder()
                            .name("직원" + i)
                            .mailId("anti-corona" + i)
                            .deptName("개발부")
                            .salary(12700.00 * i)
                            .startDate(LocalDateTime.now())
                            .title("사원")
                            .commissionPct(15.00)
                            .build();

                    em.persist(employee);
                }
        );

        em.getTransaction().commit();
    }

    @Order(2)
    @Test
    void dataSelect() {
        // JPQL
        String jpql = "SELECT id, name, deptName, salary " +
                "FROM Employee " +
                "WHERE id=:employeeId AND name = :employeeName ";

        // JPQL 전송
        Query query = em.createQuery(jpql);
        query.setParameter("employeeId", 1L);
        query.setParameter("employeeName", "직원1");


        // 검색 결과 처리
        Object[] result = (Object[]) query.getSingleResult();
        logger.info("{}번 직원의 정보", result[0]);
        logger.info(Arrays.toString(result));

    }

    @BeforeEach
    void beforeEach() {
        em = emf.createEntityManager();
    }

    @AfterEach
    void afterEach() {
        em.close();
    }

}
