package org.example.webapitableau.repository;

import org.example.webapitableau.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    // Custom query methods (if any) can be defined here
}

