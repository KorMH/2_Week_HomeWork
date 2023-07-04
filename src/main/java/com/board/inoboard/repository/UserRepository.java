package com.board.inoboard.repository;


import com.board.inoboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String Email);
    Optional<User> findByPassword(String Password);
}