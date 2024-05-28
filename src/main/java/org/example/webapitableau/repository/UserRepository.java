package org.example.webapitableau.repository;

import org.example.webapitableau.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods (if any) can be defined here
}

