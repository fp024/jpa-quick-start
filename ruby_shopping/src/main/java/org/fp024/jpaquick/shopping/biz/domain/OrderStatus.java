package org.fp024.jpaquick.shopping.biz.domain;

public enum OrderStatus {
    // '주문' 상태
    ORDER("주문"),
    // '주문 취소' 상태
    CANCEL("취소");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
