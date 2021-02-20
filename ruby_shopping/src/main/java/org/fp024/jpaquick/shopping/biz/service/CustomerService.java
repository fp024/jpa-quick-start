package org.fp024.jpaquick.shopping.biz.service;

import org.fp024.jpaquick.shopping.biz.domain.Customer;
import org.fp024.jpaquick.shopping.biz.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // 회원 등록
    public void insertCustomer(Customer customer) {
        customerRepository.insertCustomer(customer);
    }

    // 회원 상세 조회
    public Customer getCustomer(Long customerId) {
        return customerRepository.getCustomer(customerId);
    }

    // 회원 목록 검색
    public List<Customer> getCustomerList() {
        return customerRepository.getCustomerList();
    }
}
