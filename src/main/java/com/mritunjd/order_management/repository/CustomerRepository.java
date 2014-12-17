package com.mritunjd.order_management.repository;

import com.mritunjd.order_management.model.Customer;
import com.mritunjd.order_management.util.DBHandler;
import com.mysql.jdbc.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> getCustomerById(String customerID) throws SQLException {
        System.out.println("** Repository **");
        System.out.println(customerID);
        String readQuery = "select cust_id,cust_name,address from customers where cust_id=?";
        List<Customer> customers = jdbcTemplate.query(readQuery, new Object[]{customerID}, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                System.out.println("** yha to aa rha h **");
                int id = rs.getInt("cust_id");
                String name = rs.getString("cust_name");
                String address = rs.getString("address");
                return new Customer(id, name, address);
            }
        });
        return customers;
//        System.out.println(customers.size());
//        for (Customer customer : customers) {
//            System.out.println(customer);
//        }
//        return Arrays.asList(new Customer(1, "", ""));
    }
}
