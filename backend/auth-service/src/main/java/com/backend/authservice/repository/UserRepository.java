package com.backend.authservice.repository;


import com.backend.authservice.entity.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
@Hidden
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);
    boolean existsUsersByUsername(String username);

}