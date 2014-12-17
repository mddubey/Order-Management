package com.mritunjd.order_management.util;

import com.mysql.jdbc.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource basicDataSource = new SimpleDriverDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/order_management");
        basicDataSource.setUsername("jdbc");
        basicDataSource.setPassword("password");
        basicDataSource.setDriverClass(Driver.class);
        return basicDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
