package org.example.webapitableau.service;

import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.models.RecipeEntity;
import org.example.webapitableau.Mapper.RecipeMapper;
import org.example.webapitableau.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    public Recipe getRecipe(Integer id) {
        RecipeEntity recipeEntity = (RecipeEntity) recipeRepository.getById(id);
        return recipeMapper.toDto(recipeEntity);
    }

    public String addRecipe(Recipe recipe) {
        RecipeEntity recipeEntity = recipeMapper.toEntity(recipe);
        recipeRepository.save(recipeEntity);
        return "Recipe created";
    }

    public RecipeEntity updateRecipe(Integer id, Recipe updatedrecipe) {
        return recipeRepository.findById(id)
                .map(oldrecipe -> {
                    oldrecipe.setTitle(updatedrecipe.getTitle());
                    oldrecipe.setShortsummary(updatedrecipe.getShortsummary());
                    oldrecipe.setCreatedby(updatedrecipe.getCreatedby());
                    oldrecipe.setIngredients(updatedrecipe.getIngredients());
                    oldrecipe.setPreparation(updatedrecipe.getPreparation());
                    oldrecipe.setPicture(updatedrecipe.getPicture());
                    oldrecipe.setBakingtime(updatedrecipe.getBakingtime());

                    return recipeRepository.save(oldrecipe);
                })
                .orElseGet(() -> {
                    updatedrecipe.setId(id);
                    RecipeEntity recipeEntity = recipeMapper.toEntity(updatedrecipe);
                    return recipeRepository.save(recipeEntity);
                });

    }

    public List<Recipe> page(Integer pageno, Integer pagesize,String title) {
        Pageable pageable = PageRequest.of(pageno,pagesize);
        Page<RecipeEntity> page = recipeRepository.findAll(pageable);
        List<RecipeEntity> recipes = page.getContent();
        return recipes.stream().map(p -> recipeMapper.toDto(p)).collect(Collectors.toList());
    }
}
