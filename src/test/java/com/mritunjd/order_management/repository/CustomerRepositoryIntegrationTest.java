package com.mritunjd.order_management.repository;

import com.mritunjd.order_management.config.DatabaseConfig;
import com.mritunjd.order_management.model.Customer;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/test-om.properties")
@ContextConfiguration(classes = {DatabaseConfig.class, TestConfig.class})
@WebAppConfiguration
public class CustomerRepositoryIntegrationTest {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @After
    public void tearDown() {
        jdbcTemplate.update("delete from customers");
    }


    @Test
    public void shouldGiveACustomer() throws SQLException {
        String setUpSql = "insert into customers SELECT * FROM CSVREAD('classpath:/csvs/customers.csv') ";
        jdbcTemplate.execute(setUpSql);

        List<Customer> customers = repository.getCustomerById("1");
        System.out.println(customers);
        assertEquals(1,customers.size());
    }

    @Test
    public void shouldInsertACustomer() {
//        String setUpSql = "create table customers as SELECT * FROM CSVREAD('classpath:/csvs/customers.csv') ";
//        jdbcTemplate.execute(setUpSql);
//
//        String sql = "select * from customers";

    }

}
