package org.fp024.jpaquick.shopping;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.shopping.biz.domain.Address;
import org.fp024.jpaquick.shopping.biz.domain.Customer;
import org.fp024.jpaquick.shopping.biz.domain.Order;
import org.fp024.jpaquick.shopping.biz.domain.Product;
import org.fp024.jpaquick.shopping.biz.service.CustomerService;
import org.fp024.jpaquick.shopping.biz.service.OrderService;
import org.fp024.jpaquick.shopping.biz.service.ProductService;
import org.fp024.jpaquick.shopping.config.SpringConfiguration;
import org.junit.jupiter.api.MethodOrderer;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class RubyShoppingClientTest {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @org.junit.jupiter.api.Order(1)
    @Test
    void insertData() {
        // 회원정보 생성 및 등록
        Customer customer = Customer.builder()
                .name("홍길동")
                .address(Address.builder().city("서울시").roadName("행당로 82").zipCode("123-123").build())
                .phone("011-1234-5678")
                .comments("반품 요청이 많은 회원임")
                .build();

        customerService.insertCustomer(customer);


        // 두개의 상품 정보 등록
        Product product1 = Product.builder()
                .name("JPA 처음 가보는 길")
                .price(20000)
                .quantity(10)
                .build();

        productService.insertOrUpdateProduct(product1);

        Product product2 = Product.builder()
                .name("자바 프로그래밍 기초")
                .price(40000)
                .quantity(20)
                .build();

        productService.insertOrUpdateProduct(product2);

        // 주문정보 생성
        orderService.insertOrder(customer.getId(), product1.getId(), 5);
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    void selectData() {
        Customer customer = customerService.getCustomer(1L);
        logger.info(customer.toString());

        List<Order> orderList = customer.getOrderList();
        orderList.forEach(order -> logger.info("\t{}", order));
    }

}
