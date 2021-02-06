package org.fp024.jpaquick.persistence.jdbc;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "s_emp")
public class EmployeeVO {
	@Id
	private Long id;
	
	private String name;
	
	@Column(name="start_date")
	private Date startDate;
	
	private String title;
	
	@Column(name="dept_name")
	private String deptName;
	
	private Double salary;
	
	private String email;
}
