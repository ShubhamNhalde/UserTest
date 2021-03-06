package com.bridgelabz.lsm.repository;

import com.bridgelabz.lsm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * from user_details WHERE email= :email", nativeQuery = true)
    Optional<User> findByEmail(String email);

    Optional<User> findByToken(String token);
}
