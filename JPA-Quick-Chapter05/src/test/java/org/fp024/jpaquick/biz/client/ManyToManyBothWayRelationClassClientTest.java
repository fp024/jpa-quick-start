package org.fp024.jpaquick.biz.client;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.biz.domain.Item;
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
 * 연결 클래스로 다대다 단방향 테스트
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ManyToManyBothWayRelationClassClientTest {
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

        // 상품 등록
        Product product1 = new Product();
        product1.setName("LG 통돌이 세탁기");
        em.persist(product1);

        Product product2 = new Product();
        product2.setName("다이슨 청소기");
        em.persist(product2);


        // 주문등록
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        em.persist(order);

        // 카트 등록
        Item item1 = new Item();
        item1.setOrder(order);
        item1.setProduct(product1);
        item1.setPrice(100000L);
        item1.setQuantity(2L);
        em.persist(item1);

        Item item2 = new Item();
        item2.setOrder(order);
        item2.setProduct(product2);
        item2.setPrice(270000L);
        item2.setQuantity(3L);
        em.persist(item2);

        em.getTransaction().commit();
        em.close();
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    void dataSelect() {
        EntityManager em = emf.createEntityManager();

        Order order = em.find(Order.class, 1L);
        logger.info("주문 날짜: {}", order.getOrderDate());
        logger.info("[주문 목록]");
        order.getItemList().forEach(i -> logger.info("--->{}", i.getProduct().getName()));


        Product product = em.find(Product.class, 1L);
        product.getItemList().forEach(i -> logger.info("--->{}", i.getOrder()));

        em.close();
    }
}
