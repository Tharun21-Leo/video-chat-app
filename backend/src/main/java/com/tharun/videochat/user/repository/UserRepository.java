package com.tharun.videochat.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tharun.videochat.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByMobileNumber(String mobileNumber);

    Optional<User> findByMobileNumber(String mobileNumber);
}