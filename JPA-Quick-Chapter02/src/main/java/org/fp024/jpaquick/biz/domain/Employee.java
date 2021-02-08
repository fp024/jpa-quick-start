package org.fp024.jpaquick.biz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
// @ToString(exclude = { "searchCondition", "searchKeyword" })
@ToString
@Entity
//@Table(name = "s_emp", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "mailId" }) })
@Table(name = "s_emp")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 7, nullable = false)
	private Long id;

	@Column(length = 25, nullable = false)
	private String name;

//	/**
//	 * MySQL에 아무설정도 안하여, 컬럼명이 mailId로 그대로 저장은 되지만 조회시 대소문자구분없이 조회를 할 수는 있다. 명시적으로
//	 * 지정을 한다.
//	 * 
//	 * 자동으로 스네이크케이스로 변환해서 저장하려면
//	 * https://thorben-janssen.com/naming-strategies-in-hibernate-5/
//	 */
//	@Column(length = 8, unique = true)
//	private String mailId;
//
//	/**
//	 * hibernate-core 5.2.10 이상부터는 LocalDateTime을 직접 사용할 수 있다고 함. LocalDateTime 이나
//	 * LocalDate에는 @Temporal 을 사용할 수 없음. 알맞은 타입을 직접 맞춰서 써야하는 거 같다.
//	 * 
//	 * LocalDate -> MySQL DATE (시간정보 저장하지 않는 컬럼타입) LocalDateTime -> MySQL DATETIME
//	 * (시간정보 포함 컬럼타입)
//	 */
//	private LocalDate startDate;
//
//	@Column(length = 25)
//	private String title;
//
//	@Column(length = 30)
//	private String deptName;
//
//	@Column(precision = 11, scale = 2)
//	private Double salary;
//
//	/** 성과급 지급률, MySQL 8에서 CHECK 제약조건 설정이 사용가능함. */
//	@Column(precision = 4, scale = 2, columnDefinition = "DOUBLE CHECK (commission_pct IN (10, 12.5, 15, 17.5, 20))")
//	private Double commissionPct;
//
//	@Transient
//	private String searchCondition;
//
//	@Transient
//	private String searchKeyword;

}
