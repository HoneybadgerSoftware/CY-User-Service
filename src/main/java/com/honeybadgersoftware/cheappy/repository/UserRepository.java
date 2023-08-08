package com.honeybadgersoftware.cheappy.repository;

import com.honeybadgersoftware.cheappy.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
