package org.fp024.jpaquick.shopping.biz.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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

}
