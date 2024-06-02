package org.example.webapitableau.service;

import org.example.webapitableau.models.PaginatedRecipes;
import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.models.RecipeEntity;
import org.example.webapitableau.Mapper.RecipeMapper;
import org.example.webapitableau.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    public Recipe getRecipe(Integer id) {
        return (Recipe) recipeRepository.findAll(Pageable.ofSize(id));
    }

    public void addRecipe(Recipe recipe) {
        RecipeEntity recipeEntity = recipeMapper.toEntity(recipe);
        recipeRepository.save(recipeEntity);
    }

    public RecipeEntity updateRecipe(Integer id, Recipe updatedrecipe) {
        return recipeRepository.findById(id)
                .map(oldrecipe -> {
                    oldrecipe.setTitle(updatedrecipe.getTitle());
                    oldrecipe.setShortsummary(updatedrecipe.getShortsummary());
                    oldrecipe.setCreatedby(updatedrecipe.getCreatedby());
                    oldrecipe.setIngredients(updatedrecipe.getIngredients());
                    oldrecipe.setPreparation(updatedrecipe.getPreparation());
                    oldrecipe.setPicture(updatedrecipe.getPicture());

                    return recipeRepository.save(oldrecipe);
                })
                .orElseGet(() -> {
                    updatedrecipe.setId(id);
                    RecipeEntity recipeEntity = recipeMapper.toEntity(updatedrecipe);
                    return recipeRepository.save(recipeEntity);
                });

    }

    public PaginatedRecipes page(Integer page, Integer size, String title) {
        return null;
    }
}
