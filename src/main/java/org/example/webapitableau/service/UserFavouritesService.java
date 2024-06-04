package org.example.webapitableau.service;

import org.example.webapitableau.Mapper.RecipeMapper;
import org.example.webapitableau.models.PaginatedFavourites;
import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFavouritesService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    public PaginatedFavourites deletefavourite(String uid, Integer recipeid) {
        return null;
    }

    public PaginatedFavourites getfavourites(String uid) {
        return null;
    }

    public PaginatedFavourites postfavourites(String uid, Integer recipeid) {
        return null;
    }
}
