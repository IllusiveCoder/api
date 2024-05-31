package org.example.webapitableau.service;

import org.example.webapitableau.models.PaginatedRecipes;
import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.models.RecipeEntity;
import org.example.webapitableau.Mapper.RecipeMapper;
import org.example.webapitableau.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(recipeMapper::toDto)
                .collect(Collectors.toList());
    }

    public void addRecipe(Recipe recipe) {
        RecipeEntity recipeEntity = recipeMapper.toEntity(recipe);
        recipeRepository.save(recipeEntity);
    }

    public PaginatedRecipes getPaginatedRecipes(int page, int size) {
        Page<RecipeEntity> recipePage = recipeRepository.findAll(PageRequest.of(page, size));
        List<Recipe> recipes = recipePage.getContent().stream()
                .map(recipeMapper::toDto)
                .collect(Collectors.toList());
        PaginatedRecipes paginatedRecipes = new PaginatedRecipes();
        paginatedRecipes.setRecipes(recipes);
        paginatedRecipes.setCurrentPage(page);
        paginatedRecipes.setTotalItems((int) recipePage.getTotalElements());
        paginatedRecipes.setTotalPages(recipePage.getTotalPages());
        return paginatedRecipes;
    }

    public List<Recipe> searchRecipesByTitle(String title) {
        return recipeRepository.findByTitleContaining(title).stream()
                .map(recipeMapper::toDto)
                .collect(Collectors.toList());
    }
}
