package org.fp024.jpaquick.biz.client;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Department;
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
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JQLBasicJoinClientTest {
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
        Department department1 = Department.builder().name("개발부").build();

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Employee employee = Employee.builder()
                    .name("개발직원" + i)
                    .salary(i * 12700.00)
                    .mailId("Dev-" + i)
                    .dept(department1).build();
            department1.getEmployeeList().add(employee);
        });
        em.persist(department1);


        Department department2 = Department.builder().name("영업부").build();

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Employee employee = Employee.builder()
                    .name("영업직원" + i)
                    .salary(i * 27300.00)
                    .mailId("Sale-" + i)
                    .dept(department2).build();
            department2.getEmployeeList().add(employee);
        });
        em.persist(department2);

        Department department3 = Department.builder().name("인재개발부").build();
        em.persist(department3);

        em.getTransaction().commit();
    }

    @Order(2)
    @Test
    void dataSelect() {
        String jpql = "SELECT e, d FROM Employee e INNER JOIN e.dept d";

        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

        List<Object[]> result = query.getResultList();

        logger.info("검색된 직원 목록");
        result.forEach(joinedRow -> {
            Employee employee = (Employee) joinedRow[0];
            Department department = (Department) joinedRow[1];
            logger.info("{}의 부서 {}", employee.getName(), department.getName());
        });

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
