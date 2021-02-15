package org.fp024.jpaquick.biz.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name = "s_emp")
/*
@NamedNativeQueries({
        @NamedNativeQuery(name = "Employee.searchDeptId",
                query = "SELECT e.ID, e.NAME AS ename, e.SALARY, d.NAME AS dname " +
                        "  FROM S_EMP e INNER JOIN S_DEPT d " +
                        "    ON e.DEPT_ID = d.DEPT_ID " +
                        " WHERE e.DEPT_ID = :deptId ")
})
 */
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

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department dept;

}
