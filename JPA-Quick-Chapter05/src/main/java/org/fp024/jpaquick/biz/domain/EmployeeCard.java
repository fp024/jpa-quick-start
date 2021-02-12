package org.fp024.jpaquick.biz.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
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
    private Long cardId;

    /**
     * 사원증 만료 기간
     */
    private LocalDate expireDate;

    private String role;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="emp_id")
    private Employee employee;
}
