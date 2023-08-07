package com.honeybadgersoftware.cheappy.base

import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.junit.jupiter.Testcontainers
import spock.lang.Specification

@Testcontainers
@SpringBootTest
class BaseIntegrationTest extends Specification{

    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:15.3")
            .withDatabaseName("testDb")
            .withUsername("test")
            .withPassword("test");

}
