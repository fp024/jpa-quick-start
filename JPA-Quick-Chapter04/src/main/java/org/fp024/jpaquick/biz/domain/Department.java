package org.fp024.jpaquick.biz.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"employeeList"})
@EqualsAndHashCode(exclude = {"employeeList"})
@Entity
@Table(name="s_dept")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    @Column(length = 25, nullable = false)
    private String name;

    @OneToMany(mappedBy = "dept", fetch = FetchType.EAGER)
    private Set<Employee> employeeList = new HashSet<>();
}