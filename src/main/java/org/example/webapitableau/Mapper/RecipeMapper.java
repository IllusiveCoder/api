package org.example.webapitableau.Mapper;

import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.models.RecipeEntity;
import org.example.webapitableau.models.RecipeShort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RecipeMapper {

    public RecipeEntity toEntity(Recipe dto, Integer id) {
        if (dto == null) {
            return null;
        }
        RecipeEntity entity = new RecipeEntity();
        if (id != null) {
            entity.setId(id.longValue());
        }
        else{
            entity.setId(Long.valueOf(dto.getId()));
        }

        entity.setTitle(dto.getTitle());
        entity.setCreatedby(dto.getCreatedby());
        entity.setShortsummary(dto.getShortsummary());
        entity.setPreparation(dto.getPreparation());
        entity.setIngredients(dto.getIngredients());
        entity.setBakingtime(Integer.valueOf(dto.getBakingtime()));
        entity.setPicture(dto.getPicture());

        return entity;
    }

    public Recipe toDto(RecipeEntity entity) {
        if (entity == null) {
            return null;
        }

        Recipe dto = new Recipe();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setCreatedby(entity.getCreatedby());
        dto.setShortsummary(entity.getShortsummary());
        dto.setPreparation(entity.getPreparation());
        dto.setIngredients(entity.getIngredients());
        dto.setBakingtime(Integer.valueOf(entity.getBakingtime()));
        dto.setPicture(entity.getPicture());

        return dto;
    }

    public static List<RecipeShort> mapRecipesToRecipeShorts(List<Recipe> recipes) {
        return recipes.stream()
                .map(recipe -> {
                    RecipeShort recipeShort = new RecipeShort();
                    recipeShort.setId(Math.toIntExact(recipe.getId()));
                    recipeShort.setTitle(recipe.getTitle());
                    recipeShort.setBakingTime(recipe.getBakingtime());
                    recipeShort.setShortsummary(recipe.getShortsummary());
                    recipeShort.setPicture(recipe.getPicture());
                    return recipeShort;
                })
                .collect(Collectors.toList());
    }

    public static List<RecipeShort> mapRecipeEntitiesToRecipeShorts(List<RecipeEntity> recipeEntities) {
        return recipeEntities.stream()
                .map(recipeEntity -> {
                    RecipeShort recipeShort = new RecipeShort();
                    recipeShort.setId(recipeEntity.getId());
                    recipeShort.setTitle(recipeEntity.getTitle());
                    recipeShort.setShortsummary(recipeEntity.getShortsummary());
                    recipeShort.setPicture(recipeEntity.getPicture());
                    recipeShort.setBakingTime(recipeEntity.getBakingtime());
                    return recipeShort;
                })
                .collect(Collectors.toList());
    }

    public static List<RecipeEntity> mapRecipesToRecipeEntities(List<Recipe> recipes) {
        return recipes.stream()
                .map(recipe -> {
                    RecipeEntity recipeEntity = new RecipeEntity();
                    recipeEntity.setId(Long.valueOf(recipe.getId()));
                    recipeEntity.setTitle(recipe.getTitle());
                    recipeEntity.setCreatedby(recipe.getCreatedby());
                    recipeEntity.setShortsummary(recipe.getShortsummary());
                    recipeEntity.setPreparation(recipe.getPreparation());
                    recipeEntity.setIngredients(recipe.getIngredients());
                    recipeEntity.setBakingtime(recipeEntity.getBakingtime());
                    recipeEntity.setPicture(recipe.getPicture());
                    return recipeEntity;
                })
                .collect(Collectors.toList());
    }

    public static List<Recipe> mapRecipeEntitiesToRecipes(List<RecipeEntity> recipeEntities) {
        return recipeEntities.stream()
                .map(recipeEntity -> {
                    Recipe recipe = new Recipe();
                    recipe.setId(recipeEntity.getId());
                    recipe.setTitle(recipeEntity.getTitle());
                    recipe.setCreatedby(recipeEntity.getCreatedby());
                    recipe.setShortsummary(recipeEntity.getShortsummary());
                    recipe.setPreparation(recipeEntity.getPreparation());
                    recipe.setIngredients(recipeEntity.getIngredients());
                    recipe.setBakingtime(recipeEntity.getBakingtime());
                    recipe.setPicture(recipeEntity.getPicture());
                    return recipe;
                })
                .collect(Collectors.toList());
    }
}


