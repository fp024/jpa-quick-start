package org.fp024.jpaquick.persistence.jdbc;

import static org.fp024.jpaquick.persistence.jdbc.EmployeeVODynamicSqlSupport.deptName;
import static org.fp024.jpaquick.persistence.jdbc.EmployeeVODynamicSqlSupport.employeeVO;
import static org.fp024.jpaquick.persistence.jdbc.EmployeeVODynamicSqlSupport.id;
import static org.fp024.jpaquick.persistence.jdbc.EmployeeVODynamicSqlSupport.name;
import static org.fp024.jpaquick.persistence.jdbc.EmployeeVODynamicSqlSupport.salary;
import static org.fp024.jpaquick.persistence.jdbc.EmployeeVODynamicSqlSupport.startDate;
import static org.fp024.jpaquick.persistence.jdbc.EmployeeVODynamicSqlSupport.title;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.annotation.processing.Generated;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface EmployeeDAOMyBatis {

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	BasicColumn[] selectList = BasicColumn.columnList(id, name, startDate, title, deptName, salary);

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	long count(SelectStatementProvider selectStatement);

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	@DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
	int delete(DeleteStatementProvider deleteStatement);

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insert")
	int insert(InsertStatementProvider<EmployeeVO> insertStatement);

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	@InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
	int insertMultiple(MultiRowInsertStatementProvider<EmployeeVO> multipleInsertStatement);

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("EmployeeVOResult")
	Optional<EmployeeVO> selectOne(SelectStatementProvider selectStatement);

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@Results(id = "EmployeeVOResult", value = {
			@Result(column = "id", property = "id", jdbcType = JdbcType.DOUBLE, id = true),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "start_date", property = "startDate", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
			@Result(column = "dept_name", property = "deptName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "salary", property = "salary", jdbcType = JdbcType.DOUBLE) })
	List<EmployeeVO> selectMany(SelectStatementProvider selectStatement);

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	@UpdateProvider(type = SqlProviderAdapter.class, method = "update")
	int update(UpdateStatementProvider updateStatement);

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	default long count(CountDSLCompleter completer) {
		return MyBatis3Utils.countFrom(this::count, employeeVO, completer);
	}

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	default int delete(DeleteDSLCompleter completer) {
		return MyBatis3Utils.deleteFrom(this::delete, employeeVO, completer);
	}

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	default int deleteByPrimaryKey(Long id_) {
		return delete(c -> c.where(id, isEqualTo(id_)));
	}

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	default int insert(EmployeeVO record) {
		return MyBatis3Utils.insert(this::insert, record, employeeVO,
				c -> c.map(id).toProperty("id").map(name).toProperty("name").map(startDate).toProperty("startDate")
						.map(title).toProperty("title").map(deptName).toProperty("deptName").map(salary)
						.toProperty("salary"));
	}

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	default int insertMultiple(Collection<EmployeeVO> records) {
		return MyBatis3Utils.insertMultiple(this::insertMultiple, records, employeeVO,
				c -> c.map(id).toProperty("id").map(name).toProperty("name").map(startDate).toProperty("startDate")
						.map(title).toProperty("title").map(deptName).toProperty("deptName").map(salary)
						.toProperty("salary"));
	}

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	default int insertSelective(EmployeeVO record) {
		return MyBatis3Utils.insert(this::insert, record, employeeVO,
				c -> c.map(id).toPropertyWhenPresent("id", record::getId).map(name)
						.toPropertyWhenPresent("name", record::getName).map(startDate)
						.toPropertyWhenPresent("startDate", record::getStartDate).map(title)
						.toPropertyWhenPresent("title", record::getTitle).map(deptName)
						.toPropertyWhenPresent("deptName", record::getDeptName).map(salary)
						.toPropertyWhenPresent("salary", record::getSalary));
	}

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	default Optional<EmployeeVO> selectOne(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectOne(this::selectOne, selectList, employeeVO, completer);
	}

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	default List<EmployeeVO> select(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectList(this::selectMany, selectList, employeeVO, completer);
	}

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	default List<EmployeeVO> selectDistinct(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectDistinct(this::selectMany, selectList, employeeVO, completer);
	}

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	default Optional<EmployeeVO> selectByPrimaryKey(Long id_) {
		return selectOne(c -> c.where(id, isEqualTo(id_)));
	}

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	default int update(UpdateDSLCompleter completer) {
		return MyBatis3Utils.update(this::update, employeeVO, completer);
	}

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	static UpdateDSL<UpdateModel> updateAllColumns(EmployeeVO record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(id).equalTo(record::getId).set(name).equalTo(record::getName).set(startDate)
				.equalTo(record::getStartDate).set(title).equalTo(record::getTitle).set(deptName)
				.equalTo(record::getDeptName).set(salary).equalTo(record::getSalary);
	}

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	static UpdateDSL<UpdateModel> updateSelectiveColumns(EmployeeVO record, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(id).equalToWhenPresent(record::getId).set(name).equalToWhenPresent(record::getName)
				.set(startDate).equalToWhenPresent(record::getStartDate).set(title).equalToWhenPresent(record::getTitle)
				.set(deptName).equalToWhenPresent(record::getDeptName).set(salary)
				.equalToWhenPresent(record::getSalary);
	}

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	default int updateByPrimaryKey(EmployeeVO record) {
		return update(c -> c.set(name).equalTo(record::getName).set(startDate).equalTo(record::getStartDate).set(title)
				.equalTo(record::getTitle).set(deptName).equalTo(record::getDeptName).set(salary)
				.equalTo(record::getSalary).where(id, isEqualTo(record::getId)));
	}

	@Generated("org.mybatis.generator.api.MyBatisGenerator")
	default int updateByPrimaryKeySelective(EmployeeVO record) {
		return update(c -> c.set(name).equalToWhenPresent(record::getName).set(startDate)
				.equalToWhenPresent(record::getStartDate).set(title).equalToWhenPresent(record::getTitle).set(deptName)
				.equalToWhenPresent(record::getDeptName).set(salary).equalToWhenPresent(record::getSalary)
				.where(id, isEqualTo(record::getId)));
	}

	/**
	 * myBatis DSL로 어떻게 표현할지 모르겠음. ㅠㅠ... 일단 쿼리 넣어서 돌려봄.
	 */
	@Insert({ "INSERT INTO s_emp (id, name, start_date, title, dept_name, salary) ",
			"VALUES ((SELECT IFNULL(MAX(id), 0) + 1 FROM s_emp AS temp), #{name}, #{startDate}, #{title}, #{deptName}, #{salary})" })
	void insertQuery(EmployeeVO vo);

}