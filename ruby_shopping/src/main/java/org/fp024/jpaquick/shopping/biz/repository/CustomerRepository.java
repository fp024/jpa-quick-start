package org.fp024.jpaquick.shopping.biz.repository;

import org.fp024.jpaquick.shopping.biz.domain.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    // 회원 등록
    public void insertCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    // 회원 상세 조회
    public Customer getCustomer(Long id) {
        return entityManager.find(Customer.class, id);
    }

    // 회원 목록 검색
    public List<Customer> getCustomerList() {
        return entityManager.createQuery("SELECT c FROM Customer c ORDER BY c.id", Customer.class).getResultList();
    }

}
