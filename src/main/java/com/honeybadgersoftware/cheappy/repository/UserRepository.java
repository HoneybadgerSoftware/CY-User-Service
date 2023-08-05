package com.honeybadgersoftware.cheappy.repository;

import com.honeybadgersoftware.cheappy.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
