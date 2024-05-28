package org.example.webapitableau.controller;

import org.example.webapitableau.api.RecipeApi;
import org.example.webapitableau.models.PaginatedRecipes;
import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.models.User;
import org.example.webapitableau.service.RecipeService;
import org.example.webapitableau.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.example.webapitableau.api.UserApi;
import org.example.webapitableau.api.RecipesApi;

import java.util.List;

@RestController
public class ApiController implements RecipesApi, UserApi, RecipeApi {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<List<User>> getusers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> postuser(User body) {
        userService.saveUser(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PaginatedRecipes> getrecipes(Integer page, Integer size) {
        List<Recipe> recipes = recipeService.getAllRecipes(page, size);
        PaginatedRecipes paginatedRecipes = new PaginatedRecipes();
        paginatedRecipes.setRecipes(recipes);
        paginatedRecipes.setTotalItems(recipes.size());
        paginatedRecipes.setCurrentPage(page);
        paginatedRecipes.setTotalPages((int) Math.ceil((double) recipeService.getAllRecipes(0, Integer.MAX_VALUE).size() / size));
        return new ResponseEntity<>(paginatedRecipes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> postrecipes(Recipe body) {
        recipeService.saveRecipe(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Recipe>> getrecipe() {
        List<Recipe> recipes = recipeService.getAllRecipes(0, Integer.MAX_VALUE);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> postrecipe(Recipe body) {
        recipeService.saveRecipe(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
