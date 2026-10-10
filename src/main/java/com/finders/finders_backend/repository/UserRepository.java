package com.finders.finders_backend.repository;

import com.finders.finders_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query method - Spring Data JPA reads this method name and
    // builds the SQL automatically: "find a user WHERE email = ?"
    // this is needed for login later, to look a user up by their email.
    Optional<User> findByEmail(String email);

    // checks if an email is already taken before creating a new
    // user, without fetching the whole record.
    boolean existsByEmail(String email);
}