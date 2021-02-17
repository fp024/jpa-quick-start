package org.fp024.jpaquick.biz.client;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Department;
import org.fp024.jpaquick.biz.domain.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import java.time.LocalDateTime;
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

    @org.junit.jupiter.api.Order(2)
    @Test
    void dataSelect() {
        // 크라이테리어 빌더 생성
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);

        /** 서브쿼리 생성 */
        Subquery<Double> subQuery = criteriaQuery.subquery(Double.class);

        // FROM Employee e
        Root<Employee> e = subQuery.from(Employee.class);

        // SELECT AVG(e.salary)
        subQuery.select(builder.avg(e.<Double>get("salary")));

        /** 메인 쿼리 생성 */
        // FROM Employee emp
        Root<Employee> emp = criteriaQuery.from(Employee.class);

        // SELECT emp
        criteriaQuery.select(emp);

        // JOIN FETCH emp.dept dept
        emp.fetch("dept");


        /** 메인 쿼리에 서브쿼리 연결하기 */
        // WHERE salary >= (서브쿼리)
        criteriaQuery.where(builder.ge(emp.<Double>get("salary"), subQuery));

        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        query.getResultList().forEach(employee -> logger.info("---> {}", employee));
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
