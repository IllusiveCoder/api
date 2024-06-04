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
import java.util.*;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    public Recipe getRecipe(Integer id) {
        RecipeEntity recipeEntity = recipeRepository.getById(id);
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
                    oldrecipe.setBakingtime(Integer.valueOf(updatedrecipe.getBakingtime()));

                    return recipeRepository.save(oldrecipe);
                })
                .orElseGet(() -> {
                    Integer newid = recipeRepository.findAll().getLast().getId() + 1;
                    updatedrecipe.setId(newid);
                    RecipeEntity recipeEntity = recipeMapper.toEntity(updatedrecipe);
                    return recipeRepository.save(recipeEntity);
                });

    }
    public PaginatedRecipes page(@RequestParam Integer pageno, @RequestParam Integer pagesize, String title, String uid) {
        Pageable pageable = PageRequest.of(pageno, pagesize);
        List<RecipeEntity> recipeEntityList;
        PaginatedRecipes paginatedRecipes = new PaginatedRecipes();
        if (title.isEmpty() && uid.isEmpty()) {
            Page<RecipeEntity> recipeEntityPage = recipeRepository.findAll(pageable);
            recipeEntityList = recipeEntityPage.getContent();
            List<RecipeShort> recipeShortList = RecipeMapper.mapRecipeEntitiesToRecipeShorts(recipeEntityList);
            paginatedRecipes.setContent(recipeShortList);
            paginatedRecipes.setCurrentpageNo(recipeEntityPage.getNumber());
            paginatedRecipes.setPagesize(recipeEntityPage.getSize());
            paginatedRecipes.setTotalItems((int) recipeEntityPage.getTotalElements());
            paginatedRecipes.setTotalPages(recipeEntityPage.getTotalPages());
            paginatedRecipes.setIslast(recipeEntityPage.isLast());
        } else {
            if (!uid.isEmpty()) {
                Page<RecipeEntity> recipeEntityPage = recipeRepository.findByCreatedby(uid, pageable);
                recipeEntityList = recipeEntityPage.getContent();
                List<RecipeShort> recipeShortList = RecipeMapper.mapRecipeEntitiesToRecipeShorts(recipeEntityList);
                paginatedRecipes.setContent(recipeShortList);
                paginatedRecipes.setCurrentpageNo(recipeEntityPage.getNumber());
                paginatedRecipes.setPagesize(recipeEntityPage.getSize());
                paginatedRecipes.setTotalItems((int) recipeEntityPage.getTotalElements());
                paginatedRecipes.setTotalPages(recipeEntityPage.getTotalPages());
                paginatedRecipes.setIslast(recipeEntityPage.isLast());
            } else {
                Page<RecipeEntity> recipeEntityPage = recipeRepository.findByTitleContaining(title, pageable);
                recipeEntityList = recipeEntityPage.getContent();
                List<RecipeShort> recipeShortList = RecipeMapper.mapRecipeEntitiesToRecipeShorts(recipeEntityList);
                paginatedRecipes.setContent(recipeShortList);
                paginatedRecipes.setCurrentpageNo(recipeEntityPage.getNumber());
                paginatedRecipes.setPagesize(recipeEntityPage.getSize());
                paginatedRecipes.setTotalItems((int) recipeEntityPage.getTotalElements());
                paginatedRecipes.setTotalPages(recipeEntityPage.getTotalPages());
                paginatedRecipes.setIslast(recipeEntityPage.isLast());
            }
        }
        return paginatedRecipes;
    }

    public String deleterecipe(Integer recipeid, String uid) {
        Optional<RecipeEntity> recipeEntityList = recipeRepository.findById(recipeid);
        String message;
        if (recipeEntityList.isPresent()) {
            RecipeEntity recipeEntity = recipeEntityList.get();
            if (recipeEntity.getCreatedby().equals(uid))
            {
                recipeRepository.delete(recipeEntity);
                message = "Recipe deleted";
            }
            else{
                message = "Recipe not deleted";
            }
        }
        else{
            message = "Recipe not deleted";
        }
        return message;
    }

}
