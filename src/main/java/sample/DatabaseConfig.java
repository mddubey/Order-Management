package sample;

import com.mysql.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

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
