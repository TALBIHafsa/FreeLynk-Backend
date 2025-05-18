package org.example.freelynk.repository;

import org.example.freelynk.model.Role;
import org.example.freelynk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Find user by email (used for login)
    Optional<User> findByEmail(String email);

    // Find all freelancers (role-based filtering)
    List<User> findByRole(Role role);
}
