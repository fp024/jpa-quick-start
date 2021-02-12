package org.fp024.jpaquick.biz.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "s_item")
public class Item {
    /**
     * 주문 내역 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 주문 참조
     */
    @ManyToOne
    @JoinColumn(name = "ord_id")
    private Order order;

    /**
     * 상품 참조
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 주문 가격
     */
    private Long price;

    /**
     * 주문 수량
     */
    private Long quantity;

    /**
     * 주문(Order)과의 양뱡항 참조 설정
     */
    public void setOrder(Order order) {
        this.order = order;
        order.getItemList().add(this);
    }
}
