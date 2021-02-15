package org.fp024.jpaquick.biz.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "employeeList")
@Entity
@Table(name = "s_dept")
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    private String name;

    @OrderColumn(name = "emp_idx")
    @OneToMany(mappedBy = "dept", cascade = CascadeType.PERSIST)
    private final List<Employee> employeeList = new ArrayList<>();
}
