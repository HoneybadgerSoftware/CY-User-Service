package com.honeybadgersoftware.userservice.controller

import com.honeybadgersoftware.userservice.base.BaseIntegrationTest
import com.honeybadgersoftware.userservice.model.dto.AddressDto
import com.honeybadgersoftware.userservice.model.dto.UserDto
import com.honeybadgersoftware.userservice.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*

class UserControllerITest extends BaseIntegrationTest {

    @Autowired
    UserRepository userRepository

    def "should create user and return 201 CREATED"() {
        given: "a user and address DTO"
        def addressDto = new AddressDto(
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
                address: addressDto
        )

        when: "create user endpoint is called"
        ResponseEntity response = restTemplate.postForEntity(addressToUseForTests + "/api/users", userDto, UserDto)

        then: "response is 201 CREATED"
        response.statusCode == HttpStatus.CREATED
        response.body.firstName == "John"

        and: "user is saved in database"
        def userInDb = userRepository.findById(1L)
        userInDb.isPresent()
        with(userInDb.get()) {
            id == 1L
            firstName == "John"
            lastName == "Doe"
            email == "john.doe@example.com"
            password == "password123"
            with(address) {
                city == "Warsaw"
                zipCode == "00-001"
                street == "Main St"
                buildingNumber == "123"
                flatNumber == "45A"
            }
        }
    }

    def "should return bad request when posted user's email is not unique"() {
        given: "a user and address DTO"
        def addressDto = new AddressDto(
                city: "Warsaw",
                zipCode: "00-001",
                street: "Main St",
                buildingNumber: "123",
                flatNumber: "45A"
        )

        def userDto = new UserDto(
                id: 5L,
                firstName: "John",
                lastName: "Doe",
                email: "john.doe-duplicate@example.com",
                password: "password123",
                address: addressDto
        )

        when: "create user endpoint is called"
        ResponseEntity response = restTemplate.postForEntity(addressToUseForTests + "/api/users", userDto, UserDto)

        then: "response is 400 Bad request"
        response.statusCode == HttpStatus.BAD_REQUEST
    }

    def "should get user by ID"() {
        given: "An existing user ID"
        Long userId = 3L

        when: "Requesting the user by ID"
        ResponseEntity<UserDto> response = restTemplate.getForEntity(addressToUseForTests + "/api/users/${userId}", UserDto)

        then: "The user is retrieved successfully"
        response.statusCode.value() == HttpStatus.OK.value()
        response.body.id == userId
    }

    def "should return not found for non-existing user ID"() {
        given: "A non-existing user ID"
        Long userId = 999L

        when: "Requesting the user by ID"
        ResponseEntity<UserDto> response = restTemplate.getForEntity(addressToUseForTests + "/api/users/${userId}", UserDto)

        then: "Not found response is returned"
        response.statusCode.value() == HttpStatus.NOT_FOUND.value()
    }

    def "should update user by ID"() {
        given: "An existing user ID and updated user details"
        long userId = 3L

        def addressDto = new AddressDto(
                city: "Warsaw",
                zipCode: "00-001",
                street: "Main St",
                buildingNumber: "123",
                flatNumber: "45A"
        )

        def updatedUser = new UserDto(
                id: userId,
                firstName: "Jane",
                lastName: "Doe",
                email: "john.doe-duplicate@example.com",
                password: "password123",
                address: addressDto
        )

        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        HttpEntity<UserDto> request = new HttpEntity<>(updatedUser, headers)

        when: "Requesting to update the user"
        ResponseEntity<String> response = restTemplate.exchange(addressToUseForTests + "/api/users/${userId}", HttpMethod.PUT, request, String)

        then: "The user is updated successfully"
        response.statusCode.value() == HttpStatus.OK.value()
        response.body == "User with id: " + userId + " successfully updated"
    }

    def "should return not found for non-existing user ID"() {
        given: "A non-existing user ID and user details"
        Long userId = 999L

        def addressDto = new AddressDto(
                city: "Warsaw",
                zipCode: "00-001",
                street: "Main St",
                buildingNumber: "123",
                flatNumber: "45A"
        )

        def updatedUser = new UserDto(
                id: userId,
                firstName: "Jane",
                lastName: "Doe",
                email: "john.doe-duplicate@example.com",
                password: "password123",
                address: addressDto
        )

        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        HttpEntity<UserDto> request = new HttpEntity<>(updatedUser, headers)

        when: "Requesting to update the user"
        ResponseEntity<String> response = restTemplate.exchange(addressToUseForTests + "/api/users/${userId}", HttpMethod.PUT, request, String)

        then: "Not found response is returned"
        response.statusCode.value() == HttpStatus.NOT_FOUND.value()
        response.body == "User with provided id does not exists"
    }

    def "should delete user by ID"() {
        given: "An existing user ID"
        Long userId = 3L

        when: "Requesting to delete the user"
        ResponseEntity<String> response = restTemplate.exchange(addressToUseForTests + "/api/users/${userId}", HttpMethod.DELETE, null, String)

        then: "The user is deleted successfully"
        response.statusCode.value() == HttpStatus.OK.value()
        response.body == "Succesfully deleted entity with id: " + userId
    }

    def "should return not found for non-existing user ID"() {
        given: "A non-existing user ID"
        Long userId = 999L  // Assuming this ID doesn't exist.

        when: "Requesting to delete the user"
        ResponseEntity<String> response = restTemplate.exchange(addressToUseForTests + "/api/users/${userId}", HttpMethod.DELETE, null, String)

        then: "Not found response is returned"
        response.statusCode.value() == HttpStatus.NOT_FOUND.value()
        response.body == "Entity not found"
    }
}
