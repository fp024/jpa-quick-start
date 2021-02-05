package org.fp024.jpaquick.persistence.jdbc;

import java.util.Date;

import javax.annotation.processing.Generated;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeVO {
	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	private Long id;
	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	private String name;
	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	private Date startDate;
	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	private String title;
	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	private String deptName;
	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	private Double salary;
}
