package org.fp024.jpaquick.biz.client;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Employee;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CriteriaSearchClientTest {
    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("Chapter07");
    }

    @AfterAll
    static void afterAll() {
        emf.close();
    }

    @Order(1)
    @Test
    void dataInsert() {
        em.getTransaction().begin();
        // 직원 정보 등록
        IntStream.rangeClosed(1, 3).forEach(i -> {
            Employee employee = Employee.builder()
                    .name("개발맨 " + i)
                    .mailId("Corona " + i)
                    .deptName("개발부")
                    .salary(12700.00 * i)
                    .startDate(LocalDateTime.now())
                    .commissionPct(10.00)
                    .build();
            em.persist(employee);
        });

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Employee employee = Employee.builder()
                    .name("영업맨 " + i)
                    .mailId("Virus " + i)
                    .deptName("영업부")
                    .salary(23800.00 * i)
                    .startDate(LocalDateTime.now())
                    .title("과장")
                    .commissionPct(15.00)
                    .build();
            em.persist(employee);
        });

        // 부서 정보가 없는 직원 등록
        Employee employee = Employee.builder()
                .name("아르바이트")
                .mailId("Alba-01")
                .salary(10000.00)
                .build();
        em.persist(employee);

        em.getTransaction().commit();
    }

    @Order(2)
    @Test
    void dataSelect() {
        // 검색 정보 설정
        String searchCondition = "NAME";
        String searchKeyword = "아르바이트";

        // 검색관련 쿼리
        String jpqlByMailId = "SELECT e FROM Employee e WHERE e.mailId = :searchKeyword";

        String jpqlByName = "SELECT e FROM Employee e WHERE e.name = :searchKeyword";

        String jpqlByTitle = "SELECT e FROM Employee e WHERE e.title = :searchKeyword";

        TypedQuery<Employee> query;

        // 검색조건에 따른 분기 처리
        if (searchCondition.equals("NAME")) {
            query = em.createQuery(jpqlByName, Employee.class);
        } else if (searchCondition.equals("MAILID")) {
            query = em.createQuery(jpqlByMailId, Employee.class);
        } else if (searchCondition.equals("TITLE")) {
            query = em.createQuery(jpqlByTitle, Employee.class);
        } else {
            throw new IllegalArgumentException("잘못된 검색 조건: " + searchCondition);
        }

        query.setParameter("searchKeyword", searchKeyword);

        List<Employee> resultList = query.getResultList();

        resultList.forEach(e -> logger.info("---> {}", e));

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
