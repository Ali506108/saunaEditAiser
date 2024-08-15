package com.example.securetyStart.repository;

import com.example.securetyStart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {

    Optional<User> findByUsername(String Email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
