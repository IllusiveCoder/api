package org.example.webapitableau.controller;

import org.example.webapitableau.api.RecipeApi;
import org.example.webapitableau.api.RecipesApi;
import org.example.webapitableau.models.PaginatedRecipes;
import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@ComponentScan(basePackages = { "org.example.webapitableau.*" })
@EntityScan("org.example.webapitableau.*")
public class ApiController implements RecipesApi, RecipeApi {

    @Autowired
    private RecipeService recipeService;

    @Override
    public ResponseEntity<List<Recipe>> getrecipe() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }

    @Override
    public ResponseEntity<Void> postrecipe(Recipe body) {
        recipeService.addRecipe(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<PaginatedRecipes> getrecipes(Integer page, Integer size) {
        PaginatedRecipes paginatedRecipes = recipeService.getPaginatedRecipes(page, size);
        return ResponseEntity.ok(paginatedRecipes);
    }

    @Override
    public ResponseEntity<Void> postrecipes(Recipe body) {
        recipeService.addRecipe(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipes(@RequestParam("title") String title) {
        List<Recipe> recipes = recipeService.searchRecipesByTitle(title);
        return ResponseEntity.ok(recipes);
    }
}

