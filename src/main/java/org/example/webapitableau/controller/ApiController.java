package org.example.webapitableau.controller;

import org.example.webapitableau.api.RecipeApi;
import org.example.webapitableau.models.PaginatedRecipes;
import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.example.webapitableau.api.UserApi;
import org.example.webapitableau.api.RecipesApi;

import java.util.List;

@RestController
//@EnableJpaRepositories("org.example.webapitableau.*")
@ComponentScan(basePackages = { "org.example.webapitableau.*" })
@EntityScan("org.example.webapitableau.*")
public class ApiController implements RecipesApi, UserApi, RecipeApi {


    @Override
    public ResponseEntity<List<Recipe>> getrecipe() {
        return null;
    }

    @Override
    public ResponseEntity<Void> postrecipe(Recipe body) {
        return null;
    }

    @Override
    public ResponseEntity<PaginatedRecipes> getrecipes(Integer page, Integer size) {
        return null;
    }

    @Override
    public ResponseEntity<Void> postrecipes(Recipe body) {
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
