package org.fp024.jpaquick.biz;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Department;
import org.fp024.jpaquick.biz.domain.Employee;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
public class ManyToOneOneWayClientTest {
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
        em.persist(department);

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
        tx.commit();

        em.close();
    }

    @Order(2)
    @Disabled
    @Test
    void dataSelect() {
        EntityManager em = emf.createEntityManager();
        Employee result = em.find(Employee.class, 2L);
//      logger.info("{} 직원이 검색됨 : {}", result.getName());
        logger.info("{}의 부서: {}", result.getName(), result.getDept().getName());
    }

    @Order(3)
    @Disabled
    @Test
    void dataUpdate() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // 신규 부서 등록
        Department department = new Department();
        department.setName("영업부");
        em.persist(department);

        // 부서 변경
        Employee employee = em.find(Employee.class, 1L);
        employee.setDept(department);
        em.persist(department);

        em.getTransaction().commit();
    }

    @Order(3)
    @Test
    void dateDelete() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Employee employee1 = em.find(Employee.class, 1L);
        employee1.setDept(null);

        Employee employee2 = em.find(Employee.class, 2L);
        employee2.setDept(null);

        Department department = em.find(Department.class, 1L);
        em.remove(department);
        em.getTransaction().commit();
    }
}
