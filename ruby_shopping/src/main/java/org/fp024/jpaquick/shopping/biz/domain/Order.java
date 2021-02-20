package org.fp024.jpaquick.shopping.biz.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {})
@Entity
@Table(name = "s_order")
public class Order {
    // 주문 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    // 회원 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // 주문 상태
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 주문 날짜
    private LocalDateTime orderDate;

    /**
     * 검색 관련 정보
     */
    // 검색할 회원 이름
    @Transient
    private String searchCustomerName;

    // 검색할 주문 상태
    @Transient
    private OrderStatus searchOrderStatus;

    // 주문 생성자 : 주문한 회원정보를 설정한다.
    protected Order(Customer customer) {
        setCustomer(customer);
        this.status = OrderStatus.ORDER;
        this.orderDate = LocalDateTime.now();
    }

    private void setCustomer(Customer customer) {
        customer.getOrderList().add(this);
        this.customer = customer;
    }
}
