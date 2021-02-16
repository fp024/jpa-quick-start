package org.fp024.jpaquick.biz.client;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Department;
import org.fp024.jpaquick.biz.domain.Employee;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Arrays;
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
                    .salary(12700.00 * i)
                    .startDate(LocalDateTime.now())
                    .commissionPct(10.00)
                    .build();
            em.persist(employee);
            devDept.getEmployeeList().add(employee);
        });

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Employee employee = Employee.builder()
                    .name("영업맨 " + i)
                    .mailId("Virus " + i)
                    .dept(salesDept)
                    .salary(23800.00 * i)
                    .startDate(LocalDateTime.now())
                    .title("과장")
                    .commissionPct(15.00)
                    .build();
            em.persist(employee);
            salesDept.getEmployeeList().add(employee);
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
        // 크라이테리어 빌더 생성
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

        // FROM Employee emp
        Root<Employee> emp = criteriaQuery.from(Employee.class);

        // SELECT emp.dept.name, SUM(emp.salary), COUNT(emp), AVG(emp.salary)
        criteriaQuery.multiselect(
                emp.get("dept").get("name")
                , builder.sum(emp.get("salary"))
                , builder.count(emp)
                , builder.avg(emp.get("salary"))
        );

        // GROUP BY emp.dept.name
        criteriaQuery.groupBy(emp.get("dept").get("name"));

        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
        query.getResultList().forEach(row -> logger.info("---> {}", Arrays.toString(row)));

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
