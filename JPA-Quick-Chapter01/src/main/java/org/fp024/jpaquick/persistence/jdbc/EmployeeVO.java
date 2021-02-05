package org.fp024.jpaquick.persistence.jdbc;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeVO {
	/** 직원 아이디 */
	private Long id;

	/** 직원 이름 */
	private String name;

	/** 입사일 */
	private Date startDate;

	/** 직급 */
	private String title;

	/** 부서 이름 */
	private String deptName;

	/** 급여 */
	private Double salary;

}
