package com.mritunjd.order_management.service;

import com.mritunjd.order_management.model.Customer;
import com.mritunjd.order_management.repository.CustomerRepository;
import com.mritunjd.order_management.util.DBConnection;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {
    private CustomerRepository customerRepository;

    public Customer getCustomerById(String id) {
        return null;
    }
}
