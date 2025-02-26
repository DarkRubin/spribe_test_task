package com.example.spribe_test_task.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class TestDatasourceConfig {

    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>("postgres:latest");
    }

    @Bean
    public DynamicPropertyRegistrar apiPropertiesRegistrar(PostgreSQLContainer<?> container) {
        return registry ->
                registry.add("postgresql.driver", container::getDriverClassName);
    }

}
