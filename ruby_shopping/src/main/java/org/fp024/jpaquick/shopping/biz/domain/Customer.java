package org.fp024.jpaquick.shopping.biz.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(exclude = "orderList")
@Entity
@Table(name = "s_customer")
public class Customer {
    // 회원 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    // 회원 이름
    private String name;

    // 회원 전화번호
    private String phone;

    // 회원 특징 설명
    private String comments;

    // 회원 주소
    @Embedded
    private Address address;

    // 주문 목록
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private final List<Order> orderList = new ArrayList<>();

    // 뷰에서 address.필드명 형식으로 form 정보를 받으려면, 해당 도메인의 Setter가 설정되야한다.
    public void setAddress(Address address) {
          this.address = address;
    }
}
