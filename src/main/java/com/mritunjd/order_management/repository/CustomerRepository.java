package com.mritunjd.order_management.repository;

import com.mritunjd.order_management.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> getCustomerById(String customerID) throws SQLException {
        System.out.println("** Repository **");
        System.out.println(customerID);
        String readQuery = "select customer_id,customer_name,address from customers where customer_id=?";
        List<Customer> customers = jdbcTemplate.query(readQuery, new Object[]{customerID}, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                System.out.println("** yha to aa rha h **");
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String address = rs.getString(3);
                return new Customer(id, name, address);
            }
        });
        return customers;
    }
}
