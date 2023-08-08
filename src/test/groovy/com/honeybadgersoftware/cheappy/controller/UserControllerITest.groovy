package com.honeybadgersoftware.cheappy.controller

import com.honeybadgersoftware.cheappy.base.BaseIntegrationTest
import com.honeybadgersoftware.cheappy.model.dto.AddressDto
import com.honeybadgersoftware.cheappy.model.dto.UserDto
import com.honeybadgersoftware.cheappy.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class UserControllerITest extends BaseIntegrationTest {

    @Autowired
    UserRepository userRepository

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
                address: address
        )

        when: "create user endpoint is called"
        ResponseEntity response = restTemplate.postForEntity(addressToUseForTests + "/api/users", userDto, UserDto)

        then: "response is 201 CREATED"
        response.statusCode == HttpStatus.CREATED
        response.body.firstName == "John"

        and: "user is saved in database"
        def userInDb = userRepository.findById(1L)
        userInDb.isPresent()
        userInDb.get().firstName == "John"
    }
}
