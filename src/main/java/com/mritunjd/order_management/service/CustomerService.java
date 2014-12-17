package com.mritunjd.order_management.service;

import com.mritunjd.order_management.model.Customer;
import com.mritunjd.order_management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(String id) {
        Customer customer;
        try {
            System.out.println("** Service **");
            customer = customerRepository.getCustomerById(id).get(0);
            System.out.println("** Service Fulfill **");
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
            customer=null;
        }
        return customer;
    }
}
