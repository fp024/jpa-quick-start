package org.fp024.jpaquick.shopping.biz.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"customer", "searchCustomerName", "searchOrderStatus", "itemList"})
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

    // 주문 내역 목록
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<Item> itemList= new ArrayList<>();

    // 주문 생성자 : 주문한 회원정보를 설정한다.
    public Order(Customer customer, Item item) {
        setCustomer(customer);
        addItem(item);
        this.status = OrderStatus.ORDER;
        this.orderDate = LocalDateTime.now();
    }

    private void setCustomer(Customer customer) {
        customer.getOrderList().add(this);
        this.customer = customer;
    }

    // 주문상품 설정 시에 주문 상품 쪽에도 양뱡향 설정
    private void addItem(Item item) {
        itemList.add(item);
        item.setOrder(this);
    }

    // 주문 취소 처리
    void orderCancel() {
        this.status = OrderStatus.CANCEL;
        itemList.forEach(item -> item.restoreStock());
    }

}
