package org.example.webapitableau.controller;

import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.example.webapitableau.api.UserApi;
import org.example.webapitableau.api.RecipesApi;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController implements RecipesApi, UserApi{

    @Override
    public ResponseEntity<List<Recipe>> getrecipelist() {
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setTitle("Quark");
        recipe.setCreatedby("User 1");
        recipe.setIngredients("Quark");
        recipe.setPicture("pictures/quark.png");
        recipe.setPreparation("Quark kaufen");
        recipe.setShortsummary("Wie man Quark macht.");
        recipes.add(recipe);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> postrecipe(Recipe body) {
        return null;
    }

    @Override
    public ResponseEntity<List<User>> getusers() {
        return null;
    }

    @Override
    public ResponseEntity<Void> postuser(User body) {
        return null;
    }
}
