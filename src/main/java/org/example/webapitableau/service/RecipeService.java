package org.example.webapitableau.service;

import org.example.webapitableau.models.PaginatedRecipes;
import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.models.RecipeEntity;
import org.example.webapitableau.Mapper.RecipeMapper;
import org.example.webapitableau.models.RecipeShort;
import org.example.webapitableau.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

    public PaginatedRecipes page(@RequestParam Integer pageno, @RequestParam Integer pagesize, @RequestParam(required = false) String title) {
        Pageable pageable = PageRequest.of(pageno,pagesize);
        Page<RecipeEntity> recipeEntityPage = null;
        List<RecipeEntity> recipeEntityList;
        if(title.isEmpty())
        {
            recipeEntityPage = recipeRepository.findAll(pageable);
            recipeEntityList = recipeEntityPage.getContent();
        }
        else{
            Page<Recipe> recipePage = recipeRepository.findByTitleContaining(title, pageable);
            recipeEntityList =  RecipeMapper.mapRecipesToRecipeEntities(recipePage.getContent());
        }
        List<RecipeShort> recipeShortList = RecipeMapper.mapRecipeEntitiesToRecipeShorts(recipeEntityList);

        PaginatedRecipes paginatedRecipes = new PaginatedRecipes();
        paginatedRecipes.setContent(recipeShortList);
        paginatedRecipes.setCurrentpageNo(recipeEntityPage.getNumber());
        paginatedRecipes.setPagesize(recipeEntityPage.getSize());
        paginatedRecipes.setTotalItems((int) recipeEntityPage.getTotalElements());
        paginatedRecipes.setTotalPages(recipeEntityPage.getTotalPages());
        paginatedRecipes.setIslast(recipeEntityPage.isLast());

        return paginatedRecipes;
    }
}
