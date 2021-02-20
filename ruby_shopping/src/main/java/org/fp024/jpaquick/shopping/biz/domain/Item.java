package org.fp024.jpaquick.shopping.biz.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"order", "product"})
@Entity
@Table(name = "s_item")
public class Item {
    // 주문내역 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 주문 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    // 상품 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // 주문 수량
    private int count;

    // 주문 내역 생성자
    public Item(Product product, int count) {
        this.product = product;
        this.count = count;
        // 주문이 생성된 순간 주문 수량만큼 재고를 감소한다.
        reduceStock(count);
    }

    // 주문 발생시에 상품 재고량을 감소시킨다.
    private void reduceStock(int count) {
        product.reduceStock(count);
    }

    // 주문 취소 시에 재고량을 원래대로 되돌린다.
    void restoreStock() {
        product.restoreStock(count);
    }

    void setOrder(Order order) {
        this.order = order;
    }
}
