package com.mritunjd.order_management.config;

import com.mysql.jdbc.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource basicDataSource = new SimpleDriverDataSource();
        basicDataSource.setUrl(environment.getProperty("DATABASE_URL"));
        basicDataSource.setUsername(environment.getProperty("DATABASE_USER"));
        basicDataSource.setPassword(environment.getProperty("DATABASE_PASSWORD"));
        basicDataSource.setDriverClass(Driver.class);
        return basicDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
