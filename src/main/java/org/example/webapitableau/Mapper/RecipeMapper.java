package org.example.webapitableau.Mapper;

import org.example.webapitableau.models.Recipe;
import org.example.webapitableau.models.RecipeEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RecipeMapper {

    public RecipeEntity toEntity(Recipe dto) {
        if (dto == null) {
            return null;
        }

        RecipeEntity entity = new RecipeEntity();
        entity.setTitle(dto.getTitle());
        entity.setCreatedby(dto.getCreatedby());
        entity.setShortsummary(dto.getShortsummary());
        entity.setPreparation(dto.getPreparation());
        entity.setIngredients(dto.getIngredients());
        entity.setPicture(dto.getPicture());

        return entity;
    }

    public Recipe toDto(RecipeEntity entity) {
        if (entity == null) {
            return null;
        }

        Recipe dto = new Recipe();
        dto.setTitle(entity.getTitle());
        dto.setCreatedby(entity.getCreatedby());
        dto.setShortsummary(entity.getShortsummary());
        dto.setPreparation(entity.getPreparation());
        dto.setIngredients(entity.getIngredients());
        dto.setPicture(entity.getPicture());

        return dto;
    }
}

