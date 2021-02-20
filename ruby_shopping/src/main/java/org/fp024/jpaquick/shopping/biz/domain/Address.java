package org.fp024.jpaquick.shopping.biz.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {
    // 도시
    private String city;

    // 도로명
    private String roadName;

    // 우편번호
    private String zipCode;

    // 회원 주소
    @Embedded
    private Address address;
}
