package org.fp024.jpaquick.shopping.biz.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter  // 이 도메인이 MVC 컨트롤러의 메개변수로 사용된다면.. 파라미터 값 설정을 위해 Setter가 설정되야한다.
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Embeddable
public class Address {
    // 도시
    private String city;

    // 도로명
    private String roadName;

    // 우편번호
    private String zipCode;
}
