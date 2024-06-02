package org.example.webapitableau.controller;

import org.example.webapitableau.api.RecipeApi;
import org.example.webapitableau.models.PaginatedRecipes;
import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.models.RecipeEntity;
import org.example.webapitableau.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RestController
@ComponentScan(basePackages = { "org.example.webapitableau.*" })
@EntityScan("org.example.webapitableau.*")
public class ApiController implements RecipeApi {

    @Autowired
    private RecipeService recipeService;

    @Override
    public ResponseEntity<List<Recipe>> getrecipe(@RequestParam Integer id) {
        List <Recipe> recipeList = new ArrayList<Recipe>();
        Recipe recipe = recipeService.getRecipe(id);
        recipeList.add(recipe);
        return new ResponseEntity<>(recipeList, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PaginatedRecipes> getrecipepage(Integer page, Integer size, @RequestParam String title) {
        PaginatedRecipes paginatedRecipes = recipeService.page(title);
        return new ResponseEntity<>(paginatedRecipes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> postrecipe(Recipe body) {
        recipeService.addRecipe(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> putrecipe(@RequestParam Integer id,@RequestParam Recipe body) {
        recipeService.updateRecipe(id,body);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

