package com.mritunjd.order_management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DatabaseConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String database_url = environment.getProperty("DATABASE_URL");
        String database_user = environment.getProperty("DATABASE_USER");
        String database_password = environment.getProperty("DATABASE_PASSWORD");
        Driver driver=(Driver)Class.forName(environment.getProperty("DATABASE_DRIVER")).newInstance();

        SimpleDriverDataSource dataSource=new SimpleDriverDataSource(driver,database_url,database_user,database_password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return new DataSourceTransactionManager(dataSource());
    }
}
