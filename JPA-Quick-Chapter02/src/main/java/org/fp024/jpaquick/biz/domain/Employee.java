package org.fp024.jpaquick.biz.domain;

import java.time.LocalDate;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = { "searchCondition", "searchKeyword" })
@Entity
@Table(name = "s_emp", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "mailId" }) })
@Access(AccessType.PROPERTY)
public class Employee {
	private Long id;

	private String name;

	private String mailId;

	private LocalDate startDate;

	private String title;

	private String deptName;

	private Double salary;

	private Double commissionPct;

	private String searchCondition;

	private String searchKeyword;

	@Id
	@Column(length = 7, nullable = false)
	public Long getId() {
		return id;
	}

	@Column(length = 25, nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * MySQL에 아무설정도 안하여, 컬럼명이 mailId로 그대로 저장은 되지만 조회시 대소문자구분없이 조회를 할 수는 있다. 명시적으로
	 * 지정을 한다.
	 * 
	 * 자동으로 스네이크케이스로 변환해서 저장하려면
	 * https://thorben-janssen.com/naming-strategies-in-hibernate-5/
	 */
	@Column(length = 8, unique = true)
	public String getMailId() {
		return mailId;
	}

	/**
	 * hibernate-core 5.2.10 이상부터는 LocalDateTime을 직접 사용할 수 있다고 함. LocalDateTime 이나
	 * LocalDate에는 @Temporal 을 사용할 수 없음. 알맞은 타입을 직접 맞춰서 써야하는 거 같다.
	 * 
	 * LocalDate -> MySQL DATE (시간정보 저장하지 않는 컬럼타입) LocalDateTime -> MySQL DATETIME
	 * (시간정보 포함 컬럼타입)
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	@Column(length = 25)
	public String getTitle() {
		return title;
	}

	@Column(length = 30)
	public String getDeptName() {
		return deptName;
	}

	@Column(precision = 11, scale = 2)
	public Double getSalary() {
		return salary;
	}

	/** 성과급 지급률, MySQL 8에서 CHECK 제약조건 설정이 사용가능함. */
	@Column(precision = 4, scale = 2, columnDefinition = "DOUBLE CHECK (commission_pct IN (10, 12.5, 15, 17.5, 20))")
	public Double getCommissionPct() {
		return commissionPct;
	}

	@Transient
	public String getSearchCondition() {
		return searchCondition;
	}

	@Transient
	public String getSearchKeyword() {
		return searchKeyword;
	}

}
