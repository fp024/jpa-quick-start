package org.fp024.jpaquick.biz.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "s_emp")
public class Employee {
	@Id
	private EmployeeId empId;

	private String name;

}