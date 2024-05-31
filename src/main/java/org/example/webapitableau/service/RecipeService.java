package org.example.webapitableau.service;

import org.example.webapitableau.models.RecipeEntity;
import org.example.webapitableau.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<RecipeEntity> getAllRecipes() {
        return recipeRepository.findAll();
    }

}
