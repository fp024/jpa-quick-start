package org.fp024.jpaquick.shopping.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.fp024.jpaquick.shopping.common.hibernate.CustomPhysicalNamingStrategy;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@ComponentScan(basePackages = "org.fp024.jpaquick.shopping", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class, ControllerAdvice.class})
})
@PropertySource({"classpath:database.properties"})
@EnableTransactionManagement // <tx:annotation-driven />
public class SpringConfiguration {
    @Bean
    public HibernateJpaVendorAdapter vendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public DataSource dataSource(Environment environment) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(environment.getProperty("jdbc.driver"));
        hikariConfig.setJdbcUrl(environment.getProperty("jdbc.url"));
        hikariConfig.setUsername(environment.getProperty("jdbc.user"));
        hikariConfig.setPassword(environment.getProperty("jdbc.password"));
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(vendorAdapter());
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("org.fp024.jpaquick.shopping.biz.domain");
        factoryBean.setPersistenceUnitName("RubyShopping");

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.id.new_generator_mappings", true);
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.physical_naming_strategy", CustomPhysicalNamingStrategy.class.getCanonicalName());
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", false);

        // LAZY 로드시 트랜젝션 없어도 오류가 안나게 하는데... 안티패턴이라고 한다.
        // https://suhwan.dev/2019/10/27/hibernate-detached-entity-proxy-initialization/
        // https://vladmihalcea.com/the-hibernate-enable_lazy_load_no_trans-anti-pattern/
        properties.put("hibernate.enable_lazy_load_no_trans", true);
        factoryBean.setJpaPropertyMap(properties);

        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
