package org.fp024.jpaquick.biz.domain;

import lombok.*;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    private String name;

    @OneToMany(mappedBy = "dept", fetch = FetchType.EAGER)
    private final List<Employee> employeeList = new ArrayList<>();

}
