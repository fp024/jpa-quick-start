package org.fp024.jpaquick.biz.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문
 */
@Getter
@Setter
@ToString(exclude = "itemList")
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

    @OneToMany(mappedBy = "order")
    private List<Item> itemList = new ArrayList<>();


}
