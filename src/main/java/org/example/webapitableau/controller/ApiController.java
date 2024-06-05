package org.example.webapitableau.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.messaging.FirebaseMessaging;
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

import javax.naming.AuthenticationException;
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

    private final FirebaseAuth firebaseAuth;
    private final FirebaseMessaging firebaseMessaging;

    @Autowired
    public ApiController(FirebaseAuth firebaseAuth, FirebaseMessaging firebaseMessaging) {
        this.firebaseAuth = firebaseAuth;
        this.firebaseMessaging = firebaseMessaging;
    }

    @Override
    public ResponseEntity<List<Recipe>> getrecipe(@RequestParam Integer id, @RequestParam String uid) {

        try {
            firebaseAuth.getUser(uid);
        } catch (FirebaseAuthException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Recipe> recipe = new ArrayList<>();
        recipe.add(recipeService.getRecipe(id));
        return new ResponseEntity<>(recipe, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PaginatedRecipes> getrecipepage(@RequestParam Integer page,@RequestParam Integer size, @RequestParam String title, @RequestParam String uid, @RequestParam Boolean created) {

        try {
            firebaseAuth.getUser(uid);
        } catch (FirebaseAuthException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (!created) uid = "";

        PaginatedRecipes paginatedRecipes = recipeService.page(page, size, title, uid);
        return new ResponseEntity<>(paginatedRecipes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> postrecipe(@RequestParam String uid,@RequestParam Recipe body) {

        try {
            firebaseAuth.getUser(uid);
            if(uid != body.getCreatedby()) throw new AuthenticationException();
        } catch (FirebaseAuthException |AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        recipeService.addRecipe(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> putrecipe(@RequestParam Integer id,@RequestParam String uid,@RequestBody Recipe body) {

        try {
            firebaseAuth.getUser(uid);
            if(uid != body.getCreatedby()) throw new AuthenticationException();
        } catch (FirebaseAuthException |AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        recipeService.updateRecipe(id,body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleterecipe(@RequestParam Integer recipeid, @RequestParam String uid) {
        try {
            firebaseAuth.getUser(uid);
        } catch (FirebaseAuthException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        recipeService.deleterecipe(recipeid, uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PaginatedFavourites> postfavouritrecipes(String uid, Integer recipeid) {
        try {
            firebaseAuth.getUser(uid);
        } catch (FirebaseAuthException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        PaginatedFavourites paginatedFavourites = userFavouritesService.postfavourites(uid, recipeid);
        return new ResponseEntity<>(paginatedFavourites, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PaginatedFavourites> deleltefavouriterecipes(String uid, Integer recipeid) {
        try {
            firebaseAuth.getUser(uid);
        } catch (FirebaseAuthException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        PaginatedFavourites paginatedFavourites = userFavouritesService.deletefavourite(uid,recipeid);
        return new ResponseEntity<>(paginatedFavourites, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PaginatedFavourites> getfavouriterecipes(String uid) {
        try {
            firebaseAuth.getUser(uid);
        } catch (FirebaseAuthException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        PaginatedFavourites paginatedFavourites = userFavouritesService.getfavourites(uid);
        return new ResponseEntity<>(paginatedFavourites, HttpStatus.OK);
    }
}

