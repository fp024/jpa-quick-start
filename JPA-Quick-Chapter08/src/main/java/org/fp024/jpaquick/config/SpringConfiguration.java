package org.fp024.jpaquick.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@ComponentScan(basePackages = "org.fp024.jpaquick.biz")
@PropertySource({"/database.properties"})
/*
    entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager"
    @EnableJpaRepositories 어노테이션이 위의 속성의 빈을 찾는데, 위의 이름대로 빈이름 (메서드명)이 설정되어있으면
    생략해도 된다.
*/
@EnableJpaRepositories(basePackages = "org.fp024.jpaquick.biz.repository")
@EnableTransactionManagement
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
        factoryBean.setPackagesToScan("org.fp024.jpaquick.biz.domain");

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.id.new_generator_mappings", true);
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.physical_naming_strategy", "org.fp024.jpaquick.common.hibernate.CustomPhysicalNamingStrategy");
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", false);
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
