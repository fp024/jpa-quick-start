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
@NamedQueries({
        @NamedQuery(name = "Employee.searchById",
                query = "SELECT e FROM Employee e WHERE e.id = :searchKeyword"),
        @NamedQuery(name = "Employee.searchByName",
                query = "SELECT e FROM Employee e WHERE e.name LIKE :searchName")

})
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
