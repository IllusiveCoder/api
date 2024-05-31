package org.example.webapitableau.repository;

import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.models.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    @Query("SELECT r FROM RecipeEntity r WHERE r.title LIKE %:title%")
    List<RecipeEntity> findByTitleContaining(@Param("title") String title);

}