package org.fp024.jpaquick.shopping.biz.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Entity
@Table(name = "s_product")
public class Product {
    // 상품 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    // 상품 이름
    private String name;

    // 상품 가격
    private int price;

    // 상품 수량
    private int quantity;

    // 주문 정보 생성시에 재고 수량을 감소 시킨다.
    void reduceStock(int quantity) {
        this.quantity = this.quantity - quantity;

        // 재고 수량이 부족하면 예외 발생시킴
        if (quantity < 0) {
            throw new IllegalStateException("재고가 부족합니다.");
        }
    }

    // 주문 취소 시에 재고 수량을 원래 대로 되돌린다.
    void restoreStock(int quantity) {
        this.quantity = this.quantity + quantity;
    }

}
