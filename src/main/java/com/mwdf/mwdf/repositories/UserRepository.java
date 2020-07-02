package com.mwdf.mwdf.repositories;

import com.mwdf.mwdf.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}