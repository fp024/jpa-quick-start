package org.fp024.jpaquick.biz.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@ToString(exclude = {"employee"})
@Entity
@Table(name = "s_emp_card")
public class EmployeeCard {
    /**
     * 사원증 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    /**
     * 사원증 만료 기간
     */
    private LocalDate expireDate;

    private String role;

    @OneToOne(mappedBy = "card")
    private Employee employee;
}
