package com.honeybadgersoftware.cheappy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;
    @Column(name = "firstName", nullable = false)
    String firstName;
    @Column(name = "lastName")
    String lastName;
    @Column(name = "email", unique = true, nullable = false)
    String email;
    @Column(name = "password", nullable = false)
    String password;
    @Embedded
    @Column(name = "address", nullable = false)
    AddressEntity address;
}
