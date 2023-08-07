package com.honeybadgersoftware.cheappy.base

import jakarta.annotation.PostConstruct
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.client.RestTemplate
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestPropertySource(locations = "classpath:application-test.properties")

abstract class BaseIntegrationTest extends Specification {

    @LocalServerPort
    protected int port

    @Shared
    protected String addressToUseForTests

    @Autowired
    protected TestRestTemplate restTemplate

    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:15.3")
            .withDatabaseName("postgres")
            .withUsername("test")
            .withPassword("test")

    def setup() {
//        restTemplate = new RestTemplate()
        addressToUseForTests = "http://localhost:${port}"
        postgreSQLContainer.start()
    }

    def cleanup(){
        postgreSQLContainer.stop()
    }
}
