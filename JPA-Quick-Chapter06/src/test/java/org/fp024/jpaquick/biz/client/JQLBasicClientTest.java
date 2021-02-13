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
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
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
        String jpql = "SELECT e FROM Employee e WHERE e.id = 1L";

        TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);

        Employee findEmp1 = query.getSingleResult();

        Employee findEmp2 = query.getSingleResult();

        if (findEmp1 == findEmp2) {
            logger.info("두 객체의 주소는 동일하다");
        }
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
