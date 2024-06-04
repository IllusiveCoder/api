package org.example.webapitableau.controller;

import org.example.webapitableau.api.FavouritesApi;
import org.example.webapitableau.api.RecipeApi;
import org.example.webapitableau.models.PaginatedFavourites;
import org.example.webapitableau.models.PaginatedRecipes;
import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.repository.RecipeRepository;
import org.example.webapitableau.service.RecipeService;
import org.example.webapitableau.service.UserFavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@ComponentScan(basePackages = { "org.example.*" })
@EntityScan("org.example.*")
@Configuration
public class ApiController implements RecipeApi, FavouritesApi {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserFavouritesService userFavouritesService;
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public ResponseEntity<List<Recipe>> getrecipe(@RequestParam Integer id) {
        List<Recipe> recipe = new ArrayList<>();
        recipe.add(recipeService.getRecipe(id));
        return new ResponseEntity<>(recipe, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PaginatedRecipes> getrecipepage(@RequestParam Integer page,@RequestParam Integer size, @RequestParam String title, @RequestParam String uid) {
        PaginatedRecipes paginatedRecipes = recipeService.page(page, size, title, uid);
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

    @Override
    public ResponseEntity<Void> deleterecipe(@RequestParam Integer recipeid, @RequestParam String uid) {
        recipeService.deleterecipe(recipeid, uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PaginatedFavourites> postfavouritrecipes(String uid, Integer recipeid) {
        PaginatedFavourites paginatedFavourites = userFavouritesService.postfavourites(uid, recipeid);
        return new ResponseEntity<>(paginatedFavourites, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PaginatedFavourites> deleltefavouriterecipes(String uid, Integer recipeid) {
        PaginatedFavourites paginatedFavourites = userFavouritesService.deletefavourite(uid,recipeid);
        return new ResponseEntity<>(paginatedFavourites, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PaginatedFavourites> getfavouriterecipes(String uid) {
        PaginatedFavourites paginatedFavourites = userFavouritesService.getfavourites(uid);
        return new ResponseEntity<>(paginatedFavourites, HttpStatus.OK);
    }
}

