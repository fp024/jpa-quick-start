package org.fp024.jpaquick.shopping.biz.repository;

import org.fp024.jpaquick.shopping.biz.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    // 주문 등록
    public void insertOrder(Order order) {
        entityManager.persist(order);
    }

    // 주문 상세 조회
    public Order getOrder(Long id) {
        return entityManager.find(Order.class, id);
    }

    // 주문 목록 검색
    public List<Order> getOrderList(Order order) {
        // Criteria 를 이용한 동적 쿼리
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = builder.createQuery(Order.class);

        // From Order ord
        Root<Order> ord = criteriaQuery.from(Order.class);

        List<Predicate> criteria = new ArrayList<>();

        if (order.getSearchCustomerName() != null) {
            // AND ord.name LIKE %order.customerName%
            Predicate name = builder.like(ord.get("customer").get("name"), "%" + order.getSearchCustomerName() + "%");
            criteria.add(name);
        }

        if (order.getSearchOrderStatus() != null) {
            // ord.status == order.searchOrderStatus
            Predicate status = builder.equal(ord.get("status"), order.getSearchOrderStatus());
            criteria.add(status);
        }

        // Predicate 배열을 이용하여 동적 WHERE 절 생성
        criteriaQuery.where(builder.and(criteria.toArray(new Predicate[0])));

        // SELECT 전송
        TypedQuery<Order> query = entityManager.createQuery(criteriaQuery).setMaxResults(1000);
        return query.getResultList();
    }
}
