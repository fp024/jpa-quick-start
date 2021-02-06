package org.fp024.jpaquick.persistence.jdbc;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeDAOHibernate {
	private final SessionFactory sessionFactory;

	public EmployeeDAOHibernate() {
		try {
			this.sessionFactory = javaConfig();
			// this.sessionFactory = xmlConfig();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException("초기화 실패");
		}
	}

	// (참고) : Hibernate.cfg.xml에서 설정된 내용을 Class 에서 직접 명시해봤음.
	private SessionFactory javaConfig() throws IOException {
		Properties props = Resources.getResourceAsProperties("database.properties");
		props.setProperty("hibernate.connection.driver_class", props.getProperty("jdbc.driver"));
		props.setProperty("hibernate.connection.url", props.getProperty("jdbc.url"));
		props.setProperty("hibernate.connection.username", props.getProperty("jdbc.username"));
		props.setProperty("hibernate.connection.password", props.getProperty("jdbc.password"));

		Configuration configuration = new Configuration().addProperties(props)
				.addAnnotatedClass(org.fp024.jpaquick.persistence.jdbc.EmployeeVO.class);
		return configuration.buildSessionFactory();
	}
	
	private SessionFactory xmlConfig() {
		String config = "persistence/hibernate.cfg.xml";
		Configuration configuration = new Configuration().configure(config);
		return configuration.buildSessionFactory();
	}
	
	

	public void insertEmployee(EmployeeVO vo) {
		Transaction transaction = null;

		// java.io.Closeable 인터페이스는 java.lang.AutoClosable 을 상속한한다. Session은
		// java.io.Closeable 를 구현한다.
		try (Session session = sessionFactory.openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			session.persist(vo);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public List<EmployeeVO> getEmployeeList() {
		logger.info("===> Hibernate 기반으로 직원 목록 조회 기능 처리");
		String jpql = "SELECT e FROM EmployeeVO e ORDER BY e.id";
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery(jpql, EmployeeVO.class).getResultList();
		}
	}

	/**
	 * 테스트 코드 실행 종료시 명시적으로 sessionFactory를 닫아주기 위해서...
	 */
	public void close() {
		sessionFactory.close();
	}
}
