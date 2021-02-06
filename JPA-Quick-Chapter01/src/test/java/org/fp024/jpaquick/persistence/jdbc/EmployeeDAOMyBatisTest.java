package org.fp024.jpaquick.persistence.jdbc;

import static org.fp024.jpaquick.persistence.jdbc.EmployeeVODynamicSqlSupport.deptName;
import static org.fp024.jpaquick.persistence.jdbc.EmployeeVODynamicSqlSupport.employeeVO;
import static org.fp024.jpaquick.persistence.jdbc.EmployeeVODynamicSqlSupport.id;
import static org.fp024.jpaquick.persistence.jdbc.EmployeeVODynamicSqlSupport.name;
import static org.fp024.jpaquick.persistence.jdbc.EmployeeVODynamicSqlSupport.salary;
import static org.fp024.jpaquick.persistence.jdbc.EmployeeVODynamicSqlSupport.startDate;
import static org.fp024.jpaquick.persistence.jdbc.EmployeeVODynamicSqlSupport.title;
import static org.junit.Assert.assertTrue;
import static org.mybatis.dynamic.sql.SqlBuilder.deleteFrom;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * MyBatis동적 SQL 라이브러리 테스트
 * 
 * https://mybatis.org/mybatis-dynamic-sql/docs/quickStart.html
 * https://mybatis.org/mybatis-dynamic-sql/docs/insert.html
 * https://mybatis.org/mybatis-dynamic-sql/docs/delete.html
 * 
 * sqlSession 을 commit하지 않고 닫으면 DB에 반영되지 않는다.
 * 
 * @author fp024
 */
@Slf4j
@RunWith(JUnit4.class)
public class EmployeeDAOMyBatisTest {

	private SqlSession sqlSession;

	private EmployeeDAOMyBatis targetMapper;

	@Before
	public void before() throws IOException {
		Reader reader = Resources.getResourceAsReader("sql-map-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		sqlSession = sessionFactory.openSession();
		targetMapper = sqlSession.getMapper(EmployeeDAOMyBatis.class);
	}

	@Test
	public void testSelectByPrimaryKey() {
		Optional<EmployeeVO> result = targetMapper.selectByPrimaryKey(1L);
		assertTrue(result.isPresent());
		logger.info("Employee: {}", result.get());
	}

	@Test
	public void testInsertQuery() {
		EmployeeVO vo = new EmployeeVO();
		vo.setName("마이바티스");
		vo.setDeptName("마이바티스 개발부");
		vo.setTitle("시니어");
		vo.setStartDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
		vo.setSalary(9000.99);
		vo.setEmail("google@google.com");

		// 먼저 입력할 사용자 이름으로 삭제
		DeleteStatementProvider deleteStatement = deleteFrom(employeeVO).where(name, isEqualTo(vo.getName())).build()
				.render(RenderingStrategies.MYBATIS3);
		targetMapper.delete(deleteStatement);

		// 입력
		targetMapper.insertQuery(vo);

		// 조회
		SelectStatementProvider completer = select(id, name, startDate, title, deptName, salary).from(employeeVO)
				.where(name, isEqualTo(vo.getName())).build().render(RenderingStrategies.MYBATIS3);
		Optional<EmployeeVO> result = targetMapper.selectOne(completer);

		assertTrue(result.isPresent());
		logger.info("{}", result.get());

	}

	@After
	public void after() {
		if (sqlSession != null) {
			sqlSession.close();
		}
	}

}
