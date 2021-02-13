package org.fp024.jpaquick.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "s_emp")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String mailId;

    private LocalDateTime startDate;

    private String title;

    private String deptName;

    private Double salary;

    private Double commissionPct;

    /**
     * 빌더 정의 테스트
     * 특정 필드에 대해서만 사용자 정의 동작을 넣어줄 수 있음.
     * https://www.baeldung.com/lombok-builder-custom-setter
     */
    public static class EmployeeBuilder {
        private String name;

        public EmployeeBuilder name(String name) {
            this.name = name + ":custom builder test";
            return this;
        }
    }
}
