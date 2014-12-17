package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;


public class Sample {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Environment environment;

    public void readDataBase() throws Exception {
        jdbcTemplate.query("select * from customers", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                System.out.println(rowNum);
                return null;
            }
        });
        // resultSet gets the result of the SQL query
//        resultSet = handler.executeReadQuery();
//        System.out.println(resultSet.getFetchSize());
//
//        resultSet = handler.executeReadQuery("select * from orders");
//        System.out.println(resultSet.getFetchSize());
//
//        resultSet = handler.executeReadQuery("select * from products");
//        System.out.println(resultSet.getFetchSize());
//
//        resultSet = handler.executeReadQuery("select * from order_product");
//        System.out.println(resultSet.getFetchSize());
    }

    public static void main(String[] args) throws Exception {
        new Sample().readDataBase();
    }
}