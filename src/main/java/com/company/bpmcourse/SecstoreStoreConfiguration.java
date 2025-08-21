package com.company.bpmcourse;

import io.jmix.autoconfigure.data.JmixLiquibaseCreator;
import io.jmix.core.JmixModules;
import io.jmix.core.Resources;
import io.jmix.data.impl.JmixEntityManagerFactoryBean;
import io.jmix.data.impl.JmixTransactionManager;
import io.jmix.data.persistence.DbmsSpecifics;
import jakarta.persistence.EntityManagerFactory;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
public class SecstoreStoreConfiguration {

    @Bean
    @ConfigurationProperties("secstore.datasource")
    DataSourceProperties secstoreDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "secstore.datasource.hikari")
    DataSource secstoreDataSource(@Qualifier("secstoreDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean secstoreEntityManagerFactory(
            @Qualifier("secstoreDataSource") DataSource dataSource,
            JpaVendorAdapter jpaVendorAdapter,
            DbmsSpecifics dbmsSpecifics,
            JmixModules jmixModules,
            Resources resources
    ) {
        return new JmixEntityManagerFactoryBean("secstore", dataSource, jpaVendorAdapter, dbmsSpecifics, jmixModules, resources);
    }

    @Bean
    JpaTransactionManager secstoreTransactionManager(@Qualifier("secstoreEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JmixTransactionManager("secstore", entityManagerFactory);
    }

    @Bean("secstoreLiquibaseProperties")
    @ConfigurationProperties(prefix = "secstore.liquibase")
    public LiquibaseProperties secstoreLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean("secstoreLiquibase")
    public SpringLiquibase secstoreLiquibase(@Qualifier("secstoreDataSource") DataSource dataSource,
                                             @Qualifier("secstoreLiquibaseProperties") LiquibaseProperties liquibaseProperties) {
        return JmixLiquibaseCreator.create(dataSource, liquibaseProperties);
    }
}
