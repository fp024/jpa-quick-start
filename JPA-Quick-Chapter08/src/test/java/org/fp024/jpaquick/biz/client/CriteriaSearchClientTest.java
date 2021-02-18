package org.fp024.jpaquick.biz.client;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Department;
import org.fp024.jpaquick.biz.domain.Employee;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CriteriaSearchClientTest {
    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("Chapter08");
    }

    @AfterAll
    static void afterAll() {
        emf.close();
    }

    @org.junit.jupiter.api.Order(1)
    @Test
    void dataInsert() {
        em.getTransaction().begin();

        // 부서 정보 등록
        Department devDept = Department.builder()
                .name("개발부").build();
        em.persist(devDept);

        Department salesDept = Department.builder()
                .name("영업부").build();
        em.persist(salesDept);

        // 직원 정보 등록
        IntStream.rangeClosed(1, 3).forEach(i -> {
            Employee employee = Employee.builder()
                    .name("개발맨 " + i)
                    .mailId("Corona " + i)
                    .dept(devDept)
                    .build();
            em.persist(employee);
            devDept.getEmployeeList().add(employee);
        });

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Employee employee = Employee.builder()
                    .name("영업맨 " + i)
                    .mailId("Virus " + i)
                    .dept(salesDept)
                    .build();
            em.persist(employee);
            salesDept.getEmployeeList().add(employee);
        });

        // 부서 정보가 없는 직원 등록
        Employee employee = Employee.builder()
                .name("아르바이트")
                .mailId("Alba-01")
                .build();
        em.persist(employee);

        em.getTransaction().commit();
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    void dataSelect() {

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
