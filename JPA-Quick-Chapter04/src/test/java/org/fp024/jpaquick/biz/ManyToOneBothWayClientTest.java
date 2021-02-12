package org.fp024.jpaquick.biz;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Department;
import org.fp024.jpaquick.biz.domain.Employee;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ManyToOneBothWayClientTest {
    private static EntityManagerFactory emf;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("Chapter04");
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
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // 부서등록
        Department department = new Department();
        department.setName("개발부");
        // em.persist(department);

        // 직원등록
        Employee employee1 = new Employee();
        employee1.setName("탈보가이");
        employee1.setDept(department);
        em.persist(employee1);

        // 직원등록
        Employee employee2 = new Employee();
        employee2.setName("다크나이트");
        employee2.setDept(department);
        em.persist(employee2);

        logger.info("{}의 직원 수: {}", department.getName(), department.getEmployeeList().size());

        tx.commit();
        em.close();
    }

    @Order(2)
    @Test
    void dataSelect() {
        EntityManager em = emf.createEntityManager();
        Department department = em.find(Department.class, 1L);

        logger.info("검색된 부서 : {}", department.getName());
        logger.info("부서에 소속된 직원 명단");
        department.getEmployeeList().forEach(employee ->
                logger.info("{} ({})", employee.getName(), employee.getDept().getName())
        );
    }
}
