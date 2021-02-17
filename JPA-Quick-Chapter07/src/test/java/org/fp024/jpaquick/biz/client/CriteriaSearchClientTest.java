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


    static Stream<Arguments> conditionAndKeyword() {
        return Stream.of(
                arguments("mailId", "rona"), // mailId 에 'rona' 가 포함된 직원 조회
                arguments("name", "개발")    // name 에 '개발' 이 포함된 직원 조회
        );
    }

    /**
     * JUnit 파라미터 테스트 관련내용은 코드를 변경하지 말고 남겨둔다.
     */
    @org.junit.jupiter.api.Order(2)
    @ParameterizedTest
    @Disabled
    @MethodSource("conditionAndKeyword")
    void dataSelect_ParameterizedTest(String searchCondition, String searchKeyword) {
        // 사용자가 입력한 검색조건과 검색 단어를 이용하면 좋으나, 단위테스트 상에서는 사용이 안된다.
        // JUnit 5에서 제공하는 파라미터를 넘겨서 테스트 하는 식으로 진행한다.
//        Scanner keyboard = new Scanner(System.in);
//        System.out.print("검색 조건을 입력하세요.: name 혹은 mailId");
//        String searchCondition = keyboard.nextLine();
//        System.out.println("검색어를 입력하세요.");
//        String searchKeyword = keyboard.nextLine();


        // 크라이테리어 빌더 생성
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);

        // FROM Employee emp
        Root<Employee> emp = criteriaQuery.from(Employee.class);

        // SELECT emp
        criteriaQuery.select(emp);

        // JOIN FETCH emp.dept dept
        emp.fetch("dept");


        switch (searchCondition) {
            case "mailId":
            case "name":
                // WHERE emp.mailId LIKE %searchKeyword%
                // WHERE emp.name LIKE %searchKeyword%
                criteriaQuery.where(builder.like(emp.get(searchCondition), "%" + searchKeyword + "%"));
                break;
            default:
                fail("mailId 와 name 만 입력 가능합니다.");
        }

        TypedQuery<Employee> query = em.createQuery(criteriaQuery);

        if (query.getResultList().isEmpty()) {
            logger.info("검색결과가 없습니다.");
        } else {
            query.getResultList().forEach(employee -> logger.info("---> {}", employee));
        }
    }


    @org.junit.jupiter.api.Order(2)
    @Test
    void dataSelect() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = builder.createQuery(Department.class);

        // FROM Department dept
        Root<Department> dept = criteriaQuery.from(Department.class);

        // SELECT dept
        criteriaQuery.select(dept).distinct(true);

        // JOIN FETCH
        dept.fetch("employeeList");

        // WHERE employeeList.size > 2
        criteriaQuery.where(builder.ge(builder.size(dept.<List<Employee>>get("employeeList")), 3));

        TypedQuery<Department> resultList = em.createQuery(criteriaQuery);
        resultList.getResultList().forEach(row -> logger.info("---> {}", row));
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
