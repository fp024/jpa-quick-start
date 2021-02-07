package org.fp024.jpaquick.biz.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "s_emp", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "mailId" }) })
public class Employee {
	@Id
	private Long id;

	private String name;

	/**
	 * MySQL에 아무설정도 안하여, 컬럼명이 mailId로 그대로 저장은 되지만 조회시 대소문자구분없이 조회를 할 수는 있다. 명시적으로
	 * 지정을 한다.
	 * 
	 * 자동으로 스네이크케이스로 변환해서 저장하려면
	 * https://thorben-janssen.com/naming-strategies-in-hibernate-5/
	 */
	private String mailId;

	/** hibernate-core 5.2.10 이상부터는 LocalDateTime을 직접 사용할 수 있다고 함. */
	private LocalDateTime startDate;

	private String title;

	private String deptName;

	private Double salary;

	/** 성과급 지급률 */
	private Double commissionPct;
}
