package com.bhargav.expenses.repository;

import org.springframework.stereotype.Repository;

import com.bhargav.expenses.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
