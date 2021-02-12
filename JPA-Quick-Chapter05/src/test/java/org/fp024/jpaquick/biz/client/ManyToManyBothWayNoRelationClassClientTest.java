package org.fp024.jpaquick.biz.client;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Order;
import org.fp024.jpaquick.biz.domain.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

/**
 * 연결 클래스가 없는 다대다 단방향 테스트
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ManyToManyBothWayNoRelationClassClientTest {
    private static EntityManagerFactory emf;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("Chapter05");
    }

    @AfterAll
    static void afterAll() {
        if (emf != null) {
            emf.close();
        }
    }


    @org.junit.jupiter.api.Order(1)
    @Test
    void dataInsert() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // 1번 상품 등록
        Product product1 = new Product();
        product1.setName("LG 통돌이 세탁기");
        em.persist(product1);

        // 2번 상품 등록
        Product product2 = new Product();
        product2.setName("다이슨 청소기");
        em.persist(product2);

        // 1번 주문등록
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());

        // 주문 객체가 가진 상품 목록 (productList) 에 상품 저장
       // order.getProductList().add(product1);
       // order.getProductList().add(product2);
        em.persist(order);

        em.getTransaction().commit();
        em.close();
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    void dataSelect() {
        EntityManager em = emf.createEntityManager();

        // 검색한 Order 를 통해 Product 목록을 출력한다.
        Order order = em.find(Order.class, 1L);
        logger.info("{}번 주문에 대한 상품 목록", order.getId());
        //order.getProductList().forEach(p -> logger.info("--->{}", p.getName()));

        // 검색한 Product를 통해 Order 목록을 출력한다.
        Product product = em.find(Product.class, 1L);

        logger.info("{} 상품에 대한 주문 정보", product.getName());
        //product.getOrderList().forEach(p -> logger.info("--->{}", p));

        em.close();
    }
}
