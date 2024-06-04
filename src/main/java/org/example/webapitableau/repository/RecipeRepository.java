package org.example.webapitableau.repository;
import org.example.webapitableau.models.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {
    Page<RecipeEntity> findByTitleContaining(String title, Pageable pageable);
    Page<RecipeEntity> findByCreatedby(String uid, Pageable pageable);
    //List<RecipeEntity> findAllBy(List<Integer> ids, Pageable pageable);
}
