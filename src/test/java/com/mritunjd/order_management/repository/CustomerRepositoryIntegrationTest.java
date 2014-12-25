package com.mritunjd.order_management.repository;

import com.mritunjd.order_management.config.DatabaseConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.TestPropertySource;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/test-om.properties")
@ContextConfiguration(classes = {DatabaseConfig.class, TestConfig.class})

public class CustomerRepositoryIntegrationTest {
    @Test
    public void shouldGiveACustomer() {

    }
}
