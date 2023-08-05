package com.honeybadgersoftware.cheappy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
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
    @Embedded
    @Column(name = "address", nullable = false)
    Address address;
}
