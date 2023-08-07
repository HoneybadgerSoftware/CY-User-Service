package com.honeybadgersoftware.cheappy.controller

import com.honeybadgersoftware.cheappy.base.BaseIntegrationTest
import com.honeybadgersoftware.cheappy.model.dto.AddressDto
import com.honeybadgersoftware.cheappy.model.dto.UserDto
import com.honeybadgersoftware.cheappy.repository.entity.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class UserControllerITest extends BaseIntegrationTest {



    def "should create user and return 201 CREATED"() {
        given: "a user and address DTO"

        def address = new AddressDto(
                city: "Warsaw",
                zipCode: "00-001",
                street: "Main St",
                buildingNumber: "123",
                flatNumber: "45A"
        )

        def userDto = new UserDto(
                id: 1L,
                firstName: "John",
                lastName: "Doe",
                email: "john.doe@example.com",
                password: "password123",
                address: address  // Dodaj odpowiednie pola dla AddressDto
        )

        when: "create user endpoint is called"
        ResponseEntity response = restTemplate.exchange(
                addressToUseForTests + "/api/users",
                HttpMethod.POST,
                new HttpEntity<UserDto>(userDto),
                ResponseEntity<UserEntity>)

        then: "response is 201 CREATED"
        response.statusCode == HttpStatus.CREATED
        response.body.firstName == "John"
        // Dodaj więcej asercji dla innych pól, jeśli to konieczne

        and: "user is saved in database"
        // Tutaj możesz sprawdzić, czy użytkownik został zapisany w bazie danych, jeśli masz dostęp do repozytorium
    }
}
