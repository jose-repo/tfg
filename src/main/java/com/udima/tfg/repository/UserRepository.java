package com.udima.tfg.repository;

import com.udima.tfg.entitiy.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.username = ?1 AND u.password = ?2")
    Optional<UserEntity> findByUsername(String username, String password);
}

