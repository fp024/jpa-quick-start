package org.fp024.jpaquick.biz.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "dept")
@Entity
@Table(name = "s_emp")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String mailId;

    private Double salary;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department dept;

}
