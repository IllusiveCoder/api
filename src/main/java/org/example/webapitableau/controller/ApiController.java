package org.example.webapitableau.controller;

import org.example.webapitableau.api.RecipeApi;
import org.example.webapitableau.models.PaginatedRecipes;
import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@ComponentScan(basePackages = { "org.example.*" })
@EntityScan("org.example.*")
@Configuration
public class ApiController implements RecipeApi {

    @Autowired
    private RecipeService recipeService;

    @Override
    public ResponseEntity<List<Recipe>> getrecipe(@RequestParam Integer id) {
        List<Recipe> recipe = new ArrayList<>();
        recipe.add(recipeService.getRecipe(id));
        return new ResponseEntity<>(recipe, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PaginatedRecipes> getrecipepage(@RequestParam Integer page,@RequestParam Integer size, @RequestParam String title) {
        PaginatedRecipes paginatedRecipes = recipeService.page(page, size, title);
        return new ResponseEntity<>(paginatedRecipes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> postrecipe(Recipe body) {
        recipeService.addRecipe(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> putrecipe(@RequestParam Integer id,@RequestBody Recipe body) {
        recipeService.updateRecipe(id,body);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

