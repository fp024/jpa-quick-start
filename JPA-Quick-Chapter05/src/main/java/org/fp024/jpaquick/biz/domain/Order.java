package org.fp024.jpaquick.biz.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "s_ord")
public class Order {
    /**
     * 주문 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 고객 ID
     */
    private Long customerId;

    /**
     * 주문 날짜
     */
    private LocalDateTime orderDate;

    /**
     * 주문 금액
     */
    private Double total;

    /**
     * 주문 내역 - 연결 테이블을 별도 클래스 없이 생성
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "s_item"
            , joinColumns = @JoinColumn(name = "ord_id")
            , inverseJoinColumns = @JoinColumn(name = "product_id")
            , uniqueConstraints = @UniqueConstraint(columnNames = {"ord_id", "product_id"})
    )
    private List<Product> productList = new ArrayList<>();
}
