package org.fp024.jpaquick.persistence.jdbc;

import java.sql.JDBCType;
import java.util.Date;

import javax.annotation.processing.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EmployeeVODynamicSqlSupport {

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	public static final EmployeeVO employeeVO = new EmployeeVO();
	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	public static final SqlColumn<Long> id = employeeVO.id;
	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	public static final SqlColumn<String> name = employeeVO.name;
	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	public static final SqlColumn<Date> startDate = employeeVO.startDate;
	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	public static final SqlColumn<String> title = employeeVO.title;
	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	public static final SqlColumn<String> deptName = employeeVO.deptName;
	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	public static final SqlColumn<Double> salary = employeeVO.salary;
	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	public static final SqlColumn<String> email = employeeVO.email;

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	public static final class EmployeeVO extends SqlTable {
		public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);
		public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);
		public final SqlColumn<Date> startDate = column("start_date", JDBCType.TIMESTAMP);
		public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);
		public final SqlColumn<String> deptName = column("dept_name", JDBCType.VARCHAR);
		public final SqlColumn<Double> salary = column("salary", JDBCType.DOUBLE);
		public final SqlColumn<String> email = column("email", JDBCType.VARCHAR);

		public EmployeeVO() {
			super("s_emp");
		}
	}
}